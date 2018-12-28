package com.sbt.springBoot.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbt.springBoot.business.util.DBMapper;
import com.sbt.springBoot.persistence.dao.user.UserRepository;
import com.sbt.springBoot.persistence.model.User;
import com.sbt.springBoot.persistence.model.internal.response.user.UserRes;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DBMapper dbMapper;

	@Transactional(readOnly = true)
	public User findUserById(long id) {
		return userRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User findUserByName(String name) {
		return userRepository.findByUserName(name);
	}

	@Transactional
	public User createUser(User user) {
		if (existUser(user.getUserName()).equals(null))
			return userRepository.save(user);

		return null;
	}

	@Transactional(readOnly = true)
	public User existUser(String name) {
		return userRepository.existsByUserName(name);
	}

	public List<UserRes> searchUserNameAndTaskTitle(String userName, String taskTitle) {
		List<Object[]> mapper = userRepository.findAllUserAndTask(userName, taskTitle);
		return dbMapper.mapperSearchUserNameAndTaskTitle(mapper);
	}

}