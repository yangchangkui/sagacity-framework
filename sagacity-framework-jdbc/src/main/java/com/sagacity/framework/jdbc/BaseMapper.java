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
package com.sagacity.framework.jdbc;

import com.sagacity.framework.api.model.request.GenericBO;

import java.io.Serializable;
import java.util.List;

/**
 * 基础Mapper接口
 * @author xingyun
 * @date 2020-07-04 12:58
 */
public interface BaseMapper<T> {

    /**
     * 插入一条记录
     * @param entity 实体对象
     */
    Integer insert(T entity);

    /**
     * 根据 ID 删除
     * @param entity 实体对象（主键ID）
     */
    Integer disable(T entity);

    /**
     * 根据 ID 修改
     * @param entity 实体对象
     */
    Integer updateById(T entity);

    /**
     * 根据 ID 查询
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectBatchIds(List<Serializable> idList);

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param entity 实体对象（可以为 null）
     */
    T selectOne(T entity);

    /**
     * 根据 entity 条件，查询总数
     *
     * @param entity 实体对象（可以为 null）
     */
    Integer selectCount(T entity);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param entity 实体对象（可以为 null）
     */
    List<T> selectList(T entity);

    /**
     * 根据 entity 或 generalConditions 条件，分页查询数据
     * @param bo 分页查询条件
     */
    List<T> search(GenericBO<T> bo);

}