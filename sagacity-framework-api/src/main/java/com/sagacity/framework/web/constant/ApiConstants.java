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
package com.sagacity.framework.web.constant;

/**
 * API模块的常量
 * @author xingyun
 * @date 2020-07-04 13:49
 */
public final class ApiConstants {
    private ApiConstants(){}

    /**
     * 空字符串
     */
    public static final String STR_EMPTY = "";

    /**
     * 前缀
     */
    public static final String CRITERION_PREFIX = "(";

    /**
     * 后缀
     */
    public static final String CRITERION_SUFFIX = ")";

    /**
     * 关联条件-OR
     */
    public static final String CONNECT_OR = "OR";

    /**
     * 关联条件-AND
     */
    public static final String CONNECT_AND = "AND";
}
