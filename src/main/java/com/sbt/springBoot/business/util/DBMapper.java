package com.sbt.springBoot.business.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sbt.springBoot.persistence.model.internal.response.user.UserRes;

@Component
public class DBMapper {
	public List<UserRes> mapperSearchUserNameAndTaskTitle(List<Object[]> objectList) {
		List<UserRes> userResList = new ArrayList<>();
		for (Object[] obj : objectList) {
			UserRes userRes = new UserRes();
			userRes.setUserName(obj[0].toString());
			userRes.setTaskTitle(obj[1].toString());
			userResList.add(userRes);
		}
		return userResList;
	}
}