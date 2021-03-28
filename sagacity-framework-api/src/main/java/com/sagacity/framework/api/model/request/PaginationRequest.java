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
package com.sagacity.framework.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询请求实体
 * @author xingyun
 * @date 2020-07-04 13:19
 */
@Data
@ApiModel(description = "分页查询请求实体")
public class PaginationRequest<T> {

    @ApiModelProperty("简单查询实体")
    private T entity;

    @ApiModelProperty("通用查询实体")
    private GeneralConditions generalConditions;

    @ApiModelProperty("页数")
    private int pageNum;

    @ApiModelProperty("每页条数")
    private int pageSize;
}
