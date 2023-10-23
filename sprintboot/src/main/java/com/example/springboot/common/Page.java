package com.example.springboot.common;

import lombok.Data;

import java.util.List;

/**
 * @Classname Page
 * @Description TODO
 * @Author zjj
 * @Date 10/23/23 1:02AM
 */
@Data
public class Page<T> {
    private Integer total;
    private List<T> list;
}
