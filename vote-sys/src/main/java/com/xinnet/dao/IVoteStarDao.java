package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.VoteStarBean;

/**
 * 明星表
 * @author meitao
 */
public interface IVoteStarDao {
	int insert(VoteStarBean bean);

	int inserts(java.lang.Iterable<VoteStarBean> bean);

	VoteStarBean selectOne(VoteStarBean where);

	List<VoteStarBean> select(VoteStarBean where);

	List<VoteStarBean> selectPaged(VoteStarBean where, RowBounds rowBounds);

	java.lang.Long total(VoteStarBean where);

	int deletes();

	int delete(String id);

	int update(VoteStarBean set);

	int updates(VoteStarBean sets);

	int updateAll(VoteStarBean set);

	VoteStarBean selectById(String id);

	List<VoteStarBean> selectByIdArray(String[] ids);

	List<String> selectId(VoteStarBean where);

	List<VoteStarBean> selectAll();
}