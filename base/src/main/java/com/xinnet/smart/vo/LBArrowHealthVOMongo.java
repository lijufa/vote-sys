package com.xinnet.smart.vo;

import java.io.Serializable;

/**
 * 记录负载均衡下绑定的云主机健康状态
 * @author lijufa
 * @date 2017年9月4日 下午2:38:07
 */
public class LBArrowHealthVOMongo implements Serializable {
	private static final long serialVersionUID = 803821240339089983L;
	private String slb_name;//  负载均衡实例名字   slb_name+uuid=slb_name1100007
	private String rs_name;// 云主机名字rs+uuid（云主机uuid）=rs7992074
	private String status;// 异常
	private Long create_time;//创建时间

	public LBArrowHealthVOMongo() {
		super();
	}

	public LBArrowHealthVOMongo(String slb_name, String rs_name, String status, Long create_time) {
		super();
		this.slb_name = slb_name;
		this.rs_name = rs_name;
		this.status = status;
		this.create_time = create_time;
	}

	public String getSlb_name() {
		return slb_name;
	}

	public void setSlb_name(String slb_name) {
		this.slb_name = slb_name;
	}

	public String getRs_name() {
		return rs_name;
	}

	public void setRs_name(String rs_name) {
		this.rs_name = rs_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
}
