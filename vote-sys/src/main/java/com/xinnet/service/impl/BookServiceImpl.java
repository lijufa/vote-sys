package com.xinnet.service.impl;

import com.xinnet.dao.AccountDao;
import com.xinnet.dao.BookDao;
import com.xinnet.entity.AccountBean;
import com.xinnet.entity.BookBean;
import com.xinnet.interceptor.mybatis.domain.PageBounds;
import com.xinnet.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="bookService")
public class BookServiceImpl implements BookService{
    @Resource(name="bookDao")
    private BookDao bookDao;
    @Resource(name="accountDao")
    private AccountDao accountDao;

    /**
     * 查询所有图书
     * @return
     */
    @Override
    public List<BookBean> selectAll(Integer page, Integer limit,BookBean where){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(page);
        pageBounds.setLimit(limit);
        List<BookBean> dataList = bookDao.selectPaged(where, pageBounds);
        return dataList;
    }
    public Long selectAllNum(BookBean where){
        return bookDao.total(where);
    }
    /**
     * 新增图书
     * @param bookBean
     */
    @Override
    public void addBook(BookBean bookBean){

        bookDao.insert(bookBean);
    }
    /**
     * 修改图书
     * @param bookBean
     */
    @Override
    public void updateBook(BookBean bookBean){

        bookDao.update(bookBean);
    }
    /**
     * 修改图书
     * @param bookBean
     */
    @Override
    public void deleteBook(BookBean bookBean){
        bookDao.delete(bookBean.getId());
    }
    /**
     * 图书详情
     * @param id
     */
    @Override
    public BookBean selectBookBean(Long id){
        return bookDao.selectById(id);
    }
    /**
     * 新增账号
     * @param accountBean
     */
    @Override
    public void addAccount(AccountBean accountBean){
        accountDao.insert(accountBean);
    }
    @Override
    public AccountBean getAccount(AccountBean accountBean){
        return accountDao.selectOne(accountBean);
    }

}