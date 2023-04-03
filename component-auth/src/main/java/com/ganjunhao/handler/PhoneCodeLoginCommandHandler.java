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

package com.ganjunhao.handler;

import com.ganjunhao.domain.dto.UserLoginCommandDTO;
import com.ganjunhao.domain.vo.UserLoginRespVO;
import com.ganjunhao.strategy.AbstractExecuteStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 手机验证登录
 *
 * @author chen.ma
 */
@Component
@AllArgsConstructor
public class PhoneCodeLoginCommandHandler implements AbstractExecuteStrategy<UserLoginCommandDTO, UserLoginRespVO> {
    

    @Override
    public String mark() {
        return "customer_user_login_phone";
    }
    
    @Override
    public UserLoginRespVO executeResp(UserLoginCommandDTO requestParam) {
        // 邮箱登录逻辑
        return new UserLoginRespVO(null, "手机验证登录", "手机验证登录", "手机验证登录");
    }
}
