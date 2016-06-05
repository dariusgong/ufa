package org.ufa.core.model;

/**
 * <P>仅用于ajax分页条，保存各种页码信息和将要显示的数据</P>
 * 
 *  
 */
public class Pager {
	/**
	 * 每页显示条数
	 */
	private int rowNum;
	/**
	 * 数据总条数
	 */
	private int totalRow;
	/**
	 * 当前页数
	 */
	private int nowPage;
	/**
	 * 显示页数
	 */
	private int pageNum;

	/**
	 * <p>Method for constructor</p>
	 */
	public Pager() {
		this.rowNum = 10;
		this.totalRow = 0;
		this.nowPage = 1;
		this.pageNum = 1;
	}

	public int getNowPage() {
		return nowPage;
	}

	/**
	 * <p>设置当前页，如果小于1或者大于最大页数，则取最近值</p>
	 * 
	 * @param nowPage 当前页数
	 *  
	 */
	public void setNowPage(int nowPage) {
		if (nowPage < 1) {
			nowPage = 1;
		}
		if (nowPage > this.getPageNum()) {
			nowPage = this.getPageNum();
		}
		this.nowPage = nowPage;
	}

	/**
	 * <p>取得显示页数</p>
	 * 
	 * @return
	 *  
	 */
	public int getPageNum() {
		if (totalRow % rowNum == 0) {
			pageNum = totalRow / rowNum;
		} else {
			pageNum = totalRow / rowNum + 1;
		}
		return pageNum;
	}

	public void setPageNum(int pages) {
		this.pageNum = pages;
	}

	public int getRowNum() {
		return rowNum;
	}

	/**
	 * <p>设置每页显示条数</p>
	 * 
	 * @param rowNum 每页显示条数
	 *  
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getTotalRow() {
		return totalRow;
	}

	/**
	 * <p>设置数据总条数</p>
	 * 
	 * @param totalRow 数据总条数
	 *  
	 */
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

}
