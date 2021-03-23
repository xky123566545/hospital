package com.example.demo.util;

import com.github.pagehelper.Page;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class PagedData<T> {
    private long total;//数据总数
    private List<T> data;//数据
    private long totalPage;//总页数
    private long currPage;


    private PagedData(){
        super();
    }
    private static PagedData getInstance() {
        return new PagedData();
    }

    public static PagedData getInstance(Page page) {
        return PagedData.getInstance().setTotal(page.getTotal()).setCurrPage(page.getPageNum()).setTotalPage(page.getPages()).setData(page.getResult());
    }

    public static PagedData getInstance(long totalCount) {
        return PagedData.getInstance().setTotal(totalCount);
    }

    public static PagedData getInstance(long total, long currPage, int pageSize) {
        return PagedData.getInstance().setTotal(total).setCurrPage(currPage).setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
    }

    public PagedData setTotalPage(long totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public PagedData setCurrPage(long currentPage) {
        this.currPage = currentPage;
        return this;
    }

    public PagedData setTotal(long totalCount) {
        this.total = totalCount;
        return this;
    }

    public PagedData setData(List<T> dataRows) {
        this.data = dataRows;
        return this;
    }

    public static Map<String, Object> paramMap(Integer pageNo, Integer pageSize) {
        Map<String, Object> param = new HashMap();
        if (pageNo != null && pageSize != null) {
            long start = (pageNo - 1) * pageSize;
            long end = pageNo * pageSize;
            param.put("start", start);
            param.put("end", end);
            param.put("pageSize", pageSize);
        }
        return param;
    }
}
