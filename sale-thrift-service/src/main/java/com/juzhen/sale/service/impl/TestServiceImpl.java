package com.juzhen.sale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.api.sale.customer.CustomerRpcDTO;
import com.juzhen.api.sale.customer.CustomerRpcService;
import com.juzhen.api.sale.test.TestRpcDTO;
import com.juzhen.api.sale.test.TestRpcService;
import com.juzhen.sale.entity.Customer;
import com.juzhen.sale.mapper.CustomerMapper;
import com.juzhen.sale.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 25131 on 2022-10-12.
 */
@Service
@Slf4j
public class TestServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService ,TestRpcService.Iface {


    @Override
    public TestRpcDTO testUserByName(String username) throws TException {
        log.info("获取到测试请求");
        return new TestRpcDTO().setId(1212);
    }
}
