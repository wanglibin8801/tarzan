package com.tarzan.common.mybatis.util;

import com.tarzan.common.mybatis.enums.CryptTypeEnum;

/**
 * @author 58491
 * @description:
 * @date 2021/6/8
 */
public class CryptLoaderUtils {

    /**
     * 加载所有加密方式实现类
     */
    public static void loadCrypt() {
        CryptContextUtils.setCrypt(CryptTypeEnum.AES, null);

//        CryptContextUtils.setCrypt(CryptTypeEnum.AES, new AesCtrCryptoUtil());
    }
}
