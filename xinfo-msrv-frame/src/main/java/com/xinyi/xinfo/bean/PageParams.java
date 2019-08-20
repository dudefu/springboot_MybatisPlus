package com.xinyi.xinfo.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分页参数对象实体类")
public class PageParams implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 8253707145188948276L;

    // 页号 从1开始
    @ApiModelProperty(value = "页码（从1开始）")
    private int pageIndex = 1;

    // 分页开始下标
    @ApiModelProperty(value = "分页开始下标", hidden = true)
    private int startIndex = 0;
    
    // 分页开始下标
    @ApiModelProperty(value = "分页结束下标", hidden = true)
    private int endIndex = 0;

    // 每页记录数
    @ApiModelProperty(value = "每页显示多少条，默认10")
    private int pageSize = 10;

    // 排序字段
    @ApiModelProperty(value = "排序字段")
    private String sortField;

    // 排序顺序
    @ApiModelProperty(value = "排序顺序，desc/asc")
    private String sortOrder = "asc";

    // 查询条件
    @ApiModelProperty(value = "查询条件，如queryParams[xx]")
    private Map<String, Object> queryParams = new HashMap<String, Object>();

    // 总记录数
    @ApiModelProperty(value = "数据总数", hidden = true)
    private long totalCount;

    // 导出列参数集合
    @ApiModelProperty(value = "导出列参数集合", hidden = false)
    private List<String> exportList;

    public int getPageIndex()
    {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public int getStartIndex()
    {
        return startIndex;
    }

    public void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getSortField()
    {
        return sortField;
    }

    public void setSortField(String sortField)
    {
        this.sortField = sortField;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Map<String, Object> getQueryParams()
    {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams)
    {
        this.queryParams = queryParams;
    }

    public long getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(long totalCount)
    {
        this.totalCount = totalCount;
    }

    public void setExportList(List<String> exportList) {
        this.exportList = exportList;
    }

    public List getExportList() {
        return exportList;
    }


    @Override
    public String toString()
    {
        return "PageParams [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", sortField=" + sortField + ", sortOrder=" + sortOrder + ", queryParams=" + queryParams
                + ", totalCount=" + totalCount + "]";
    }

}
