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
package com.sagacity.framework.api.model.response;

import com.sagacity.framework.api.constant.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应对象
 * @author xingyun
 * @date 2020-07-04 14:13
 */
@Data
@ApiModel(description = "响应对象")
public class ResponseEntity<T> {

    @ApiModelProperty("响应码")
    private String code;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    @ApiModelProperty("追踪ID（记录请求）")
    private String traceId;

    public static <T> ResponseEntity<T> create(){
        return new ResponseEntity<>();
    }
    public void ok(){
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMsg();
    }

    public void fail(){
        this.code = ResponseCode.FAIL.getCode();
        this.msg = ResponseCode.FAIL.getMsg();
    }

    public void fail(String message){
        this.code = ResponseCode.FAIL.getCode();
        this.msg = message;
    }

    public ResponseEntity<T> setData(T data){
        this.data = data;
        return this;
    }

}
