package cn.sz.gl.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
@Configuration
public class MyRedisConfig extends CachingConfigurerSupport {

	@Autowired
	private RedisConnectionFactory factory;
	
	@Bean
	public RedisTemplate<String, Object> rt(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String,Object>();
		//指定key的序列化的类型为String类型
		redisTemplate.setKeySerializer(new StringRedisSerializer(Charset.forName("gbk")));
		
		//指定value序列化的方式
		MyFastJsonRedisSerializer<Object> fr = new MyFastJsonRedisSerializer<Object>(Object.class);
		redisTemplate.setValueSerializer(fr);
		redisTemplate.setConnectionFactory(factory);
		//把指定包里面的类型添加到白名单，这样就可以实现序列化和反序列化了
		ParserConfig.getGlobalInstance().addAccept("cn.sz.gl.pojo");
		return redisTemplate;
	}
}
