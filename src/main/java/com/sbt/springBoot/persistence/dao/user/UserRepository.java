package com.sbt.springBoot.persistence.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbt.springBoot.persistence.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public User findByUserName(String userName);

	@Query("Select u From User u Where u.userName = :userName")
	public User existsByUserName(String userName);

	@Query("SELECT c.userName, p.taskTitle FROM User c JOIN c.taskList p Where c.userName LIKE CONCAT('%',:user_name,'%') and p.taskTitle LIKE CONCAT('%',:task_title,'%')")
	public List<Object[]> findAllUserAndTask(@Param("user_name") String userName, @Param("task_title") String taskTitle);
}