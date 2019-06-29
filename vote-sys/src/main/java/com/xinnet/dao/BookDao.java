package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.BookBean;

/**
 * 
 * @author meitao
 */
public interface BookDao {
	int insert(BookBean bean);

	int inserts(java.lang.Iterable<BookBean> bean);

	BookBean selectOne(BookBean where);

	List<BookBean> select(BookBean where);

	List<BookBean> selectPaged(BookBean where, RowBounds rowBounds);

	java.lang.Long total(BookBean where);

	int deletes();

	int delete(Long id);

	int update(BookBean set);

	int updates(BookBean sets);

	int updateAll(BookBean set);

	BookBean selectById(Long id);

	List<BookBean> selectByIdArray(Long[] ids);

	List<Long> selectId(BookBean where);

	List<BookBean> selectAll();
}