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

package com.ganjunhao.service.impl;

import com.ganjunhao.constant.R;
import com.ganjunhao.domain.AliPayRequest;
import com.ganjunhao.domain.PayRequest;
import com.ganjunhao.service.PayService;
import com.ganjunhao.springboot.starter.designparttern.strategy.AbstractStrategyChoose;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 支付接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Service
@RequiredArgsConstructor
public final class PayServiceImpl implements PayService {
    
    private final AbstractStrategyChoose abstractStrategyChoose;
    

    @Override
    public R commonPay(AliPayRequest requestParam) {
        /**
         * {@link AliPayNativeHandler}
         */
        String s = requestParam.buildMark();
        // 策略模式：通过策略模式封装支付渠道和支付场景，用户支付时动态选择对应的支付组件
        String result = abstractStrategyChoose.chooseAndExecuteResp(requestParam.buildMark(), requestParam);
        return R.ok(result);
    }
}
