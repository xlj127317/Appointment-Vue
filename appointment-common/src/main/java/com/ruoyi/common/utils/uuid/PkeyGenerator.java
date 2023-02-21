package com.ruoyi.common.utils.uuid;

import cn.hutool.core.lang.UUID;

/**
 * @author 肖理君
 * @date 2022/07/05 11:17
 **/
public class PkeyGenerator {
    private PkeyGenerator() {
    }

    /**
     * 生成时间戳主键
     */
    public static synchronized String getUniqueString() {
        if (generateCount > 999) {
            generateCount = 0;
        }
        String uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);
        generateCount++;
        return uniqueNumber;
    }

    /**
     * 生成UUID去"-"的字符串
     *
     * @return 总长度为32的字符串
     */
    public static String getUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static final int MAX_GENERATE_COUNT = 999;
    private static int generateCount = 0;


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(PkeyGenerator.getUUIDString());
        }
    }
}
