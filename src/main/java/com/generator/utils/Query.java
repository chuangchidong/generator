package com.generator.utils;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author
 *
 * @date 2017-03-14 23:15
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page;
    //每页条数
    private int limit;

    public Query(Map<String, Object> params){
        this.putAll(params);

        String pageNum = params.get("page") == null ? "1" : params.get("page").toString();
        String size = params.get("limit") == null ? "20" : params.get("limit").toString();
        //分页参数
        this.page = Integer.parseInt(pageNum);
        this.limit = Integer.parseInt(size);
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
