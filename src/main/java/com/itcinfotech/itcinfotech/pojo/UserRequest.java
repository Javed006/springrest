package com.itcinfotech.itcinfotech.pojo;

import lombok.Data;

@Data
public class UserRequest {
    private int pageSize;
    private int pageNumber;
    private String[] sortingFieldsOrder;

}
