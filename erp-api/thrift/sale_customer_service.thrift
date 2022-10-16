namespace java com.juzhen.api.sale.customer

struct CustomerRpcDTO{
  1:i64 id,
  2:string name,
  3:string phone,
  4:string projectIds,

  5:i32 sellerId,

  6:i32 shopId,

  7:string source,

  8:string weddingDay,

  /**
   * 礼服师
   */
  9:string dresser,

  /**
   * 预算
   */
  10:i64 budget,

  /**
   * 婚纱推荐
   */
  11:string dress,

  /**
   * 西服推荐
   */
  12:string suit,

  /**
   * 珠宝推荐
   */
  13:string jewel,

  /**
   * 四大推荐
   */
  14:string sida,

  15:string remark,

  16:i32 createUserId,

  17:string createdAt,

  18:string upstringdAt,

  19:string deletedAt,

  20:string projectInfo,

  /**
   * 0 全部  1 已进店 2 未预约 3 已预约
   */
  21:i32 bookStatus,

  22:string inshopTime,
}

service CustomerRpcService {
    /**
     * 根据id获取管理模块
     */
    CustomerRpcDTO getById(1:i64 id);
    list<CustomerRpcDTO> queryCustomerList();
}

