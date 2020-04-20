package cn.sz.gl.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.sz.gl.dao.IUserDAO;

@Configuration
public class SpringAndMvcConfigger {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/*@Bean
	public IUserDAO userdao() throws Exception {
		MapperFactoryBean<IUserDAO> factory = new MapperFactoryBean<IUserDAO>();
		factory.setSqlSessionFactory(sqlSessionFactory);
		factory.setMapperInterface(IUserDAO.class);
		return factory.getObject();
	}*/
	
	@Bean
	public MapperScannerConfigurer configurer() {
		MapperScannerConfigurer conf = new MapperScannerConfigurer();
		conf.setSqlSessionFactoryBeanName("sqlSessionFactory");
		conf.setBasePackage("cn.sz.gl.dao");
		return conf;
	}
	
}
