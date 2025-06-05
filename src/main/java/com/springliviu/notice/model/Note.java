package com.springliviu.notice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
// @Entity marks this class as a JPA entity to be mapped to a database table
// @Table(name = "notes") specifies the table name in the database
// @Id is the primary key
// @GeneratedValue sets auto-increment strategy
// @NotBlank ensures the title field is not empty
// createdAt and updatedAt are timestamps
// @PrePersist and @PreUpdate automatically set timestamps on insert/update