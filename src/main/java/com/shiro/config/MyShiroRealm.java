package com.shiro.config;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.shiro.entity.SysPermission;
import com.shiro.entity.SysRole;
import com.shiro.entity.UserInfo;
import com.shiro.service.UserService;

public class MyShiroRealm extends CasRealm {
	
	@Resource
	private UserService userInfoService;
	
	@Resource
	private UserService userInfoService;
	
	@Resource
	private UserService userInfoService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		for (SysRole role : userInfo.getRoleList()) {
			authorizationInfo.addRole(role.getRole());
			for (SysPermission p : role.getPermissions()) {
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
		String username = (String) authc.getPrincipals().getPrimaryPrincipal();
		UserInfo userInfo = userInfoService.findByUsername(username);
		if (userInfo == null) {
			return null;
		}
		return authc;
	}

}