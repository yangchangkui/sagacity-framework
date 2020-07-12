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
package com.sagacity.framework.web.controller;

import com.github.pagehelper.PageInfo;
import com.sagacity.framework.service.IService;
import com.sagacity.framework.web.model.request.GenericBO;
import com.sagacity.framework.web.model.request.PaginationRequest;
import com.sagacity.framework.web.model.response.ResponseEntity;
import com.sagacity.framework.web.service.BaseRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 控制器
 * @author xingyun
 * @date 2020-07-05 10:47
 */
public class BaseController<S extends IService<T>,T> implements BaseRemoteService<T> {

    @Autowired
    protected S service;

    @Override
    public ResponseEntity<Integer> insert(@RequestBody T entity) {
        ResponseEntity<Integer> resp = new ResponseEntity<>();
        resp.setData(service.insert(entity)).ok();
        return resp;
    }

    @Override
    public ResponseEntity<Integer> deleteById(@RequestBody T entity) {
        ResponseEntity<Integer> resp = new ResponseEntity<>();
        resp.setData(service.deleteById(entity)).ok();
        return resp;
    }

    @Override
    public ResponseEntity<Integer> updateById(@RequestBody T entity) {
        ResponseEntity<Integer> resp = new ResponseEntity<>();
        resp.setData(service.updateById(entity)).ok();
        return resp;
    }

    @Override
    public ResponseEntity<T> getById(Long id) {
        ResponseEntity<T> resp = new ResponseEntity<>();
        resp.setData(service.selectById(id)).ok();
        return resp;
    }

    @Override
    public ResponseEntity<List<T>> getByIds(@RequestBody GenericBO genericBO) {
        ResponseEntity<List<T>> resp = new ResponseEntity<>();
        resp.setData(service.selectBatchIds(genericBO.getIdList())).ok();
        return resp;
    }

    @Override
    public ResponseEntity<T> get(@RequestBody T entity) {
        ResponseEntity<T> resp = new ResponseEntity<>();
        resp.setData(service.selectOne(entity)).ok();
        return resp;
    }

    @Override
    public ResponseEntity<List<T>> list(@RequestBody T entity) {
        ResponseEntity<List<T>> resp = new ResponseEntity<>();
        resp.setData(service.selectList(entity)).ok();
        return resp;
    }

    @Override
    public ResponseEntity<PageInfo<T>> search(@RequestBody PaginationRequest<T> paginationRequest) {
        ResponseEntity<PageInfo<T>> resp = new ResponseEntity<>();
        resp.setData(service.search(paginationRequest)).ok();
        return resp;
    }
}