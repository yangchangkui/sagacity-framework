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
package com.sagacity.framework.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sagacity.framework.exception.AppException;
import com.sagacity.framework.jdbc.BaseMapper;
import com.sagacity.framework.service.constant.MethodType;
import com.sagacity.framework.util.ExcelUtils;
import com.sagacity.framework.util.IdClient;
import com.sagacity.framework.util.RequestUtil;
import com.sagacity.framework.util.UserInfoUtil;
import com.sagacity.framework.api.model.request.PaginationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.sagacity.framework.api.constant.ResponseCode.ERROR;

/**
 * 基础服务公共方法
 * @author xingyun
 * @date 2020-07-04 12:58
 */
@Slf4j
public abstract class BaseService<M extends BaseMapper<T>,T> implements IService<T> {

    @Autowired
    private M mapper;

    /**
     * 插入一条记录
     * @param entity 实体对象
     */
    public Integer insert(T entity){
        setDefault(entity,MethodType.INSERT);
        return mapper.insert(entity);
    }




    /**
     * 根据 ID 删除
     * @param entity 主键ID
     */
    public Integer deleteById(T entity){
        setDefault(entity,MethodType.DELETE);
        // 只做逻辑删除
        return mapper.disable(entity);
    }

    /**
     * 根据 ID 修改
     * @param entity 实体对象
     */
    public Integer updateById(T entity){
        setDefault(entity,MethodType.UPDATE);
        return mapper.updateById(entity);
    }

    /**
     * 根据 ID 查询
     * @param id 主键ID
     */
    public T selectById(Long id){
        return mapper.selectById(id);
    }

    /**
     * 查询（根据ID 批量查询）
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    public List<T> selectBatchIds(List<Long> idList){
        return mapper.selectBatchIds(idList);
    }

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param entity 实体对象（可以为 null）
     */
    public T selectOne(T entity){
        return mapper.selectOne(entity);
    }

    /**
     * 根据 entity 条件，查询总记录数
     *
     * @param entity 实体对象（可以为 null）
     */
    public Integer selectCount(T entity){
        return mapper.selectCount(entity);
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param entity 实体对象（可以为 null）
     */
    public List<T> selectList(T entity){
        return mapper.selectList(entity);
    }

    /**
     * 根据 entity 或 generalConditions 条件，分页查询数据
     * @param paginationRequest 分页查询条件
     */
    public PageInfo<T> search(PaginationRequest<T> paginationRequest){
        PageHelper.startPage(paginationRequest.getPageNum(),paginationRequest.getPageSize());
        List<T> tList = mapper.search(paginationRequest);
        return PageInfo.of(tList);
    }


    /**
     * 根据 entity 或 generalConditions 条件，分页查询数据
     * @param entity 分页查询条件
     */
    public void export(T entity) {
        List<T> list = selectList(entity);
        ExcelWriter excelWriter = ExcelUtils.getExcelWriter(null);
        excelWriter.write(list);
        HttpServletResponse response = RequestUtil.getResponse();
        // 设置 Excel 响应头
        setExcelResponseHeaders(response);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            log.error("export get outputStream error",e);
            throw new AppException(ERROR);
        }
        excelWriter.flush(outputStream,true);
    }

    /**
     * 设置响应头
     * @param response
     */
    private void setExcelResponseHeaders(HttpServletResponse response){
        String fileName = IdClient.nextIdStr() + ExcelUtils.XLSX_SUFFIX;
        // 下面几行是为了解决文件名乱码的问题
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
    }


    /**
     * 设置默认值
     * @param entity 实体对象
     */
    private void setDefault(T entity, MethodType methodType) {
        if(MethodType.INSERT.equals(methodType)){
            // 主键ID
            ReflectUtil.setFieldValue(entity,"id", IdClient.nextId());
            // 创建时间、创建人
            ReflectUtil.setFieldValue(entity,"createTime", DateUtil.date());
            ReflectUtil.setFieldValue(entity,"createBy", UserInfoUtil.getName());
        }else if(MethodType.UPDATE.equals(methodType) || MethodType.DELETE.equals(methodType)){
            // 修改时间、修改人
            ReflectUtil.setFieldValue(entity,"modifyTime", DateUtil.date());
            ReflectUtil.setFieldValue(entity,"modifyBy", UserInfoUtil.getName());
        }
        // 删除，逻辑删除
        if(MethodType.DELETE.equals(methodType)){
            ReflectUtil.setFieldValue(entity,"activeFlag",0);
        }else{
            ReflectUtil.setFieldValue(entity,"activeFlag",1);
        }
        // traceId
        ReflectUtil.setFieldValue(entity,"traceId",UserInfoUtil.getTraceId());
    }
}
