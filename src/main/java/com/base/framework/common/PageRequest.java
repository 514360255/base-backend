package com.base.framework.common;

import com.base.framework.constant.CommonConstant;
import lombok.Data;

/**
 * 分页请求
 * @author guojiuling
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNo = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;

    // 在执行查询语句前，会执行一次count统计
    private boolean count = false;
    // 如果pageNum<1会查询第一页，如果pageNum>pages（总页数）会查询最后一页
    private boolean reasonable = false;
    // 传入参数pageSizeZero （true），那么这时候 传入的 pageSize如果为0，那么就是查询全部
    private boolean pageSizeZero = false;
}
