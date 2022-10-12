package com.juzhen.sale.edge.controller;


import com.juzhen.sale.edge.vo.SaleVo;
import com.juzhen.sale.service.BalanceService;
import com.juzhen.sale.service.User;
import com.juzhen.sale.edge.thrift.ServiceProvider;
import com.juzhen.sale.edge.thrift.UserRpcDTO;
import com.juzhen.sale.edge.thrift.UserRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
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

    @RequestMapping("/acc/user")
    public SaleVo getUser(@RequestParam Integer id) throws TException {
        UserRpcService.Client userService = serviceProvider.getUserService();
        UserRpcDTO hcf = userService.getUserByName("hcf");
        System.out.println("远程调用thrift接口={}"+hcf);
        SaleVo saleVo = new SaleVo();
        if(id != null && userMap.containsKey(id)) {
            User user = userMap.get(id);
            user.setBalance(balanceService.getBalance(id));
            saleVo.setUser(user);
        }
        saleVo.setRpcDTO(hcf);
        return saleVo;
    }
}
