package com.xinnet.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.xinnet.entity.MessageReadInfoBean;

/**
 * 消息查看信息表
 * @author meitao
 */
public interface IMessageReadInfoDao {
	int insert(MessageReadInfoBean bean);

	int inserts(java.lang.Iterable<MessageReadInfoBean> bean);

	MessageReadInfoBean selectOne(MessageReadInfoBean where);

	List<MessageReadInfoBean> select(MessageReadInfoBean where);

	List<MessageReadInfoBean> selectPaged(MessageReadInfoBean where, RowBounds rowBounds);

	java.lang.Long total(MessageReadInfoBean where);

	int deletes();

	int delete(String id);

	int update(MessageReadInfoBean set);

	int updates(MessageReadInfoBean sets);

	int updateAll(MessageReadInfoBean set);

	MessageReadInfoBean selectById(String id);

	List<MessageReadInfoBean> selectByIdArray(String[] ids);

	List<String> selectId(MessageReadInfoBean where);

	List<MessageReadInfoBean> selectAll();
}