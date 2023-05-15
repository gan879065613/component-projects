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

import cn.hutool.core.text.StrBuilder;
import com.ganjunhao.common.PayChannelEnum;
import com.ganjunhao.common.PayTradeTypeEnum;
import com.ganjunhao.domain.AliPayRequest;
import com.ganjunhao.domain.PayRequest;
import com.ganjunhao.springboot.starter.designparttern.strategy.AbstractExecuteStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 阿里支付组件
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@RequiredArgsConstructor
public final class AliPayNativeHandler extends AbstractPayHandler implements AbstractExecuteStrategy<AliPayRequest, String> {

    @Override
    public String pay(PayRequest payRequest) {
        return "pay";
    }
    
    @Override
    public String mark() {
        return StrBuilder.create()
                .append(PayChannelEnum.ALI_PAY.name())
                .append("_")
                .append(PayTradeTypeEnum.NATIVE.name())
                .toString();
    }
    
    @Override
    public String executeResp(AliPayRequest requestParam) {
        return pay(requestParam);
    }
}
