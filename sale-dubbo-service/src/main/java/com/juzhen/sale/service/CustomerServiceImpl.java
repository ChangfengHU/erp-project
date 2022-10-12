package com.juzhen.sale.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.api.sale.dto.CustomerRpcDTO;
import com.juzhen.api.sale.service.CustomerRpcService;
import com.juzhen.sale.entity.Customer;
import com.juzhen.sale.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 25131 on 2022-10-12.
 */
@Service
@Slf4j
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService, CustomerRpcService {
//    @Autowired
//    private ServiceProvider serviceProvider;
    @Override
    public List<CustomerRpcDTO> queryCustomerList() {
        log.info("CourseServiceImpl接收到请求");
        List<Customer> list = this.list();
        List<CustomerRpcDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : list) {
            CustomerRpcDTO customerDTO = new CustomerRpcDTO();
            BeanUtils.copyProperties(customer,customerDTO);
            customerDTOS.add(customerDTO);
        }
//        UserRpcService.Client userService = serviceProvider.getUserService();
//        try {
//            UserRpcDTO userRpcDTO = userService.getUserById(80);
//            log.info("销售模块获取到用户信息 userRpcDTO={}", JSON.toJSONString(userRpcDTO));
//        } catch (TException e) {
//            e.printStackTrace();
//        }
        return customerDTOS;
    }
}
