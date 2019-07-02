package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.MessageInfoBean;

/**
 * 消息表
 * @author meitao
 */
public interface IMessageInfoDao {
	int insert(MessageInfoBean bean);

	int inserts(java.lang.Iterable<MessageInfoBean> bean);

	MessageInfoBean selectOne(MessageInfoBean where);

	List<MessageInfoBean> select(MessageInfoBean where);

	List<MessageInfoBean> selectPaged(MessageInfoBean where, RowBounds rowBounds);

	java.lang.Long total(MessageInfoBean where);

	int deletes();

	int delete(String id);

	int update(MessageInfoBean set);

	int updates(MessageInfoBean sets);

	int updateAll(MessageInfoBean set);

	MessageInfoBean selectById(String id);

	List<MessageInfoBean> selectByIdArray(String[] ids);

	List<String> selectId(MessageInfoBean where);

	List<MessageInfoBean> selectAll();
}