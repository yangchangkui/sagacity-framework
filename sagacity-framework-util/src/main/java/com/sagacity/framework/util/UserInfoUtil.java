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


import com.sagacity.framework.web.model.UserInfo;

/**
 * @author xingyun
 * @date 2020-07-11 19:36
 */
public final class UserInfoUtil {
    /**
     * 当前线程对象
     */
    private static final ThreadLocal<UserInfo> tl = new ThreadLocal<>();

    public static String getName(){
        UserInfo userInfo = tl.get();
        return userInfo == null ? "System":userInfo.getName();
    }

    public static String getAccount(){
        UserInfo userInfo = tl.get();
        return userInfo == null ? "System":userInfo.getAccount();
    }

    public static String getTraceId(){
        UserInfo userInfo = tl.get();
        return userInfo == null ? "":userInfo.getTraceId();
    }

    public void setUserInfo(UserInfo userInfo){
        tl.set(userInfo);
    }

    public UserInfo getUserInfo(){
        return tl.get();
    }

}
