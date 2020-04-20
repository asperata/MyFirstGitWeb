package cn.sz.gl.dao;

import cn.sz.gl.pojo.Users;

public interface IUserDAO {

	public Users findByNameAndPwd(Users user);
	
	public Users findUserById(Integer id);
}
