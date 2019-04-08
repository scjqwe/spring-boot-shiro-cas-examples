package com.shiro.service;

import com.shiro.entity.SysUser;

public interface UserService {
	void addUser(SysUser user);

	void deleteUser(int uid);

	void updateUser(SysUser user);

	SysUser getUserByUsername(String username);

	SysUser getUserByUid(int uid);
}