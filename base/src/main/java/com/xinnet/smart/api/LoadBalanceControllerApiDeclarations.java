package com.xinnet.smart.api;

/**
 * 负载均衡API常量表
 */
public interface LoadBalanceControllerApiDeclarations {
	String LB_ARROW_CREATE = "loadbalance/create";
	String LB_INSTANCE_DELETE = "loadbalance/delete";
	String LB_VALIDATE_RECONFIG = "loadbalance/reconfigValidateResource";//变更负载均衡实例校验资源
	String LB_INSTANCE_RECONFIG = "loadbalance/reconfigLbInstance";//变更负载均衡实例配置
	String LB_INSTANCE_MASTERORSLAVESTATUS = "loadbalance/updateMasterOrSlaveStatus";//更新负载均衡对应的虚机主备状态
	String LB_API_INSTANCE_RECONFIG_CALLBACK = "slbInstances/reconfigCallback";//manager项目地址
	String LB_RULE_CONFIG = "loadbalance/monitor_rule/config";
	String LB_RULE_CONFIG_ROLLBACK = "loadbalance/monitor_rule/configRollback";
	String LB_INSTANCE_TYPE_REPORTING = "load_balancer/type_reporting";//负载均衡变更上报回调
	String LB_INSTANCE_HEALTH_REPORTING = "load_balancer/health_reporting";//负载均衡健康检查上报回调
}
