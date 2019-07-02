package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.VoteInfoBean;

/**
 * 投票信息表
 * @author meitao
 */
public interface IVoteInfoDao {
	int insert(VoteInfoBean bean);

	int inserts(java.lang.Iterable<VoteInfoBean> bean);

	VoteInfoBean selectOne(VoteInfoBean where);

	List<VoteInfoBean> select(VoteInfoBean where);

	List<VoteInfoBean> selectPaged(VoteInfoBean where, RowBounds rowBounds);

	java.lang.Long total(VoteInfoBean where);

	int deletes();

	int delete(String id);

	int update(VoteInfoBean set);

	int updates(VoteInfoBean sets);

	int updateAll(VoteInfoBean set);

	VoteInfoBean selectById(String id);

	List<VoteInfoBean> selectByIdArray(String[] ids);

	List<String> selectId(VoteInfoBean where);

	List<VoteInfoBean> selectAll();
}