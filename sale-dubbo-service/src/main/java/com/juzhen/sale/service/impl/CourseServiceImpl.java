package com.juzhen.sale.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzhen.sale.customer.CustomerRpcService;
import com.juzhen.sale.customer.dto.CustomerDTO;
import com.juzhen.sale.entity.Customer;
import com.juzhen.sale.mapper.CustomerMapper;
import com.juzhen.sale.service.ICustomerService;
import com.juzhen.sale.thrift.ServiceProvider;
import com.juzhen.user.api.rpc.UserRpcDTO;
import com.juzhen.user.api.rpc.UserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService , CustomerRpcService{

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CustomerDTO> courseList() {
        log.info("CourseServiceImpl接收到请求");
        List<Customer> list = this.list();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : list) {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer,customerDTO);
            customerDTOS.add(customerDTO);
        }
        UserRpcService.Client userService = serviceProvider.getUserService();
        try {
            UserRpcDTO userRpcDTO = userService.getUserById(80);
            log.info("销售模块获取到用户信息 userRpcDTO={}",JSON.toJSONString(userRpcDTO));
        } catch (TException e) {
            e.printStackTrace();
        }
        return customerDTOS;
    }
}
