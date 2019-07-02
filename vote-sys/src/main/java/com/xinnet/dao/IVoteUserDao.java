package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.VoteUserBean;

/**
 * 用户表
 * @author meitao
 */
public interface IVoteUserDao {
	int insert(VoteUserBean bean);

	int inserts(java.lang.Iterable<VoteUserBean> bean);

	VoteUserBean selectOne(VoteUserBean where);

	List<VoteUserBean> select(VoteUserBean where);

	List<VoteUserBean> selectPaged(VoteUserBean where, RowBounds rowBounds);

	java.lang.Long total(VoteUserBean where);

	int deletes();

	int delete(String id);

	int update(VoteUserBean set);

	int updates(VoteUserBean sets);

	int updateAll(VoteUserBean set);

	VoteUserBean selectById(String id);

	List<VoteUserBean> selectByIdArray(String[] ids);

	List<String> selectId(VoteUserBean where);

	List<VoteUserBean> selectAll();
}