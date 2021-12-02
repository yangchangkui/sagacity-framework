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
package com.sagacity.framework.jdbc.cache;

import cn.hutool.extra.spring.SpringUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xingyun
 * @date 2020-07-12 18:19
 */
public class MybatisRedisCache implements Cache {

    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private RedisTemplate<Object, Object> redisTemplate;

    public MybatisRedisCache(String id){
        this.id = id;
        if(redisTemplate == null){
            redisTemplate = SpringUtil.getBean("redisTemplate");
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate.boundHashOps(id).put(key,value);
    }

    @Override
    public Object getObject(Object key) {
        return redisTemplate.boundHashOps(id).get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return redisTemplate.boundHashOps(id).delete(key);
    }

    @Override
    public void clear() {
        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.boundHashOps(id).size();
        if(size != null){
            return Math.toIntExact(size);
        }
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
