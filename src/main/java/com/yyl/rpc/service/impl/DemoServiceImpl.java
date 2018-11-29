package com.yyl.rpc.service.impl;

import com.yyl.rpc.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author yyl
 * @date 2018年11月29日 13:21
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String add(String a, String b) {
        return a+b;
    }
}
