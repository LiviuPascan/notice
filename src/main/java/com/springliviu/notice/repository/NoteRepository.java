package com.springliviu.notice.repository;

import com.springliviu.notice.model.Note; // Imports the Note entity
import org.springframework.data.jpa.repository.JpaRepository; // Base interface for JPA repositories
import org.springframework.stereotype.Repository; // Marks this interface as a Spring-managed repository bean

@Repository // Indicates that this interface is a Spring repository component
public interface NoteRepository extends JpaRepository<Note, Long> {
    // No additional methods required — basic CRUD operations are inherited from JpaRepository
}
