package com.juzhen.sale.edge.vo;
import com.juzhen.sale.service.User;
import lombok.Data;
import lombok.ToString;

import com.juzhen.sale.edge.thrift.UserRpcDTO;

/**
 * Created by 25131 on 2022-10-12.
 */
@ToString
@Data
public class SaleVo {
    UserRpcDTO rpcDTO;
    User user;
}
