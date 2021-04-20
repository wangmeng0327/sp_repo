package cn.tedu.sp03.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 只有添加了@RefreshScope的注解的Bean实例才
 * 会被注入新的配置数据
 * @author wm
 *
 */


@RefreshScope
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	/**
	 * yml配置的用户数据，json字符串注入到
	 * 动态刷新时，可以更新配置数据
	 */
	@Value("${sp.user-service.users}")
	private String userJson;
	
	@Override
	public User getUser(Integer id) {
		log.info("users json string : "+userJson);
		List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});
		for (User u : list) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		
		return new User(id, "name-"+id, "pwd-"+id);
	}

	/**
	 * 增加用户积分
	 */
	@Override
	public void addScore(Integer id, Integer score) {
		// 这里增加积分
		log.info("user "+id+" - 增加积分 "+score);
	}

}
