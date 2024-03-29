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
package com.sagacity.framework.api.service;

import com.github.pagehelper.PageInfo;
import com.sagacity.framework.api.model.request.GenericBO;
import com.sagacity.framework.api.model.response.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 微服务调用基础服务API
 * @author xingyun
 * @date 2020-07-04 14:11
 */
public interface BaseRemoteService<T> {

    /**
     * 插入一条记录
     * @param entity 实体对象
     */
    @PostMapping(value = "/insert")
    ResponseEntity<Integer> insert(@RequestBody T entity);

    /**
     * 根据 ID 删除
     * @param entity 实体对象（主键ID）
     */
    @PostMapping(value = "/deleteById")
    ResponseEntity<Integer> deleteById(@RequestBody T entity);

    /**
     * 根据 ID 修改
     * @param entity 实体对象
     */
    @PostMapping(value = "/updateById")
    ResponseEntity<Integer> updateById(@RequestBody T entity);

    /**
     * 根据 ID 查询
     * @param id 主键ID
     */
    @GetMapping(value = "/getById/{id}")
    ResponseEntity<T> getById(@PathVariable("id") Serializable id);

    /**
     * 查询（根据ID 批量查询）
     * @param idList 包含 idList 主键ID列表(不能为 null 以及 empty)
     */
    @GetMapping(value = "/getByIds")
    ResponseEntity<List<T>> getByIds(List<Serializable> idList);

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param entity 实体对象（可以为 null）
     */
    @PostMapping(value = "/get")
    ResponseEntity<T> get(@RequestBody T entity);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param entity 实体对象（可以为 null）
     */
    @PostMapping(value = "/list")
    ResponseEntity<List<T>> list(@RequestBody T entity);

    /**
     * 根据 entity 或 generalConditions 条件，分页查询数据
     * @param bo 分页查询条件
     */
    @PostMapping(value = "/search")
    ResponseEntity<PageInfo<T>> search(@RequestBody GenericBO<T> bo);

    /**
     * 根据 entity 或 generalConditions 条件，分页查询数据 导出
     * @param bo 分页查询条件 导出
     */
    @PostMapping(value = "/export")
    void export(@RequestBody GenericBO<T> bo);

}
