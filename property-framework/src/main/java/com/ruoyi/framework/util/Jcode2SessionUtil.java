package com.ruoyi.framework.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.enums.WeChatUrl;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

/**
 * @author 心风
 * @date 2022/10/06 11:09
 **/
@Slf4j
public class Jcode2SessionUtil {
    /**
     * 请求微信后台获取用户数据
     *
     * @param appid
     * @param secret
     * @param code-wx.login获取到的临时code
     * @param grantType-默认authorization_code
     * @return 请求结果
     * @throws Exception
     */
    public static String jscode2session(String appid, String secret, String code, String grantType) throws Exception {
        //定义返回的json对象
//        JSONObject result = new JSONObject();
//        //创建请求通过code换取session等数据
//        HttpPost httpPost = new HttpPost(WeChatUrl.JS_CODE_2_SESSION.getUrl());
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        //建立一个NameValuePair数组，用于存储欲传送的参数
//        params.add(new BasicNameValuePair("appid", appid));
//        params.add(new BasicNameValuePair("secret", secret));
//        params.add(new BasicNameValuePair("js_code", code));
//        params.add(new BasicNameValuePair("grant_type", grantType));
//        //设置编码 添加参数
//        httpPost.setEntity(new UrlEncodedFormEntity(params));
//        return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
        String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grantType;
        String json = cn.hutool.http.HttpUtil.get(wxLoginUrl);
        log.info(json);
        return json;
    }

    /**
     * 解密用户敏感数据获取用户信息
     *
     * @param encryptedData-包括敏感数据在内的完整用户信息的加密数据
     * @param sessionKey-数据进行加密签名的密钥
     * @param iv-加密算法的初始向量
     * @return
     * @throws Exception
     */
    public static String getUserInfo(String encryptedData, String sessionKey, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
        byte[] resultByte = cipher.doFinal(dataByte);
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(resultByte, "UTF-8");
            log.info(result);
            return result;
        }
        return null;
    }

    /**
     * 获取微信接口调用凭证
     *
     * @param appid
     * @param secret
     * @return 返回String 可转JSON
     */
    public static String getAccessToken(String appid, String secret) {
        JSONObject params = new JSONObject();
        params.put("grant_type", "client_credential");//获取接口调用凭证
        params.put("appid", appid);
        params.put("secret", secret);
        return HttpUtils.sendGet(WeChatUrl.GET_ACCESS_TOKEN.getUrl() + "?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
    }

    /**
     * 发送模板消息
     *
     * @param access_token      接口调用凭证
     * @param touser            接收者（用户）的 openid
     * @param template_id       所需下发的订阅模板id
     * @param page              点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     * @param data              模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     * @param miniprogram_state 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     * @param lang              进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
     * @return String           JSON数据包
     */
    public static String sendTemplateMessage(String access_token, String touser, String template_id, String page, Object data, String miniprogram_state, String lang) {
        JSONObject params = new JSONObject();
        params.put("touser", touser);
        params.put("template_id", template_id);
        if (!StrUtil.isEmptyIfStr(page)) {
            params.put("page", page);
        }
        if (!StrUtil.isEmptyIfStr(miniprogram_state)) {
            params.put("miniprogram_state", miniprogram_state);
        }
        params.put("lang", lang);
        params.put("data", data);
        String log = String.valueOf(params);
        System.out.println(log);
        //发送请求
        return HttpUtils.sendPost(WeChatUrl.SEND_TEMPLATE_MESSAGE.getUrl() + "?access_token=" + access_token, params.toString());
    }

    /**
     * 模板消息发送
     *
     * @param touser 接收者（用户）的 openid
     * @param data   模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     * @return JSON
     */
    public static String sendTemplateMessage(String touser, Object data) {
        SysLoginService sysUserService = new SysLoginService();
        String access_token = getAccessToken(sysUserService.getAppid(), sysUserService.getSecret());
        cn.hutool.json.JSONObject entries = JSONUtil.parseObj(access_token);
        String access = entries.getStr("access_token");
        if (StrUtil.isEmptyIfStr(access)) {
            throw new GlobalException("获取接口调用凭据失败");
        }
        return sendTemplateMessage(access, touser, "", null, data, "", "zh_CN");
    }

    /**
     * 获取授权手机号
     *
     * @param access_token 许可
     * @param code         code
     * @return String
     */
    public static String getPhoneNum(String access_token, String code) {
        JSONObject params = new JSONObject();
        params.put("code", code);
        return HttpUtils.sendPost(WeChatUrl.GET_PHONE_NUM.getUrl() + "?access_token=" + access_token, params.toString());
    }
}
