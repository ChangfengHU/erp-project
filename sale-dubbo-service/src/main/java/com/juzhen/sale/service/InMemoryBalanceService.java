//package com.juzhen.sale.service;
//
//import com.alibaba.fastjson.JSON;
//import com.juzhen.api.sale.dto.Balance;
//import com.juzhen.api.sale.dto.CustomerRpcDTO;
//import com.juzhen.api.sale.service.BalanceService;
//import com.juzhen.sale.entity.Customer;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.dubbo.config.annotation.Service;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service(protocol = "dubbo")
//@Slf4j
//public class InMemoryBalanceService implements BalanceService {
//
//    final static Map<Integer, Balance> balanceMap = new HashMap() {{
//        put(1, new Balance(1, 10, 1000));
//        put(2, new Balance(2, 0, 10000));
//        put(3, new Balance(3, 100, 0));
//    }
//    };
//    @Autowired
//    ICustomerService iCustomerService;
//
//    @Override
//    public Balance getBalance(Integer id) {
//        log.info("getBalance 接收到请求");
//        List<Customer> list = iCustomerService.list();
//        List<CustomerRpcDTO> customerDTOS = new ArrayList<>();
//        for (Customer customer : list) {
//            CustomerRpcDTO customerDTO = new CustomerRpcDTO();
//            BeanUtils.copyProperties(customer,customerDTO);
//            customerDTOS.add(customerDTO);
//        }
//        log.info("getBalance 接收到请求={}", JSON.toJSONString(customerDTOS));
//        if(id != null && balanceMap.containsKey(id)) {
//            return balanceMap.get(id);
//        }
//
//        return new Balance(0, 0, 0, "不存在");
//    }
//}
