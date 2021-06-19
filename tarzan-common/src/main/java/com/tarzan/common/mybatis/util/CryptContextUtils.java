package com.tarzan.common.mybatis.util;

import com.tarzan.common.mybatis.enums.CryptTypeEnum;
import com.tarzan.common.mybatis.interfaces.Crypt;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 58491
 * @description:
 * @date 2021/6/8
 */
public class CryptContextUtils {

    private static Map<CryptTypeEnum, Crypt> Crypts = new HashMap<>(CryptTypeEnum.values().length);

    /**
     * 获取加密方式
     *
     * @param cryptTypeEnum
     *            加密方式枚举
     * @return 机密方式实现类
     */
    public static Crypt getCrypt(CryptTypeEnum cryptTypeEnum) {
        Crypt crypt = Crypts.get(cryptTypeEnum);
        if (crypt == null) {
            crypt = Crypts.get(CryptTypeEnum.AES);
        }

        return crypt;
    }

    /**
     * 设置加密方式
     *
     * @param cryptTypeEnum
     *            加密类型
     * @param crypt
     *            加载方式
     */
    public static void setCrypt(CryptTypeEnum cryptTypeEnum, Crypt crypt) {
        Crypts.put(cryptTypeEnum, crypt);
    }
}
