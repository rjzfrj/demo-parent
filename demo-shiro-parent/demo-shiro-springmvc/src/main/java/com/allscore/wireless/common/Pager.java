package com.allscore.wireless.common;

/**
 * 用于查询数据库的分页模型[MySQL]
 * 
 */
public class Pager {
	private int firstPage;// 首页
	private int prevPage;// 上一页
	private int page = 1; // 第几页
	private int nextPage;// 下一页
	private int lastPage;// 最后一页
	private int size = 10; // 每页记录数
	private int total;// 总记录数
	private int offest = 0;// 页起始记录偏移

	public Pager() {
	}
	
	public Pager(int page, int size) {
		this.page = page;
		this.size = size;
		offest = (page - 1) * size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return ((this.total + this.size - 1) / this.size);
	}

	public int getPage() {
		if (page >= getTotalPage())
			return getTotalPage();
		if (page <= 0)
			return 1;
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPrevPage() {
		if (page == 1) {
			return 1;
		}
		return page - 1;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		if (page == getTotalPage()) {
			return page;
		}
		return page + 1;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getFirstPage() {
		return 1;
	}

	public int getLastPage() {
		return getTotalPage();
	}

	public int getOffest() {
		return offest;
	}
}
