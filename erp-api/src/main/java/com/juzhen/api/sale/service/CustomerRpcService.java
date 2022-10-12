package com.juzhen.api.sale.service;


import com.juzhen.api.sale.dto.CustomerRpcDTO;

import java.util.List;

public interface CustomerRpcService {
   public List<CustomerRpcDTO> queryCustomerList();
}
