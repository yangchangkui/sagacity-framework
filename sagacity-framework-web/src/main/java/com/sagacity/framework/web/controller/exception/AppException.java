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
package com.sagacity.framework.web.controller.exception;

import com.sagacity.framework.web.constant.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 应用异常，用于业务异常抛出，全局捕获
 * @author xingyun
 * @date 2020-07-11 13:37
 */
public class AppException extends RuntimeException {
    /**
     * 响应码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    public AppException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public AppException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
