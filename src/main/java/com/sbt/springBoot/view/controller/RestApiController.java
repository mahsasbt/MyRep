package com.sbt.springBoot.view.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.sbt.springBoot.business.exception.UserNotFoundException;
import com.sbt.springBoot.business.service.TaskService;
import com.sbt.springBoot.business.service.UserService;
import com.sbt.springBoot.persistence.model.Task;
import com.sbt.springBoot.persistence.model.User;
import com.sbt.springBoot.persistence.model.internal.request.task.TaskPage;
import com.sbt.springBoot.persistence.model.internal.response.user.UserRes;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	public static final String TASK_WITHOUT_SORT = "/tasks";
	public static final String TASK_WITH_SORT = "/tasks/sort";

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	// Retrieve All Users
	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		Iterable<User> users = userService.findAll();
		if (Iterables.isEmpty(users))
			throw new UserNotFoundException("Not Find Any User");

		return new ResponseEntity<List<User>>(Lists.newArrayList(users), HttpStatus.OK);
	}

	// Retrieve Single User By ID
	@RequestMapping(value = "/userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByID(@Valid @PathVariable(value = "id", required = true) Long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findUserById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			throw new UserNotFoundException("User with " + id + " not found.");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Retrieve Single User By Name
	@RequestMapping(value = "/userName/{name}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByName(@Valid @PathVariable(value = "name", required = true) String name) {
		logger.info("Fetching User with Name {}", name);
		User user = userService.findUserByName(name);
		if (user == null) {
			logger.error("User with id {} not found.", name);
			throw new UserNotFoundException("Not Find Any User With Name Of=> " + name);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{userName}/{title}", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRes> getLikeUserNameAndTaskTitle(
			@Valid @PathVariable(value = "userName", required = true) String userName,
			@Valid @PathVariable(value = "title", required = true) String title) {
		return userService.searchUserNameAndTaskTitle(userName, title);
	}

	@RequestMapping(value = TASK_WITHOUT_SORT, method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Task>> getTaskPage(@Valid @RequestBody TaskPage taskPage) {
		List<Task> response = taskService.pageAllTask(taskPage, TASK_WITHOUT_SORT);
		return new ResponseEntity<List<Task>>(response, response != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = TASK_WITH_SORT, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<Task>> getTaskSortable(@Valid @RequestBody TaskPage taskPage) {
		if (!taskPage.equalsAdvance(null))
			taskPage.setSortDirection(taskPage.getSort().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC);

		List<Task> response = taskService.pageAllTask(taskPage, TASK_WITH_SORT);
		return new ResponseEntity<List<Task>>(response, response != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}