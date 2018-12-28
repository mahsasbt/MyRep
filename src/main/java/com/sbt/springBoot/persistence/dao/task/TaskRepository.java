package com.sbt.springBoot.persistence.dao.task;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sbt.springBoot.persistence.model.Task;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{

}