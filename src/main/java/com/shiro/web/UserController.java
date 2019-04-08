package com.shiro.web;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.dao.SysUserDao;
import com.shiro.entity.SysUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private SysUserDao userDao;

	/**
	 * 用户查询
	 * 
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("user:view")
	public String listUser(Model model) {
		model.addAttribute("userList", userDao.findAll());
		return "user/list";
	}

	/**
	 * 用户添加
	 * 
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("user:add")
	public String addUser(Model model) {
		return "user/add";
	}

	/**
	 * 用户添加
	 * 
	 * @return
	 */
	@GetMapping("/addUser")
	@RequiresPermissions("user:add")
	public void addUserAjax(Model model, SysUser user) {
		userDao.save(user);
		model.addAttribute("code", "1");
	}

	/**
	 * 用户删除
	 * 
	 * @return
	 */
	@RequestMapping("/del")
	@RequiresPermissions("user:del")
	public void delUser(Model model, int uid) {
		userDao.deleteByUid(uid);
		model.addAttribute("code", "1");
	}
}