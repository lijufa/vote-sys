package com.xinnet.smart.vo;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 并发连接数：sbc；rps；QPS（TPS）= 并发数/平均响应时间
 * 每秒响应HTTP请求数：RPS（个/秒）；平均响应时间：RT（秒）；并发 = rps * 响应时间
 * @author lijufa
 * @date 2017年9月4日 下午2:38:07
 */
public class DBInfoMonitor implements Serializable {
	private static final long serialVersionUID = 803821240339089983L;
	private String id;// 主键
	private long host_id;// 物理主机id
	@JsonProperty("vid")
	private long arrow_uuid;// 云主机id
	private long instance_uuid;// 云数据库实例uuid
	private double iops;//3
	private String time_stamp;// 时间戳
	private String role;//角色，主备
	private Integer handled;//是否已经进行过数据处理
	private double total_used;//3 总空间使用量
	private double data_used;//4  数据空间使用量
	private double system_used;//5  系统空间使用量
	private double threads_connected;//6  并发连接数
	private double tps;//1
	private double qps;//2
	@JsonProperty("create_time")
	private long createTime;//14   底层产生数据的时间

	public DBInfoMonitor() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getHost_id() {
		return host_id;
	}

	public void setHost_id(long host_id) {
		this.host_id = host_id;
	}

	public long getArrow_uuid() {
		return arrow_uuid;
	}

	public void setArrow_uuid(long arrow_uuid) {
		this.arrow_uuid = arrow_uuid;
	}

	public long getInstance_uuid() {
		return instance_uuid;
	}

	public void setInstance_uuid(long instance_uuid) {
		this.instance_uuid = instance_uuid;
	}

	public double getTotal_used() {
		return total_used;
	}

	public void setTotal_used(double total_used) {
		this.total_used = total_used;
	}

	public double getData_used() {
		return data_used;
	}

	public void setData_used(double data_used) {
		this.data_used = data_used;
	}

	public double getSystem_used() {
		return system_used;
	}

	public void setSystem_used(double system_used) {
		this.system_used = system_used;
	}

	public double getIops() {
		return iops;
	}

	public void setIops(double iops) {
		this.iops = iops;
	}

	public double getTps() {
		return tps;
	}

	public void setTps(double tps) {
		this.tps = tps;
	}

	public double getQps() {
		return qps;
	}

	public void setQps(double qps) {
		this.qps = qps;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
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

	public double getThreads_connected() {
		return threads_connected;
	}

	public void setThreads_connected(double threads_connected) {
		this.threads_connected = threads_connected;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
