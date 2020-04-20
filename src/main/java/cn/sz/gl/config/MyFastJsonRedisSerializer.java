package cn.sz.gl.config;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MyFastJsonRedisSerializer<T> implements RedisSerializer<T> {

	private Class<T> cls;
	
	public MyFastJsonRedisSerializer(Class<T> cls) {
		this.cls = cls;
	}
	
	
	@Override
	public byte[] serialize(T t) throws SerializationException {
		if(t==null) {
			return new byte[0];
		}
		
		//这里需要把传入的java对象t转变成json字符串
		//考虑到将来从缓存中获取数据的时候，需要把获取的json数据转变成对应的java对象
		//到时候转换的时候，需要指定转换成哪一个类型的java对象，这里需要把类型保存到json字符串中
		//SerializerFeature.WriteClassName其实是一个枚举
		String jsonStr = JSON.toJSONString(t,SerializerFeature.WriteClassName);
		System.out.println("jsonStr:"+jsonStr);
		return jsonStr.getBytes(Charset.forName("utf-8"));
	}

	@Override
	public T deserialize(byte[] bs) throws SerializationException {
		if(bs==null||bs.length<=0) {
			return null;
		}
		String str = new String(bs,Charset.forName("utf-8"));
		return JSON.parseObject(str, cls);
	}

}
