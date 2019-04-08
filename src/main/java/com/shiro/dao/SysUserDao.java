package com.shiro.dao;

import org.springframework.data.repository.CrudRepository;

import com.shiro.entity.SysUser;

public interface SysUserDao extends CrudRepository<SysUser, Long> {

	SysUser findByUsername(String username);

	SysUser findByUid(int uid);

	void deleteByUid(int uid);
}