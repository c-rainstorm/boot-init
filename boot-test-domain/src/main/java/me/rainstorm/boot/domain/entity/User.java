package me.rainstorm.boot.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chen
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 421323423L;

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