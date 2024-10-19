package org.zerock.ex2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 1;

    public Pageable getPageable(Sort sort) {

        // 0 인덱스가 1 페이지
//        int pageNum = page < 0 ? 1 : page - 1;

        // 사용자 N페이지 -----------> N-1 인덱스
        // 사용자가 1 이하 페이지 --------> 0 인덱스
        int pageNum = page <= 0 ? 0 : page - 1;
        int sizeNum = size <= 10 ? 10 : size;

        return PageRequest.of(pageNum, sizeNum, sort);

    }
}
