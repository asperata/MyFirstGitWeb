package cn.sz.gl.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilters {
	
	@Autowired
	private Filter myFilter;

	public FilterRegistrationBean<Filter> filterRegist(){
		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<Filter>();
		fr.setFilter(myFilter);
		fr.addUrlPatterns("/*");
		return fr;
	}
}
