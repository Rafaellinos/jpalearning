package br.com.rafaellinos.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageableDomain<T> {

    private List<T> content;
    private Integer totalPages;
    private Integer totalElements;

}
