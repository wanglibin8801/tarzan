package com.tarzan.common.mybatis.enums;

import com.tarzan.common.mybatis.interfaces.Desensitizer;
import lombok.Getter;

/**
 * @author 58491
 * @description: 脱敏策略
 * @date 2021/6/8
 */
public enum DesensitizationStrategy {
    // 用户名
    USERNAME(s -> s.replaceAll("(\\s)\\s(\\s*)", "$1*$2")),
    // 身份证
    ID_CARD(s -> s.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1****$2")),
    // 电话号码
    PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2")),
    // 地址
    ADDRESS(s -> s.replaceAll("(\\S{8})\\S{4}(\\S*)\\S{4}", "$1****$2****"));

    @Getter
    private final Desensitizer desensitizer;

    DesensitizationStrategy(Desensitizer desensitizer) {
        this.desensitizer = desensitizer;
    }


}
