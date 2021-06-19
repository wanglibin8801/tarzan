package com.tarzan.common.mybatis.enums;

/**
 * @author 58491
 * @description: 验证类型枚举
 * @date 2021/6/8
 */
public enum CryptTypeEnum {
    /**
     * AES加密（这个可是加密，不是脱敏）
     */
    AES,

    /**
     * 手机号
     */
    @Deprecated
    PhoneNumber,

    /**
     * 身份证
     */
    @Deprecated
    IdCard,

    @Deprecated
    /** 银行卡 */
    BankCard
}
