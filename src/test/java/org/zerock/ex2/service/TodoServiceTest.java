package org.zerock.ex2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.TodoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle("Test Todo");
        todoDTO.setWriter("user00");
        todoDTO.setDueDate(LocalDate.of(2025,12,23));

        TodoDTO resultDTO = todoService.register(todoDTO);

        System.out.println(resultDTO);

    }

    @Test
    public void testRead() {

        Long mno = 103L;    // 현재 DB에 존재하는 번호

        TodoDTO resultDTO = todoService.read(mno);

        System.out.println(resultDTO);

    }

    @Test
    public void testRemove() {
        Long mno = 3L;
        todoService.remove(mno);
    }

    @Test
    public void testModify() {

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setMno(102L);
        todoDTO.setTitle("수정된 제목");
        todoDTO.setWriter("fix1");
        todoDTO.setDueDate(LocalDate.now());
        todoService.modify(todoDTO);

    }

    @Test
    public void testList() {

        // page 1, size 10
        PageRequestDTO pageRequestDTO = new PageRequestDTO(10, 10);

        Page<TodoDTO> result = todoService.getList(pageRequestDTO);

        System.out.println("PREV: " + result.previousPageable());
        System.out.println("NEXT: " + result.nextPageable());
        System.out.println("TOTAL: " + result.getTotalElements());

        result.getContent().forEach(todoDTO -> System.out.println(todoDTO));
        Pageable pageable = result.nextPageable();


        System.out.println("Page<TodoDTO> 로 다음 페이지 확인 할때 result.hasNext() = " + result.hasNext());
        System.out.println("Pageable 로 현재 페이지가 존재하는지 확인 할때 pageable.isPaged() = " + pageable.isPaged());

        if (pageable.isPaged()) {

            System.out.println("pageable.getPageNumber() : " + pageable.getPageNumber());
            System.out.println("pageable.getPageSize() : " + pageable.getPageSize());


            System.out.println("------------------------------------------------------------");


            PageRequestDTO pageRequestDTO2 = new PageRequestDTO(pageable.getPageNumber() + 1, pageable.getPageSize());
            Page<TodoDTO> result2 = todoService.getList(pageRequestDTO2);
            result2.getContent().forEach(todoDTO -> System.out.println(todoDTO));
        }

//        todoService.getList(pageRequestDTO);

    }

}
