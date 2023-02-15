package com.elastic.poc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SearchResponse<T> {
    @Builder.Default
    private List<T> records = Collections.emptyList();
    @Builder.Default
    private Integer currentPage=0;
    @Builder.Default
    private Integer size=0;
    @Builder.Default
    private Integer numberOfElements=0;
    @Builder.Default
    private Long totalNumberOfElements=0L;
    @Builder.Default
    private Integer totalPages=0;

}
