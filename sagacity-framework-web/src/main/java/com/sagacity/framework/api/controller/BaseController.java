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
package com.sagacity.framework.api.controller;

import com.github.pagehelper.PageInfo;
import com.sagacity.framework.api.model.request.GenericBO;
import com.sagacity.framework.service.IService;
import com.sagacity.framework.api.model.response.ResponseEntity;
import com.sagacity.framework.api.service.BaseRemoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * 控制器
 * @author xingyun
 * @date 2020-07-05 10:47
 */
public class BaseController<S extends IService<T>,T> implements BaseRemoteService<T> {

    @Autowired
    protected S service;

    @ApiOperation("新增接口")
    @Override
    public ResponseEntity<Integer> insert(@RequestBody T entity) {
        ResponseEntity<Integer> resp = new ResponseEntity<>();
        resp.setData(service.insert(entity)).ok();
        return resp;
    }

    @ApiOperation("根据ID删除接口")
    @Override
    public ResponseEntity<Integer> deleteById(@RequestBody T entity) {
        ResponseEntity<Integer> resp = new ResponseEntity<>();
        resp.setData(service.deleteById(entity)).ok();
        return resp;
    }

    @ApiOperation("根据ID更新接口")
    @Override
    public ResponseEntity<Integer> updateById(@RequestBody T entity) {
        ResponseEntity<Integer> resp = new ResponseEntity<>();
        resp.setData(service.updateById(entity)).ok();
        return resp;
    }

    @ApiOperation("根据ID查询接口")
    @Override
    public ResponseEntity<T> getById(Serializable id) {
        ResponseEntity<T> resp = ResponseEntity.create();
        resp.setData(service.selectById(id)).ok();
        return resp;
    }

    @ApiOperation("根据ID列表查询接口")
    @Override
    public ResponseEntity<List<T>> getByIds(@RequestBody List<Serializable> idList) {
        ResponseEntity<List<T>> resp = new ResponseEntity<>();
        resp.setData(service.selectBatchIds(idList)).ok();
        return resp;
    }

    @ApiOperation("根据条件查询单条数据接口")
    @Override
    public ResponseEntity<T> get(@RequestBody T entity) {
        ResponseEntity<T> resp = new ResponseEntity<>();
        resp.setData(service.selectOne(entity)).ok();
        return resp;
    }

    @ApiOperation("列表查询接口")
    @Override
    public ResponseEntity<List<T>> list(@RequestBody T entity) {
        ResponseEntity<List<T>> resp = new ResponseEntity<>();
        resp.setData(service.selectList(entity)).ok();
        return resp;
    }

    @ApiOperation("通用分页查询接口")
    @Override
    public ResponseEntity<PageInfo<T>> search(@RequestBody GenericBO<T> bo) {
        ResponseEntity<PageInfo<T>> resp = new ResponseEntity<>();
        resp.setData(service.search(bo)).ok();
        return resp;
    }

    @ApiOperation("导出数据接口")
    @Override
    public void export(@RequestBody GenericBO<T> bo) {
        service.export(bo);
    }


}
