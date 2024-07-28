package com.example.springbootjdbc.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_msg")
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @Column(name = "contact_id")
    private int contactId;

    @NotNull(message = "Name must not be blank")
    @Size(min=3,message = "Name must be of min 3 characters")
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobile;

    @NotBlank(message = "Message must not be empty")
    private String message;

    private String status;
}
