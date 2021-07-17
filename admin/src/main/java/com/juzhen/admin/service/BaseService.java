//package com.juzhen.admin.service;
//
//import com.baomidou.mybatisplus.extension.service.IService;
//import com.juzhen.admin.entity.BaseDO;
//import com.juzhen.admin.entity.BaseModel;
//import com.juzhen.admin.entity.SysMenu;
//import org.springframework.beans.BeanUtils;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//
///**
// * <p>
// * 菜单管理 服务类
// * </p>
// *
// * @author huchangfeng
// * @since 2021-05-19
// */
//public  class BaseService<M extends BaseModel,D extends BaseDO>  {
//
//    protected BaseDO convertT2DO(BaseModel model) {
//        BaseDO<Long> serializableBaseDO = new BaseDO<>();
//        BeanUtils.copyProperties(model, BaseDO.class);
//        return serializableBaseDO;
//    }
//
////    public D geneDoInstance() {
////        Type genericSuperclass = this.getClass().getGenericSuperclass();
////        ParameterizedType actualTypeArguments = (ParameterizedType) genericSuperclass;
////        Type[] actualTypeArguments1 = actualTypeArguments.getActualTypeArguments();
////        Class<?> aClass = (Class) actualTypeArguments1[1];
////        try {
////            BaseDO baseDO = (BaseDO) aClass.newInstance();
////            return baseDO;
////        } catch (InstantiationException e) {
////            e.printStackTrace();
////        } catch (IllegalAccessException e) {
////            e.printStackTrace();
////        }
//    }
//}
