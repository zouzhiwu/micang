package com.game.helper;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class JsonMapper {
	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
	private static ObjectMapper mapper;
	  private static JsonMapper _instance = new JsonMapper();

	  private JsonMapper() {
	    mapper = getMapper();

	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

	    // 允许出现特殊字符和转义符
	    mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

	    // 允许出现单引号
	    mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
	  }

	  /**
	   * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	   * 
	   * 如需读取集合如List/Map, 且不是List<String>这种简单类型时使用如下语句,使用後面的函数.
	   */
	  public <T> T fromJson(String jsonString, Class<T> clazz) {
	    if (StringUtils.isEmpty(jsonString)) {
	      return null;
	    }

	    try {
	      return mapper.readValue(jsonString, clazz);
	    } catch (IOException e) {
	      logger.warn("parse json string error:" + jsonString, e);
	      return null;
	    }
	  }

	  /**
	   * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	   * 
	   * 如需读取集合如List/Map, 且不是List<String>时,
	   * 先用constructParametricType(List.class,MyBean.class)构造出JavaTeype,再调用本函数.
	   */
	  @SuppressWarnings("unchecked")
	  public <T> T fromJson(String jsonString, JavaType javaType) {
	    if (StringUtils.isEmpty(jsonString)) {
	      return null;
	    }

	    try {
	      return (T) mapper.readValue(jsonString, javaType);
	    } catch (IOException e) {
	      logger.warn("parse json string error:" + jsonString, e);
	      return null;
	    }
	  }

	  /**
	   * 构造泛型的Type如List<MyBean>, Map<String,MyBean>
	   */
	  public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
	    return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	  }

	  /**
	   * 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	   */
	  public String toJson(Object object) {

	    try {
	      return mapper.writeValueAsString(object);
	    } catch (IOException e) {
	      logger.warn("write to json string error:" + object, e);
	      return null;
	    }
	  }

	  /**
	   * 输出JSONP格式数据.
	   */
	  public String toJsonP(String functionName, Object object) {
	    return toJson(new JSONPObject(functionName, object));
	  }

	  /**
	   * 取出Mapper做进一步的设置或使用其他序列化API.
	   */
	  public static ObjectMapper getMapper() {
	    if (mapper == null) {
	      mapper = new ObjectMapper();
	    }
	    return mapper;
	  }

	  /**
	   * 获取实例
	   * 
	   * @return
	   */
	  public static JsonMapper getInstance() {
	    return _instance;
	  }
}
