package com.github.miemiedev.mybatis.paginator.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 分页器，根据page,limit,totalCount用于页面上分页显示多项内容，计算页码和当前页的偏移量，方便页面分页使用.
 *
 * @author badqiu
 * @author miemiedev
 */
public class Paginator implements Serializable {
	private static final long serialVersionUID = -2429864663690465105L;
	private static final int DEFAULT_SLIDERS_COUNT = 7;
	/**
	 * 分页大小
	 */
	private long limit;
	/**
	 * 页数
	 */
	private long page;
	/**
	 * 总记录数
	 */
	@JsonProperty("total_count")
	private long totalCount;

	public Paginator() {
	}

	public Paginator(long page, long limit, long totalCount) {
		super();
		this.limit = limit;
		this.totalCount = totalCount;
		this.page = computePageNo(page);
	}

	/**
	 * 取得当前页。
	 */
	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	/**
	 * 取得总项数。
	 *
	 * @return 总项数
	 */
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 是否是首页（第一页），第一页页码为1
	 *
	 * @return 首页标识
	 */
	@JsonProperty("first_page")
	public boolean isFirstPage() {
		return page <= 1;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getDefaultSlidersCount() {
		return DEFAULT_SLIDERS_COUNT;
	}

	/**
	 * 是否是最后一页
	 *
	 * @return 末页标识
	 */
	@JsonProperty("last_page")
	public boolean isLastPage() {
		return page >= getTotalPages();
	}

	@JsonProperty("pre_page")
	public long getPrePage() {
		if (isHasPrePage()) {
			return page - 1;
		} else {
			return page;
		}
	}

	@JsonProperty("next_page")
	public long getNextPage() {
		if (isHasNextPage()) {
			return page + 1;
		} else {
			return page;
		}
	}

	/**
	 * 判断指定页码是否被禁止，也就是说指定页码超出了范围或等于当前页码。
	 *
	 * @param page 页码
	 * @return boolean  是否为禁止的页码
	 */
	public boolean isDisabledPage(int page) {
		return ((page < 1) || (page > getTotalPages()) || (page == this.page));
	}

	/**
	 * 是否有上一页
	 *
	 * @return 上一页标识
	 */
	@JsonProperty("has_pre_page")
	public boolean isHasPrePage() {
		return (page - 1 >= 1);
	}

	/**
	 * 是否有下一页
	 *
	 * @return 下一页标识
	 */
	@JsonProperty("has_next_page")
	public boolean isHasNextPage() {
		return (page + 1 <= getTotalPages());
	}

	/**
	 * 开始行，可以用于oracle分页使用 (1-based)。
	 */
	@JsonProperty("start_row")
	public long getStartRow() {
		if (getLimit() <= 0 || totalCount <= 0)
			return 0;
		return page > 0 ? (page - 1) * getLimit() + 1 : 0;
	}

	/**
	 * 结束行，可以用于oracle分页使用 (1-based)。
	 */
	@JsonProperty("end_row")
	public long getEndRow() {
		return page > 0 ? Math.min(limit * page, getTotalCount()) : 0;
	}

	/**
	 * offset，计数从0开始，可以用于mysql分页使用(0-based)
	 */
	public long getOffset() {
		return page > 0 ? (page - 1) * getLimit() : 0;
	}

	/**
	 * 得到 总页数
	 *
	 * @return
	 */
	@JsonProperty("total_pages")
	public long getTotalPages() {
		if (totalCount <= 0) {
			return 0;
		}
		if (limit <= 0) {
			return 0;
		}
		long count = totalCount / limit;
		if (totalCount % limit > 0) {
			count++;
		}
		return count;
	}

	protected long computePageNo(long page) {
		return computePageNumber(page, limit, totalCount);
	}

	/**
	 * 页码滑动窗口，并将当前页尽可能地放在滑动窗口的中间部位。
	 *
	 * @return
	 */
	public Long[] getSlider() {
		return slider(DEFAULT_SLIDERS_COUNT);
	}

	/**
	 * 页码滑动窗口，并将当前页尽可能地放在滑动窗口的中间部位。
	 * 注意:不可以使用 getSlider(1)方法名称，因为在JSP中会与 getSlider()方法冲突，报exception
	 *
	 * @return
	 */
	public Long[] slider(long slidersCount) {
		return generateLinkPageNumbers(getPage(), getTotalPages(), slidersCount);
	}

	private static long computeLastPageNumber(long totalItems, long pageSize) {
		if (pageSize <= 0)
			return 1;
		long result = totalItems % pageSize == 0 ? totalItems / pageSize : totalItems / pageSize + 1;
		if (result <= 1)
			result = 1;
		return result;
	}

	private static long computePageNumber(long page, long pageSize, long totalItems) {
		if (page <= 1) {
			return 1;
		}
		if (Integer.MAX_VALUE == page || page > computeLastPageNumber(totalItems, pageSize)) { //last page
			return computeLastPageNumber(totalItems, pageSize);
		}
		return page;
	}

	private static Long[] generateLinkPageNumbers(long currentPageNumber, long lastPageNumber, long count) {
		long avg = count / 2;
		long startPageNumber = currentPageNumber - avg;
		if (startPageNumber <= 0) {
			startPageNumber = 1;
		}
		long endPageNumber = startPageNumber + count - 1;
		if (endPageNumber > lastPageNumber) {
			endPageNumber = lastPageNumber;
		}
		if (endPageNumber - startPageNumber < count) {
			startPageNumber = endPageNumber - count;
			if (startPageNumber <= 0) {
				startPageNumber = 1;
			}
		}
		java.util.List<Long> result = new java.util.ArrayList<Long>();
		for (long i = startPageNumber; i <= endPageNumber; i++) {
			result.add(new Long(i));
		}
		return result.toArray(new Long[result.size()]);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Paginator");
		sb.append("{page=").append(page);
		sb.append(", limit=").append(limit);
		sb.append(", totalCount=").append(totalCount);
		sb.append('}');
		return sb.toString();
	}
}
