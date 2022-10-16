package com.juzhen.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzhen.sale.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 客户 Mapper 接口
 * </p>
 *
 * @author hcf
 * @since 2022-10-11
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
