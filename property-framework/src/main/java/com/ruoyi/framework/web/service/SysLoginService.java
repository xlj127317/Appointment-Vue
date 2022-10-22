package com.ruoyi.framework.web.service;

import javax.annotation.Resource;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.framework.util.Jcode2SessionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Resource
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysConfigService configService;

    @Resource
    private SysPermissionService permissionService;

    @Resource
    private SysUserMapper userMapper;

    /**
     *
     */
    public static final long DEPT_ID = 110;

    /**
     * 小程序appid
     */
    @Value("${wx.appId}")
    private String appid;

    /**
     * 小程序密钥
     */
    @Value("${wx.secret}")
    private String secret;

    public static final long ORDINARY_DEPT_NUM = 110;


    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(String userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    public AjaxResult wxLogin(String code) {
        JSONObject sessionInfo = JSONObject.parseObject(jcode2Session(code));
        if (sessionInfo == null) {
            return AjaxResult.error("code 无效");
        }
        // 判断返回值中是否有errcode
        if (sessionInfo.get("errcode") != null) {
            return AjaxResult.error(sessionInfo.getString("errmsg"));
        }
        String openid = sessionInfo.getString("openid");
        if (StrUtil.isEmpty(openid)) {
            return AjaxResult.error("openid 异常");
        }
        SysUser sysUser = userMapper.selectUserById(openid);
        LoginUser loginUser = new LoginUser();
        // 如果用户并未从小程序登录过，则添加
        try {
            if (ObjectUtil.isNull(sysUser)) {
                sysUser = new SysUser();
                sysUser.setUserId(openid);
                //此处用随机方式生成用户名和昵称
                String name = PkeyGenerator.getUniqueString();
                sysUser.setDeptId(ORDINARY_DEPT_NUM);
                sysUser.setUserName(name);
                sysUser.setNickName(name);
                sysUser.setPassword(SecurityUtils.encryptPassword("123456"));
                sysUser.setStatus("1");
                sysUser.setDelFlag("0");
                sysUser.setCreateBy(name);
                userMapper.insertUser(sysUser);
                loginUser.setUserId(openid);
                loginUser.setDeptId(DEPT_ID);
                loginUser.setUser(sysUser);
                return getAjaxResult(openid, loginUser);
            }
        } catch (NullPointerException exception) {
            System.out.println("空指针");
        }
        loginUser.setUserId(sysUser.getUserId());
        loginUser.setDeptId(sysUser.getDeptId());
        loginUser.setUser(sysUser);
        loginUser.setPermissions(permissionService.getMenuPermission(sysUser));
        recordLoginInfo(sysUser.getUserId());
        return getAjaxResult(openid, loginUser);
    }

    /**
     * 生成token 返回token和openId
     *
     * @param openid    微信登录成功后的openId 也是userId
     * @param loginUser 登录用户信息
     * @return AjaxResult
     */
    private AjaxResult getAjaxResult(String openid, LoginUser loginUser) {
        AjaxResult success = AjaxResult.success();
        String token = tokenService.createToken(loginUser);
        success.put("token", token);
        success.put("openId", openid);
        return success;
    }

    /**
     * 登录凭证校验
     *
     * @param code code
     * @return String
     */
    private String jcode2Session(String code) {
        try {
            //登录grantType固定
            return Jcode2SessionUtil.jscode2session(appid, secret, code, "authorization_code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取授权手机号
     *
     * @param code   code
     * @param userId userId
     * @return ajaxResult
     */
    public AjaxResult getPhone(String code, String userId) {
        SysUser sysUser = userMapper.selectUserById(userId);
        if (ObjectUtil.isNull(sysUser)) {
            throw new ServiceException("该用户未曾登录过小程序，请先登录");
        }
        String accessToken = Jcode2SessionUtil.getAccessToken(appid, secret);
        String access_token = (String) JSONUtil.parseObj(accessToken).get("access_token");
        String phoneNum = Jcode2SessionUtil.getPhoneNum(access_token, code);
        cn.hutool.json.JSONObject info = JSONUtil.parseObj(phoneNum);
        if (!info.get("errcode").equals(0)) {
            throw new ServiceException((String) info.get("errmsg"));
        }
        cn.hutool.json.JSONObject phoneInfo = (cn.hutool.json.JSONObject) info.get("phone_info");
        String phoneNumber = (String) phoneInfo.get("phoneNumber");
        if (!StrUtil.equals(sysUser.getUserName(), phoneNumber)) {
            sysUser.setUserName(phoneNumber);
        }
        sysUser.setPhonenumber(phoneNumber);
        int updateNum = userMapper.updateUser(sysUser);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("phoneNumber", phoneNumber);
        ajaxResult.put("userId", userId);
        ajaxResult.put("username", sysUser.getUserName());
        ajaxResult.put("nickname", sysUser.getNickName());
        ajaxResult.put("updateNum", updateNum);
        return AjaxResult.success(ajaxResult);
    }
}
