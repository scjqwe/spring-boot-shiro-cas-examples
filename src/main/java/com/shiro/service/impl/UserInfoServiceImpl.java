package com.shiro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiro.dao.UserInfoDao;
import com.shiro.entity.UserInfo;
import com.shiro.service.UserService;

@Service
public class UserInfoServiceImpl implements UserService {
	
	@Resource
	private RoleService roleService;

	@Resource
	private UserService userService;

	@Override
	public UserInfo findByUsername(String username) {
		System.out.println("UserInfoServiceImpl.findByUsername()");
		return userInfoDao.findByUsername(username);
	}
}