package com.shiro.service;

import com.shiro.entity.UserInfo;

public interface UserInfoService {
	/** 通过username查找用户信息; */
	public UserInfo findByUsername(String username);
}