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

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 主键ID生成工具类
 * @author xingyun
 * @date 2020-07-11 18:25
 */
@Slf4j
public class IdClient {

    private static IdWorker idWorker = new IdWorker(getMaxWorkerId());

    /**
     * 下个ID，默认12开头
     * @return id
     */
    public static Long nextId(){
        return idWorker.nextId(12);
    }

    /**
     * 下个ID，prefixNumber开头
     * @param prefixNumber 前缀
     * @return id
     */
    public static Long nextId(int prefixNumber){
        return idWorker.nextId(prefixNumber);
    }

    /**
     * 获取 maxWorkerId
     */
    protected static long getMaxWorkerId() {
        StringBuilder mpid = new StringBuilder();
        mpid.append(getDatacenterId());
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (StringUtils.isNotBlank(name)) {
            /*
             * GET jvmPid
             */
            mpid.append(name.split("@")[0]);
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % ((~(-1L << 5)) + 1);
    }

    /**
     * 数据标识id部分
     */
    protected static long getDatacenterId() {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % ((~(-1L << 5)) + 1);
                }
            }
        } catch (Exception e) {
            log.warn(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

}
