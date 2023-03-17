package com.ganjunhao.service;

import com.ganjunhao.constant.R;

/**
 * @author ganjunhao
 * @date 2023/3/16 22:41
 */
public interface HystrixConsumerService {

    public R<Void> test();

    public R<Void> backMethod();

}
