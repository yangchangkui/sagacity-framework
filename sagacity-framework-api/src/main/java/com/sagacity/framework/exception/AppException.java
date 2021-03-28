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
package com.sagacity.framework.exception;

import com.sagacity.framework.api.constant.ResponseCode;

/**
 * 程序异常类
 * @author xingyun
 * @date 2020-10-19 15:36
 */
public class AppException extends RuntimeException {
    private String code;
    private String msg;

    public AppException(ResponseCode responseCode){
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public AppException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
