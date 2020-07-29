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
package com.sagacity.framework.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xingyun
 * @date 2020-07-18 16:51
 */
public final class RequestUtil {

    /**
     * 获取 HttpServletResponse
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if(requestAttributes instanceof ServletRequestAttributes){
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
            if(servletRequestAttributes.getResponse() == null){
                throw new IllegalStateException("response is null");
            }
            return servletRequestAttributes.getResponse();
        }
        // 不是一个 servlet 请求
        throw new IllegalStateException("not servlet request");
    }


    /**
     * 获取 HttpServletResponse
     * @return HttpServletResponse
     */
    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if(requestAttributes instanceof ServletRequestAttributes){
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        // 不是一个 servlet 请求
        throw new IllegalStateException("not servlet request");
    }


}
