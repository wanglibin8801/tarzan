package com.tarzan.common.mybatis.annotation;

import com.tarzan.common.mybatis.enums.CryptTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 58491
 * @description: 加密字段注解
 * @date 2021/6/8
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CryptField {

    CryptTypeEnum value() default CryptTypeEnum.AES;

}
