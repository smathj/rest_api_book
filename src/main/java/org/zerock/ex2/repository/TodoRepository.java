package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex2.dto.TodoDTO;
import org.zerock.ex2.entity.TodoEntity;
import org.zerock.ex2.repository.search.TodoSearch;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {

//    @Query("select t from TodoEntity t")
    @Query(
            value = "select * from tbl_todos t",
            countQuery = "select count(*) from tbl_todos",
            nativeQuery = true
    )
    Page<TodoEntity> listAll(Pageable pageable);


    @Query("select t from TodoEntity t where t.title like %:keyword% and t.mno > 0 order by t.mno desc")
    Page<TodoEntity> listOfTitle(@Param("keyword") String keyword, Pageable pageable);


    @Query("select t from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO(@Param("mno") Long mno);

    @Query("select new org.zerock.ex2.dto.TodoDTO(t) from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO2(@Param("mno") Long mno);




}
