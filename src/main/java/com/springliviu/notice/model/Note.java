package com.springliviu.notice.model;

import jakarta.persistence.*; // JPA annotations for entity mapping
import jakarta.validation.constraints.NotBlank; // Validation annotation for non-empty fields
import lombok.*; // Lombok annotations for boilerplate code

import java.time.LocalDateTime; // Class for date-time values

@Entity // Marks this class as a JPA entity
@Table(name = "notes") // Maps the entity to the "notes" table
@Getter // Lombok: generates getters for all fields
@Setter // Lombok: generates setters for all fields
@NoArgsConstructor // Lombok: generates a no-args constructor
@AllArgsConstructor // Lombok: generates an all-args constructor
@Builder // Lombok: enables the builder pattern
public class Note {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // Owner of the note


    @Id // Marks the primary key field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses auto-incremented ID generation
    private Long id; // Unique identifier for each note

    @NotBlank(message = "Title is required") // Ensures title is not blank during validation
    private String title; // Title of the note

    private String content; // Optional content of the note

    private LocalDateTime createdAt; // Timestamp when the note was created

    private LocalDateTime updatedAt; // Timestamp when the note was last updated

    @PrePersist // Runs before the entity is inserted into the database
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Sets creation timestamp
        this.updatedAt = this.createdAt; // Sets initial update timestamp same as creation
    }

    @PreUpdate // Runs before the entity is updated in the database
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now(); // Updates the timestamp on modification
    }
}
