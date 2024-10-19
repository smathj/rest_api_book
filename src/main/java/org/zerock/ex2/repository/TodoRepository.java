package org.zerock.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex2.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
