package com.juzhen.user.controller.tob;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juzhen.common.result.ErpResult;
import com.juzhen.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author huchangfeng
 * @since 2021-05-19
 */
public  abstract class  BaseController<Biz extends IService<T>,T extends Model>  {


    public ErpResult userGet(@PathVariable("id") Integer id,IService service) {
        return ErpResult.ok(service.getById(id));
    }

    protected ErpResult post(T dto, IService service) {
        boolean save = service.save(dto);
        return ErpResult.ok(save);
    }
}
