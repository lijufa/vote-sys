package com.xinnet.smart.vo;

import java.io.Serializable;

/**
 * bin 入站流量（下行）单位字节 b;  bout：出站流量（上行）rate：新建连接数  scur：总连接数  
 * @author lijufa
 * @date 2017年9月4日 下午2:38:07
 */
public class LBInfoMonitor implements Serializable {
	private static final long serialVersionUID = 803821240339089983L;
	private String id;// 主键
	private String host_id;// 物理主机id
	private String arrow_uuid;// 云主机id
	private String load_balance_uuid;// 负载均衡实例uuid
	private String rule_uuid;//规则uuid
	private double bin;// 入站流量（下行）
	private double bout;//出站流量（上行）
	private int rate;// 新建连接数
	private int scur;// 新建连接数
	private Long create_time;//创建时间
	private String time_stamp;// 时间戳
	private String role;//角色，主备
	private Integer handled;//是否已经进行过数据处理

	public LBInfoMonitor() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getArrow_uuid() {
		return arrow_uuid;
	}

	public void setArrow_uuid(String arrow_uuid) {
		this.arrow_uuid = arrow_uuid;
	}

	public String getLoad_balance_uuid() {
		return load_balance_uuid;
	}

	public void setLoad_balance_uuid(String load_balance_uuid) {
		this.load_balance_uuid = load_balance_uuid;
	}

	public double getBin() {
		return bin;
	}

	public void setBin(double bin) {
		this.bin = bin;
	}

	public double getBout() {
		return bout;
	}

	public void setBout(double bout) {
		this.bout = bout;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getScur() {
		return scur;
	}

	public void setScur(int scur) {
		this.scur = scur;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getRule_uuid() {
		return rule_uuid;
	}

	public void setRule_uuid(String rule_uuid) {
		this.rule_uuid = rule_uuid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getHandled() {
		return handled;
	}

	public void setHandled(Integer handled) {
		this.handled = handled;
	}
}
