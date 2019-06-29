package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.AccountBean;

/**
 * 
 * @author meitao
 */
public interface AccountDao {
	int insert(AccountBean bean);

	int inserts(java.lang.Iterable<AccountBean> bean);

	AccountBean selectOne(AccountBean where);

	List<AccountBean> select(AccountBean where);

	List<AccountBean> selectPaged(AccountBean where, RowBounds rowBounds);

	java.lang.Long total(AccountBean where);

	int deletes();

	int delete(Integer id);

	int update(AccountBean set);

	int updates(AccountBean sets);

	int updateAll(AccountBean set);

	AccountBean selectById(Integer id);

	List<AccountBean> selectByIdArray(Integer[] ids);

	List<Integer> selectId(AccountBean where);

	List<AccountBean> selectAll();
}