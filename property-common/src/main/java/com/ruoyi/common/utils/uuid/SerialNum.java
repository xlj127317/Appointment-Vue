package com.ruoyi.common.utils.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 肖理君
 * @date 2022/07/06 11:06
 **/
public class SerialNum {
    private static String count = "000";
    private static String dateValue = "20131115";

    /**
     * 产生流水号
     */
    public synchronized static String getMoveOrderNo(String laboratoryName) {
        long No = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(new Date());
        No = Long.parseLong(nowDate);
        if (!(String.valueOf(No)).equals(dateValue)) {
            count = "000";
            dateValue = String.valueOf(No);
        }
        String num = String.valueOf(No);
        num += getNo(count);
        num = laboratoryName + num;
        return num;
    }


    /**
     * 获取撤展单序列号
     */
//    public synchronized static String getMoveOrderNo(String serialNum) {
//        String nyr = StringUtils.substring(serialNum, 2, 10); // 获取年月日字符串
//        String countV = StringUtils.substring(serialNum, 10); // 获取流水号
//        if (Integer.valueOf(countV) > Integer.valueOf(count)) {
//            dateValue = nyr;
//            count = String.valueOf(countV);
//        }
//        return getMoveOrderNo();
//    }

    /**
     * 返回当天的订单数+1
     */
    public static String getNo(String s) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < 3; j++) {
            rs = "0" + rs;
        }
        count = rs;
        return rs;
    }
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(getMoveOrderNo());
//        }
//    }
}
