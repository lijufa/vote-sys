package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.VoteUserPrivilegeBean;

/**
 * 投票用户特权表
 * @author meitao
 */
public interface IVoteUserPrivilegeDao {
	int insert(VoteUserPrivilegeBean bean);

	int inserts(java.lang.Iterable<VoteUserPrivilegeBean> bean);

	VoteUserPrivilegeBean selectOne(VoteUserPrivilegeBean where);

	List<VoteUserPrivilegeBean> select(VoteUserPrivilegeBean where);

	List<VoteUserPrivilegeBean> selectPaged(VoteUserPrivilegeBean where, RowBounds rowBounds);

	java.lang.Long total(VoteUserPrivilegeBean where);

	int deletes();

	int delete(String id);

	int update(VoteUserPrivilegeBean set);

	int updates(VoteUserPrivilegeBean sets);

	int updateAll(VoteUserPrivilegeBean set);

	VoteUserPrivilegeBean selectById(String id);

	List<VoteUserPrivilegeBean> selectByIdArray(String[] ids);

	List<String> selectId(VoteUserPrivilegeBean where);

	List<VoteUserPrivilegeBean> selectAll();
}