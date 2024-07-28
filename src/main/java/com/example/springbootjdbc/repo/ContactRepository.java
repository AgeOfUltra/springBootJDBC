package com.example.springbootjdbc.repo;

import com.example.springbootjdbc.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {
    private final JdbcTemplate template;

    @Autowired
    public ContactRepository(JdbcTemplate template) {
        this.template = template;

    }

    public int saveContact(Message message){
        String sql = "insert into contact_msg (name,mobile,message,status,created_at,created_by) values (?,?,?,?,?,?)";
        return  template.update(sql,message.getName(),message.getMobile(),message.getMessage(),message.getStatus(),message.getCreatedAt(),message.getCreatedBy());
    }

    public List<Message> getMessageWithOpenStatus() {
        String sql = "select * from contact_msg where status='Open'";
        return template.query(sql, new BeanPropertyRowMapper<>(Message.class));
    }

    public int changeTicketStatus(int id,String status,String updatedBy) {
        String sql = "UPDATE CONTACT_MSG SET STATUS= ? ,UPDATED_BY= ? ,UPDATED_AT= ?  WHERE CONTACT_ID=?";
        return template.update(sql,status,updatedBy,Timestamp.valueOf(LocalDateTime.now()),id);
    }
}
