namespace java com.juzhen.api.sale.test

struct TestRpcDTO{
      1:i64 id,
      2:string username,
      3:string password,
      4:string salt,
      5:string email,
      6:string realName,
      7:string mobile,
      /**
      * 状态  0：禁用   1：正常 人/服务  add/insert query/get modify/update remove/delete
      */
      8:i32 status,
      /**
       * 类型 1 管理员  2 工厂 3销售
       */
      9:i32 kind,
      10:i32 createUserId,
      11:string  createTime
}
service TestRpcService {

    /**
     * 根据名字获取用户
     */
    TestRpcDTO testUserByName(1:string username);


}

