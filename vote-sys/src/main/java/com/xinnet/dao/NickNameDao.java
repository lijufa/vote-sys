package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.NickNameBean;

/**
 * 
 * @author meitao
 */
public interface NickNameDao {
	int insert(NickNameBean bean);

	int inserts(java.lang.Iterable<NickNameBean> bean);

	NickNameBean selectOne(NickNameBean where);

	List<NickNameBean> select(NickNameBean where);

	List<NickNameBean> selectPaged(NickNameBean where, RowBounds rowBounds);

	java.lang.Long total(NickNameBean where);

	int deletes();

	int delete(Long id);

	int update(NickNameBean set);

	int updates(NickNameBean sets);

	int updateAll(NickNameBean set);

	NickNameBean selectById(Long id);

	List<NickNameBean> selectByIdArray(Long[] ids);

	List<Long> selectId(NickNameBean where);

	List<NickNameBean> selectAll();
}