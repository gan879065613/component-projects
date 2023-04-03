/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ganjunhao.controller;

import com.ganjunhao.constant.R;
import com.ganjunhao.domain.dto.UserLoginCommandDTO;
import com.ganjunhao.domain.vo.UserLoginRespVO;
import com.ganjunhao.service.CustomerUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * C 端用户控制器
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@AllArgsConstructor
@Api(tags = "C 端用户")
public class CustomerUserController {
    
    private final CustomerUserService customerUserService;

    
    @ApiOperation("登录")
    @PostMapping("/api/customer-user/login")
    public R<UserLoginRespVO> login(@RequestBody @Valid UserLoginCommandDTO requestParam) {
        UserLoginRespVO result = customerUserService.login(requestParam);
        return R.ok(result);
    }
}
