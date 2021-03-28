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
package com.sagacity.framework.api.controller.handler;

import com.sagacity.framework.api.constant.ResponseCode;
import com.sagacity.framework.api.model.response.ResponseEntity;
import com.sagacity.framework.exception.AppException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @author xingyun
 * @date 2020-07-12 21:53
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handler(Exception e){
        ResponseEntity<Object> resp = new ResponseEntity<>();
        // 处理业务异常
        if(e instanceof AppException){
            AppException ae = (AppException) e;
            resp.setCode(ae.getCode());
            resp.setMsg(ae.getMsg());

            // 参数校验异常
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            String defaultMessage = me.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            resp.setMsg(defaultMessage);
            resp.setCode(ResponseCode.ARGUMENT_EMPTY.getCode());

            // 其他类型异常
        }else{
            resp.fail(e.getCause().getMessage());
        }
        return resp;
    }


}
