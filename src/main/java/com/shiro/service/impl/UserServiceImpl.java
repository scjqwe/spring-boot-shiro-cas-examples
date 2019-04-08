package com.shiro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiro.dao.SysUserDao;
import com.shiro.entity.SysUser;
import com.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private SysUserDao sysUserDao;

	@Override
	public void addUser(SysUser user) {
		sysUserDao.save(user);
	}

	@Override
	public void updateUser(SysUser user) {
		sysUserDao.save(user);
	}

	@Override
	public void deleteUser(int uid) {
		sysUserDao.deleteByUid(uid);
	}

	@Override
	public SysUser getUserByUid(int uid) {
		return sysUserDao.findByUid(uid);
	}

	@Override
	public SysUser getUserByUsername(String username) {
		return sysUserDao.findByUsername(username);
	}

}