package com.xinnet.controller;

import com.xinnet.entity.BookBean;
import com.xinnet.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 实例
 * @author Administrator
 * @date 2019年1月16日 下午8:01:17
 */
@Controller
@RequestMapping("vote")
public class VoteController {
	@Resource(name="bookService")
	private BookService bookService;

	@RequestMapping(value = "{page}/indexInfo")
	public ModelAndView bookList(@PathVariable("page")Integer page, Integer limit,String name) {
        BookBean where=new BookBean();
	    if (!"".equals(name)){
            where.setName(name);
        }
        List<BookBean> list=bookService.selectAll(page,5,where);
	    Long  total=bookService.selectAllNum(where);
		ModelAndView view = new ModelAndView();
		view.setViewName("list");
		view.addObject("list", list);
        view.addObject("number",total/5+1);
		return view;
	}
}
