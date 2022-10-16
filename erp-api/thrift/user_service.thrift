namespace java com.juzhen.api.user

struct UserRpcDTO{
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
service UserRpcService {
    /**
     * 根据id获取用户
     */
    UserRpcDTO getUserById(1:i64 id);
    /**
     * 根据名字获取用户
     */
    UserRpcDTO getUserByName(1:string username);
    /**
     * 插入用户
     */
    void insertUser(1:UserRpcDTO userInfo);
    /**
     * 修改用户
     */
    void updateUser(1:UserRpcDTO userInfo);
    /**
     * 删除用户
     */
    void deleteUser(1:i64 id);

}

