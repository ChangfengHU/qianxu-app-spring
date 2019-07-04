package com.qianxu.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.qianxu.mapper.UsersMapper;
import com.qianxu.pojo.ChatMsg;
import com.qianxu.pojo.FriendsRequest;
import com.qianxu.pojo.MyFriends;
import com.qianxu.pojo.Users;
import com.qianxu.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private Sid sid;


	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
		
		Users user = new Users();
		user.setUsername(username);
		
		Users result = usersMapper.selectOne(user);
		
		return result != null ? true : false;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String username, String pwd) {
		
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		
		criteria.andEqualTo("username", username);
		criteria.andEqualTo("password", pwd);
		
		Users result = usersMapper.selectOneByExample(userExample);
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Users saveUser(Users user) {
		
		String userId = sid.nextShort();

		user.setQrcode("");
		user.setId(userId);
		usersMapper.insert(user);
		
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Users updateUserInfo(Users user) {
		usersMapper.updateByPrimaryKeySelective(user);
		return queryUserById(user.getId());
	}

	@Override
	public Integer preconditionSearchFriends(String myUserId, String friendUsername) {
		return null;
	}

	@Override
	public Users queryUserInfoByUsername(String username) {
		return null;
	}

	@Override
	public void sendFriendRequest(String myUserId, String friendUsername) {

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private Users queryUserById(String userId) {
		return usersMapper.selectByPrimaryKey(userId);
	}

}
