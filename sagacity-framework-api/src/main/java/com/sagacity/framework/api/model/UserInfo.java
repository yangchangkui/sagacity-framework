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
package com.sagacity.framework.api.model;

import lombok.Data;

/**
 * 请求头封装类
 * @author xingyun
 * @date 2020-07-11 19:39
 */
@Data
public class UserInfo {
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * traceId
     */
    private String traceId;
}
