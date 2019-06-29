package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.UserBean;

/**
 * 用户表
 * @author meitao
 */
public interface IUserDao {
	int insert(UserBean bean);

	int inserts(java.lang.Iterable<UserBean> bean);

	UserBean selectOne(UserBean where);

	List<UserBean> select(UserBean where);

	List<UserBean> selectPaged(UserBean where, RowBounds rowBounds);

	java.lang.Long total(UserBean where);

	int deletes();

	int delete(String id);

	int update(UserBean set);

	int updates(UserBean sets);

	int updateAll(UserBean set);

	UserBean selectById(String id);

	List<UserBean> selectByIdArray(String[] ids);

	List<String> selectId(UserBean where);

	List<UserBean> selectAll();
}