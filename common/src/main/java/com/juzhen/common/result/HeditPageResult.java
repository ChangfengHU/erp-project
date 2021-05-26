//package com.juzhen.common.result;
//
//import lombok.Data;
//import lombok.ToString;
//
//import java.util.List;
//
///**
// * Created by 25131 on 2019/8/24.
// */
//@Data
//@ToString
//public class HeditPageResult<T extends List<?>>  extends HeditResult<T>{
//    private static final long serialVersionUID = -2410124673604109647L;
//    /**
//     * 当前页码
//     */
//    private int currentPage;
//
//    /**
//     * 总页数
//     */
//    private int totalPage;
//    /**
//     * 总共项数
//     */
//    private long total;
//    /**
//
//     * 每页项数目
//     */
//    private int pageSize;
//
//    public int getCurrentPage() {
//        return currentPage;
//    }
//
//    public void setCurrentPage(int currentPage) {
//        this.currentPage = currentPage;
//    }
//
//    public int getTotalPage() {
//        return totalPage;
//    }
//
//    public void setTotalPage(int totalPage) {
//        this.totalPage = totalPage;
//    }
//
//    public long getTotal() {
//        return total;
//    }
//
//    public void setTotal(long total) {
//        this.total = total;
//    }
//
//    public int getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//}
