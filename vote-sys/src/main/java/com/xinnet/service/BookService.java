package com.xinnet.service;

import com.xinnet.entity.AccountBean;
import com.xinnet.entity.BookBean;

import java.util.List;

public interface BookService {
   public List<BookBean> selectAll(Integer page, Integer limit,BookBean where);
   public Long selectAllNum(BookBean where);
   public BookBean selectBookBean(Long id);
   public void addAccount(AccountBean accountBean);
   public AccountBean getAccount(AccountBean accountBean);
   public void addBook(BookBean bookBean);
   public void updateBook(BookBean bookBean);
   public void deleteBook(BookBean bookBean);
}