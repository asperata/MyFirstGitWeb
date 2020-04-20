package cn.sz.gl.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@PropertySource(value= {"classpath:myconn.properties"})
public class MybatisConnectionConfiger {

	@Value("${mydriver}")
	private String mydriver;
	@Value("${myurl}")
	private String myurl;
	@Value("${myuser}")
	private String myname;
	@Value("${mypwd}")
	private String mypwd;
	@Autowired
	private BasicDataSource dataSource;
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(mydriver);
		dataSource.setUrl(myurl);
		dataSource.setUsername(myname);
		dataSource.setPassword(mypwd);
		return dataSource;
	}
	
	@Bean
	//public SqlSessionFactory sqlSessionFactory(BasicDataSource dataSource) throws Exception {
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factory.setMapperLocations(resolver.getResources("classpath:cn/sz/gl/pojo/*.xml"));
		return factory.getObject();
	}
	
	
}
