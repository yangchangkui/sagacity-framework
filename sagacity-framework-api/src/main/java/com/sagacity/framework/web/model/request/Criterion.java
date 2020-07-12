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
package com.sagacity.framework.web.model.request;

import com.sagacity.framework.web.constant.ApiConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询条件
 * @author xingyun
 * @date 2020-07-04 13:37
 */
@Data
@ApiModel(description = "查询条件")
public class Criterion {
    /**
     * 实体对象字段或者表字段都可以
     */
    @ApiModelProperty("字段")
    private String field;

    @ApiModelProperty("字段值")
    private Object value;

    @ApiModelProperty("条件（>、>=、in、like等）")
    private String condition;

    @ApiModelProperty("前缀")
    private String prefix = ApiConstants.CRITERION_PREFIX;

    @ApiModelProperty("后缀")
    private String suffix = ApiConstants.CRITERION_SUFFIX;

    @ApiModelProperty("条件关联（OR、AND）")
    private String connect;
}
