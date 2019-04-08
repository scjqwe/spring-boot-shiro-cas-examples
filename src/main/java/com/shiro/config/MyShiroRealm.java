package com.shiro.config;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.shiro.entity.SysPermission;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysUser;
import com.shiro.service.UserService;

public class MyShiroRealm extends CasRealm {

	@Resource
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser user = userService.getUserByUsername(username);
		for (SysRole role : user.getRoleList()) {
			authorizationInfo.addRole(role.getRole());
			for (SysPermission p : role.getPermissions()) {
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
		String username = (String) authc.getPrincipals().getPrimaryPrincipal();
		SysUser user = userService.getUserByUsername(username);
		if (user == null) {// 用户不存在
			return null;
		} else if (2 == user.getState()) {// 用户被锁定
			return null;
		}
		return authc;
	}
}