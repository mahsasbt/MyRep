package com.sbt.springBoot.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbt.springBoot.business.dp.TaskFactory;
import com.sbt.springBoot.persistence.dao.task.TaskRepository;
import com.sbt.springBoot.persistence.model.Task;
import com.sbt.springBoot.persistence.model.internal.request.task.TaskPage;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskFactory taskfactory;

	@Transactional(readOnly = true)
	public Task getAllTask(long id) {
		return taskRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Task> pageAllTask(TaskPage taskPage, String URL) {
		PageRequest pageRequest = taskfactory.taskPaginationFactory(taskPage, URL);

		if (pageRequest != null) {
			Page<Task> pTask = taskRepository.findAll(pageRequest);
			return pTask.getContent();
		} else
			return null;
	}
}