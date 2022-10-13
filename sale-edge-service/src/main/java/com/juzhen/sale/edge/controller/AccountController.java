package com.juzhen.sale.edge.controller;


import com.juzhen.api.user.CurrentUser;
import com.juzhen.api.user.UserRpcDTO;
import com.juzhen.api.user.UserRpcService;
import com.juzhen.sale.edge.resolver.Authlogin;
import com.juzhen.sale.edge.thrift.ServiceProvider;
import com.juzhen.sale.edge.vo.SaleVo;
import com.juzhen.sale.service.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("sale/customer")
@Slf4j
public class AccountController {
    @Autowired
    private ServiceProvider serviceProvider;
    final static Map<Integer, User> userMap = new HashMap() {{
            put(1, new User(1, "张三"));
            put(2, new User(2, "李四"));
            put(3, new User(3, "王五"));
        }
    };

//
//    @Reference
//    private CustomerRpcService customerRpcService;

    @RequestMapping("/list")
    public SaleVo getUser(@RequestParam Integer id , @Authlogin CurrentUser currentUser) throws TException {
        log.info("销售模块 id={},user={}",id,currentUser);
        UserRpcService.Client userService = serviceProvider.getUserService();
        UserRpcDTO hcf = userService.getUserByName("hcf");
        SaleVo saleVo = new SaleVo();
        System.out.println("远程调用thrift接口={}"+hcf);
//        List<CustomerRpcDTO> customerRpcDTOS = customerRpcService.queryCustomerList();
//        System.out.println("远程调用thrift接口={}"+customerRpcService);
//        saleVo.setCustomerRpcDTOS(customerRpcDTOS);
        saleVo.setRpcDTO(hcf);
        return saleVo;
    }
}
