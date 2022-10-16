package com.juzhen.sale.redis;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(
 type= Executor.class,
 method = "update",
 args = {MappedStatement.class,Object.class})})
public class MyInterceptor implements Interceptor {
 @Override
 public Object intercept(Invocation invocation) throws Throwable {
 //拦截⽅法，具体业务逻辑编写的位置
  System.out.println("1111111111111");
  System.out.println(invocation);
 return invocation.proceed();
 }
 @Override
 public Object plugin(Object target) {
 //创建target对象的代理对象,⽬的是将当前拦截器加⼊到该对象中
  System.out.println("22222222");
  System.out.println(target);
 return Plugin.wrap(target, this);
 }
 @Override
 public void setProperties(Properties properties) {
  System.out.println("properties"+properties);
 //属性设置
 }

 /**
  * ⾃定义拦截器
  */
 @Bean
 public MyInterceptor myInterceptor(){
     System.out.println("22222222");
  return new MyInterceptor();
 }

 @Bean
 public SqlExplainInterceptor sqlExplainInterceptor(){
     System.out.println("22222222");
  SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
  List<ISqlParser> sqlParserList = new ArrayList<>();
  // 攻击 SQL 阻断解析器、加⼊解析链
  sqlParserList.add(new BlockAttackSqlParser());
  sqlExplainInterceptor.setSqlParserList(sqlParserList);
  return sqlExplainInterceptor; }
}