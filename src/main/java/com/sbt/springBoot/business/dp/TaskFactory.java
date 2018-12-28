package com.sbt.springBoot.business.dp;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.sbt.springBoot.persistence.model.internal.request.task.TaskPage;
import com.sbt.springBoot.view.controller.RestApiController;

@Component
public class TaskFactory {

	public PageRequest taskPaginationFactory(TaskPage taskPage, String URL) {
		if (URL.equals(RestApiController.TASK_WITHOUT_SORT)) {
			return new PageRequest(taskPage.getPageNumber(), taskPage.getSize());
		} else if (URL.equals(RestApiController.TASK_WITH_SORT)) {
			return new PageRequest(taskPage.getPageNumber(), taskPage.getSize(), taskPage.getSortDirection(),
					taskPage.getSortColumn());
		} else
			return null;
	}
}