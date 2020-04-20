package cn.sz.gl.service.impl;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.sz.gl.dao.IUserDAO;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IUserService;
@Service
//@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userdao;
	@Resource(name="rt")
	private RedisTemplate<String, Users> rt;
	
	//@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRES_NEW)
	@Override
	public Users islogin(Users user) {
		if(user==null||user.getLoginname()==null||user.getLoginpwd()==null) {
			return null;
		}
		
		if(user.getLoginname().equals("")||user.getLoginpwd().equals("")) {
			return null;
		}
		return userdao.findByNameAndPwd(user);
	}

	@Override
	public Users findbyid(Integer userid) {
		//rt.hasKey(key)//根据key判断redis数据库中是否存在对应的key
		//根据key来获取redis数据库中的value(实际上，这里就是用来获取对应的users对象)
		Users u = rt.opsForValue().get("user:"+userid);
		//如果能够从redis获取到对象，那么直接返回
		if(u!=null) {
			System.out.println("在redis中找到了需要的数据");
			return u;
		}
		System.out.println("redis中没有需要的数据");
		//此时redis里面没有找到对应的user对象，只能从mysql数据库查出来
		u = userdao.findUserById(userid);
		//从mysql数据库查出来以后，还需要把查询到的数据，存入redis
		rt.opsForValue().set("user:"+userid, u);
		return u;
	}
	


}
