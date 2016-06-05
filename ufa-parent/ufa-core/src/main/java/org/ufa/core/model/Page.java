package org.ufa.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于分页，保存各种页码信息和将要显示的数据
 * 注意:setTotalRow方法必须在设置了当前页码(nowPage)和 barNum之后执行，否则
 *      将造成页码段计算错误.正确的使用方法是在执行setTotalRow方法之前执
 *      行setNowPage或setBarNum方法.
 * @param <T> page中传递的对象类型
 *  
 */
public class Page<T> implements Serializable {
	private static final long serialVersionUID = 641061821008392751L;
	private static final int DEFAULT_PAGE_SIZE = 5;
	private static final int DEFAULT_BAR_SIZE = 5;

	/**
	 * 用于存储页码段
	 */
	private List<Integer> pageBar;

	/**
	 * 用于存储每页的数据
	 */
	private List<T> contentBulk;

	/**
	 * 每页的记录数
	 */
	private int rowNum;

	/**
	 * 每段页码段的页数
	 */
	private int barNum;

	/**
	 * 总记录数
	 */
	private int totalRow;

	/**
	 * 页码段的导航标志位,0为向前翻页,1为向后翻页
	 */
	private int previous = 1;

	/**
	 * 当前页码
	 */
	private int nowPage = 1;

	/**
	 * 用于存储附加信息
	 */
	private Object object;

	/**
	 * 使用默认的每页行数和每段页码数来构造一个新的page对象
	 * 默认每页显示5行，每段显示4个页码
	 * @param nowPage 当前页码
	 */
	public Page(final int nowPage) {
		if (nowPage <= 0) {
			throw new IllegalArgumentException("nowPage must > 0");
		}
		this.nowPage = nowPage;
	}

	/**
	 * 构造一个新的page对象
	 * 
	 * @param rowNum
	 *            每页要显示几行 必须大于0
	 * @param barNum
	 *            每段要显示几个页码 必须大于0
	 * @param nowPage
	 *            当前页码 必须大于0
	 */
	public Page(final int rowNum, final int barNum, final int nowPage) {
		if (rowNum <= 0) {
			throw new IllegalArgumentException("rowNum must > 0");
		}
		if (barNum <= 0) {
			throw new IllegalArgumentException("barNum must > 0");
		}
		if (nowPage <= 0) {
			throw new IllegalArgumentException("nowPage must > 0");
		}
		this.rowNum = rowNum;
		this.barNum = barNum;
		this.nowPage = nowPage;
	}

	public Page() {
		this.rowNum = DEFAULT_PAGE_SIZE;
		this.barNum = DEFAULT_BAR_SIZE;
		this.totalRow = 0;
		this.previous = 1;
		this.nowPage = 1;
	}

	public void reset() {
		this.pageBar = null;
		this.contentBulk = null;
		this.rowNum = DEFAULT_PAGE_SIZE;
		this.barNum = DEFAULT_BAR_SIZE;
		this.totalRow = 0;
		this.previous = 1;
		this.nowPage = 1;
		this.object = null;
	}

	public void reset(final int pageSize, final int barSize) {
		pageBar = null;
		contentBulk = null;
		rowNum = pageSize;
		barNum = barSize;
		totalRow = 0;
		previous = 1;
		nowPage = 1;
		object = null;
	}

	/**
	 * 获得总页数
	 */
	public int getTotalPageCount() {
		int result = 0;
		if (totalRow % rowNum == 0) {
			result = totalRow / rowNum == 0 ? 1 : totalRow / rowNum;
		} else {
			result = totalRow / rowNum + 1;
		}
		return result;
	}

	/**
	 * 获得页码段的数量
	 */
	public int getTotalBarCount() {
		int result = 0;
		if (getTotalPageCount() % barNum == 0) {
			result = getTotalPageCount() / barNum;
		} else {
			result = getTotalPageCount() / barNum + 1;
		}
		return result;
	}

	/**
	 * 判断是否为最后一页
	 */
	public boolean isLastPage() {
		return nowPage == getTotalPageCount();
	}

	/**
	 * 判断是否为第一页
	 */
	public boolean isFirstPage() {
		return (nowPage == 1 || nowPage == 0);
	}

	/**
	 * 判断是否为最后一段
	 */
	public boolean isLastBar() {
		return nowPage > (getTotalBarCount() - 1) * getBarNum();
		// return nowPage / barNum == getTotalBarCount() - 1 || isOneBar();
	}

	/**
	 * 判断是否为第一段
	 */
	public boolean isFirstBar() {
		return nowPage <= barNum;
	}

	/**
	 * 判断是否为只有一段
	 */
	public boolean isOneBar() {
		return getTotalPageCount() <= barNum;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = (nowPage > 1 ? nowPage : 1);
	}

	public List<Integer> getPageBar() {
		return pageBar;
	}

	public void setPageBar(List<Integer> pageBar) {
		this.pageBar = pageBar;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getTotalRow() {
		return totalRow;
	}

	/**
	 * <p>设置总记录数,同时会触发计算页码段.</p>
	 * <p>注意:此方法必须在设置了当前页码(nowPage)和 barNum之后执行，否则
	 *      将造成页码段计算错误</p>
	 * @param totalRow 总记录数
	 *  汪一鸣
	 */
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
		initPageBar();
	}

	/**
	 * 通过已知的总记录数和当前页码,计算出需要显示的页码段
	 *  汪一鸣
	 */
	private void initPageBar() {
		// 获得动态页段
		final List<Integer> bar = new ArrayList<Integer>();
		int start = 0;
		int end = 0;
		final int totalPageCount = getTotalPageCount();
		if (nowPage == 1 || nowPage == 2) {
			start = 1;
			end = totalPageCount > barNum ? barNum : totalPageCount;
		} else {
			start = nowPage - 2;
			end = (nowPage + 2) >= totalPageCount ? totalPageCount : (nowPage + 2);
		}
		for (int i = start; i <= end; i++) {
			bar.add(Integer.valueOf(i));
		}
		setPageBar(bar);
	}

	public int getBarNum() {
		return barNum;
	}

	public List<T> getContentBulk() {
		return contentBulk;
	}

	public void setContentBulk(List<T> contentBulk) {
		this.contentBulk = contentBulk;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * 不考虑是否超过最大nowPage，只计算下一个页码段的第一个页码是多少 结合isLastBar()一起使用
	 * 
	 * @return 下一个页码段的第一个页码
	 */
	public int getNextBarPage() {
		int result = 0;
		if (nowPage % barNum == 0) {
			result = nowPage + 1;
		} else {
			result = nowPage + (barNum - nowPage % barNum + 1);
		}
		return result;
	}

	/**
	 * 不考虑是否低于最小nowPage，只计算前一个页码段的最后一个页码是多少 结合isFirstBar()一起使用
	 * 
	 * @return 前一个页码段的最后一个页码
	 */
	public int getPreviousBarPage() {
		int result = 0;
		if (nowPage % barNum == 0) {
			result = nowPage - barNum;
		} else {
			result = nowPage - (nowPage % barNum);
		}
		return result;
	}

	/**
	 * 配合构造SQL中的记录条件，例如Oracle rownum >= startRow
	 * @return 根据当前页和每页显示的行数，计算出启始行数
	 *  汪一鸣
	 */
	public int getStartRow() {
		return (rowNum * (nowPage - 1) + 1);
	}

	/**
	 * 配合构造SQL中的记录条件，例如Oracle rownum <= endRow
	 * @return 根据当前页和每页显示的行数，计算出结束行数
	 *  汪一鸣
	 */
	public int getEndRow() {
		return (getStartRow() + rowNum - 1);
	}

}