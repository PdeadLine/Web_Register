package com.github.user.service;

import com.github.user.dao.UserDao;
import com.github.user.domain.User;

public class UserService {
	private UserDao userdao=new UserDao();
	
	/**
	 *	注册功能
	 * @param user
	 * @throws UserException
	 */
	public void register(User user)throws UserException{
		/*
		 *1.使用用户去查询，如果返回null,则添加
		 *2.如果不是null，抛出异常 
		 *
		 */
		User _user=userdao.findByUsername(user.getUsername());
		if(_user!=null)throw new UserException("用户名:"+user.getUsername()+",已经被注册过了！");
		
		//否则，直接添加到数据库中
		userdao.add(user);
	}
	/**
	 * 登陆功能
	 * @param form
	 * @return
	 */
	public User login(User form)throws UserException {
		User user =userdao.findByUsername(form.getUsername());
		
		if(user==null)throw new UserException("用户名不存在！");
		
		if(!form.getPassword().equals(user.getPassword())){
			throw new UserException("密码错误！");
			
		}
		/**
		 * 返回数据库中查到的对象
		 */
		return user;
	}
	
}
