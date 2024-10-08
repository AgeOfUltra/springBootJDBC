package com.example.springbootjdbc.data;

import lombok.Data;

@Data
public class Holidays extends  BaseEntity{
    private String day;
    private String reason;
    private Type type;

    public enum Type {
        FESTIVAL,FEDERAL
    }
}
