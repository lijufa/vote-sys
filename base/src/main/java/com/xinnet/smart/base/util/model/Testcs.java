package com.xinnet.smart.base.util.model;

import java.util.ArrayList;
import java.util.List;

public class Testcs {
	public static void main(String[] args) {
		System.out.println("begin sh remote_ha.sh!!!!!---------------");
		try {
			StringBuffer cmd = new StringBuffer();
			cmd.append("sh");
			cmd.append("|");
			cmd.append("/remote_ha.sh");
			cmd.append("|");
			cmd.append("/root/.ssh/lb");//key
			cmd.append("|");
			cmd.append("189.1.1.10");//管理网ip managerNetIp
			cmd.append("|");
			cmd.append("\"active=");
			cmd.append("start");
			cmd.append(" ");
			//
			cmd.append("virtual_ipaddress=");
			cmd.append("189.1.1.10");//虚拟ip
			cmd.append(" ");
			//
			cmd.append("slb_name=");
			cmd.append("web_proxy");//负载均衡名字
			cmd.append(" ");
			//
			cmd.append("balance=");
			cmd.append("wrr");//负载方式  轮循等
			cmd.append(" ");
			//
			cmd.append("mode=");
			cmd.append("http");//协议名字
			cmd.append(" ");
			//
			cmd.append("front_port=");
			cmd.append("80");
			cmd.append(" ");
			//
			cmd.append("backup_port=");
			cmd.append("80");
			cmd.append(" ");
			//
			cmd.append("cookie=");
			cmd.append("on");
			cmd.append(" ");
			//
			cmd.append("cookie_name=");
			cmd.append("test");
			cmd.append(" ");
			//
			cmd.append("contimeout=");
			cmd.append("20");
			cmd.append(" ");
			//
			cmd.append("check=");
			cmd.append("on");
			cmd.append(" ");
			//
			cmd.append("inter=");
			cmd.append("2");
			cmd.append(" ");
			//
			cmd.append("timeout_check=");
			cmd.append("3");
			cmd.append(" ");
			//
			cmd.append("rise=");
			cmd.append("4");
			cmd.append(" ");
			//
			cmd.append("fall=");
			cmd.append("2");
			cmd.append(" ");
			//
			cmd.append("server_opt=");
			cmd.append("add");
			cmd.append(" ");
			//
			cmd.append("server=rs:aa1,ip:1.2.2.1,wg:2 server=rs:ac,ip:1.2.2.3,wg:2" + "\"");
			System.out.println(cmd.toString() + ")参数");
			String[] cmdArry = cmd.toString().split("\\|");
			List<String> cmds = new ArrayList<String>(cmdArry.length);
			for (String command : cmdArry) {
				cmds.add(command);
			}
			System.out.println(cmds.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
