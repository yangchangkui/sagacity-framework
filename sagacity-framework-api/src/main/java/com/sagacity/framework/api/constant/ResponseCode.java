/*
 * Copyright 2020-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sagacity.framework.api.constant;

/**
 * <p>
 *    响应码枚举
 *    1.2000-3000 请求成功使用
 *    2.4000-5000 错误请求使用（参数）
 *    3.5000-6000 服务异常使用
 *    4.6000-7000 结果异常使用
 * </p>
 * @author xingyun
 * @date 2020-07-04 14:17
 */
public enum ResponseCode {
    SUCCESS("2000","success"),
    FAIL("4000","错误请求"),
    ERROR("5000","服务异常"),
    ARGUMENT_EMPTY("4001","参数为空"),
    ;

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
