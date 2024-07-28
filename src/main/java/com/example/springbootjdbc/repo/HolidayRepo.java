package com.example.springbootjdbc.repo;

import com.example.springbootjdbc.data.Holidays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepo {

    private final JdbcTemplate template;

    @Autowired
    public HolidayRepo(JdbcTemplate template) {
        this.template = template;
    }

    public List<Holidays> findAllHolidays(){
        String sql ="select * from holidays";
        return template.query(sql,new BeanPropertyRowMapper<>(Holidays.class));
    }
}
