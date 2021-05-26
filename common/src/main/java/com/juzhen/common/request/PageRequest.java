package com.juzhen.common.request;


import java.io.Serializable;

/**
 * Created by mrt on 2018/3/5.
 */
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 6085131498791994736L;


    /**
     * 当前页码
     */
    protected  static final int ITEM_PER_PAGe=10;
    /**
     * 当前页码
     */
    protected  int currentPage=1;
    /**
     * 每页条数 默认是10
     */
    protected int pageSize=10;
    /**
     * 每页条数 默认是10
     */
    protected int offset=0;

    public int getOffset() {
        return (this.getCurrentPage() - 1 )* this.getPageSize();
    }

    public void setOffset(int offset) {
        this.currentPage=offset/pageSize+1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage<=1 ?1:currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize =pageSize <=0? ITEM_PER_PAGe:pageSize;
    }

}
