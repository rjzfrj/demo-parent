package com.my.wallet.web.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author lzf
 * @version 1.0
 * @Date 2016年1月8日 下午2:17:06
 */
public class Pagination
{
	private int rowsCount;
	private int pageNo;
	private int pageSize;
	private int pageCount;
	private List<Map> datas;
	
	public  Pagination(int pageNo,int pageSize,int rowsCount,List<Map> datas)
	{
		this.pageNo=pageNo;
		this.pageSize=pageSize;
		this.rowsCount=rowsCount;
		this.datas=datas;
		setPageCount();
	}
	public int getRowsCount()
	{
		return rowsCount;
	}
	public void setRowsCount(int rowsCount)
	{
		this.rowsCount = rowsCount;
	}
	public int getPageNo()
	{
		return pageNo;
	}
	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public int getPageCount()
	{
		return pageCount;
	}
	public void setPageCount()
	{
		this.pageCount =  (int) (((this.rowsCount % this.pageSize) > 0) ? ((this.rowsCount / this.pageSize) + 1)
                : this.rowsCount / this.pageSize);
	}
	public List<Map> getDatas()
	{
		return datas;
	}
	public void setDatas(List<Map> datas)
	{
		this.datas = datas;
	}
	
	


}
