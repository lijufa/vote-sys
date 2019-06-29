package com.xinnet.controller;

import com.xinnet.entity.AccountBean;
import com.xinnet.service.BookService;
import com.xinnet.smart.base.util.UJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import com.xinnet.smart.base.cache.ShardedJedisUtils;
/**
 * 实例
 * @author Administrator
 * @date 2019年1月16日 下午8:01:17
 */
@Controller
@RequestMapping("account")
public class AccountController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name="bookService")
	private BookService bookService;
	@Autowired
	private ShardedJedisUtils shardJedis;
	@RequestMapping(value = "register")
	public String register(String account,String password,HttpServletRequest request, HttpServletResponse response) {
		if (account==null||"".equals(account)){
			return "register";
		}else{
			//用户注册
			AccountBean accountBean=new AccountBean();
			accountBean.setAccount(account);
			accountBean.setPassWord(password);
			accountBean.setCratetime(new Date());
			bookService.addAccount(accountBean);
			return "redirect:/vote/1/indexInfo";
		}
	}
	@RequestMapping(value = "login")
	public String login(String account,String password,HttpServletRequest request, HttpServletResponse response) {
		//查询用户是否已经注册
		AccountBean accountBean=new AccountBean();
		accountBean.setAccount(account);
		accountBean.setPassWord(password);
		AccountBean selAccountBean=bookService.getAccount(accountBean);
		if (selAccountBean!=null){
			//   有此用户  / 从连接池中获取jedis对象
			String phone="17600208211";
			try {
				if (shardJedis != null) {
					if (shardJedis.hexists("token" + phone, phone)) {
						String hostJsonStr = shardJedis.hget("token" + phone, phone);
					} else {
						// 查询智卓刷新token接口，并把内容放到缓存
						if (accountBean != null) {
							shardJedis.hset("token" + phone, phone, UJson.toString(accountBean));
						}
						shardJedis.expire("token" + phone, 24 * 60 * 60);
					}
				} else {
					// 查询数据库，并把内容放到缓存
				}
			} catch (Throwable e) {

			} finally {
			}
		}else{
			//用户  不存在  添加用户
		}
		return "redirect:/vote/1/indexInfo";
	}
	@RequestMapping(value = "send_verify_code", method = RequestMethod.POST)
	@ResponseBody
	public void sendVerifyCode(HttpServletRequest request, HttpServletResponse response){

	}
	@RequestMapping(value = "verify_code", method = RequestMethod.POST)
	@ResponseBody
	public void verifyCode(HttpServletRequest request, HttpServletResponse response){

	}
}
