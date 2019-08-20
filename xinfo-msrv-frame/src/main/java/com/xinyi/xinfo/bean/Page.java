package com.xinyi.xinfo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="分页数据")
public class Page<T> implements Serializable
{

    private static final long serialVersionUID = -8859289619082617913L;

    /**
     * page 总记录数
     */
    @ApiModelProperty(value="总记录数")
    private long total;

    /**
     * page 封装的数据列表
     */
    @ApiModelProperty(value="封装的数据列表")
    private List<T> data;
    /**
     * currentPage当前第几页
     */
    @ApiModelProperty(value="当前第几页")
    private int currentPage;
    /**
     * page 页面大小
     */
    @ApiModelProperty(value="页面大小")
    private int pageSize;

    @ApiModelProperty(value="总页数")
    private int pageTotal;

    public Page()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Page(List<T> data, int total, int pageSize)
    {
        super();
        this.total = total;
        this.data = data;
        this.pageSize = pageSize;
    }

    public Page(List<T> data, int total, int currentPage, int pageSize)
    {
        super();
        this.total = total;
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Page(int currentPage, int pageSize)
    {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public long getPageTotal()
    {
        try
        {
            if (total % pageSize == 0)
            {
                this.pageTotal = Integer.valueOf(String.valueOf(total / pageSize));
            }
            else
            {
                this.pageTotal = Integer.valueOf(String.valueOf((total - total % pageSize) / pageSize)) + 1;
            }
            return this.pageTotal;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public void setPageTotal(int pageCount)
    {
        this.pageTotal = pageCount;
    }
}
