package me.rainstorm.boot.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private LocalDateTime birthday;

    private Integer gender;

    private String createdBy;

    private LocalDateTime createdOn;

    private String updatedBy;

    private LocalDateTime updatedOn;

    private String remark;
}