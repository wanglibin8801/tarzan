package com.tarzan.common.mybatis.annotation;

import com.tarzan.common.mybatis.enums.DesensitizationStrategy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 58491
 * @description:
 * @date 2021/6/8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Desensitization {

    DesensitizationStrategy strategy() default DesensitizationStrategy.ID_CARD;
}
