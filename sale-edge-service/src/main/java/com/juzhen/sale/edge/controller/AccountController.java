package com.juzhen.sale.edge.controller;


import com.juzhen.api.sale.dto.CustomerRpcDTO;
import com.juzhen.api.sale.service.BalanceService;
import com.juzhen.api.sale.service.CustomerRpcService;
import com.juzhen.api.user.UserRpcDTO;
import com.juzhen.api.user.UserRpcService;
import com.juzhen.sale.edge.vo.SaleVo;
import com.juzhen.sale.service.User;
import com.juzhen.sale.edge.thrift.ServiceProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sale/customer")
public class AccountController {
    @Autowired
    private ServiceProvider serviceProvider;
    final static Map<Integer, User> userMap = new HashMap() {{
            put(1, new User(1, "张三"));
            put(2, new User(2, "李四"));
            put(3, new User(3, "王五"));
        }
    };

    @Reference
    private BalanceService balanceService;
    @Reference
    private CustomerRpcService customerRpcService;

    @RequestMapping("/list")
    public SaleVo getUser(@RequestParam Integer id) throws TException {
        UserRpcService.Client userService = serviceProvider.getUserService();
        UserRpcDTO hcf = userService.getUserByName("hcf");
        SaleVo saleVo = new SaleVo();
        System.out.println("远程调用thrift接口={}"+hcf);
        List<CustomerRpcDTO> customerRpcDTOS = customerRpcService.queryCustomerList();
        System.out.println("远程调用thrift接口={}"+customerRpcService);
        saleVo.setCustomerRpcDTOS(customerRpcDTOS);
        saleVo.setRpcDTO(hcf);
        return saleVo;
    }
}
