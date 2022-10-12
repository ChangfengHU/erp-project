package com.juzhen.sale.edge.vo;
import com.juzhen.api.sale.dto.CustomerRpcDTO;
import com.juzhen.api.user.UserRpcDTO;
import com.juzhen.sale.service.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by 25131 on 2022-10-12.
 */
@ToString
@Data
public class SaleVo {
    UserRpcDTO rpcDTO;
    List<CustomerRpcDTO> customerRpcDTOS;
}
