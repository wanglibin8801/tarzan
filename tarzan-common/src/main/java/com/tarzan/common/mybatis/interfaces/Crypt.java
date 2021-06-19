package com.tarzan.common.mybatis.interfaces;

/**
 * @author 58491
 * @description: 脱敏算法接口定义
 * @date 2021/6/8
 */
public interface Crypt {

    /**
     * 加密
     *
     * @param plain
     *            原始明文
     * @return 密文
     */
    String encrypt(String plain);

    /**
     * 解密
     *
     * @param cipher
     *            密文
     * @return 原始明文
     */
    String decrypt(String cipher);

}
