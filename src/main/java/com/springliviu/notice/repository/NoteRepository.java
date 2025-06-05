package com.springliviu.notice.repository;

import com.springliviu.notice.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // No need to write anything — basic CRUD methods are inherited
}


// @Repository tells Spring this interface handles database operations
// JpaRepository provides CRUD methods: save(), findById(), findAll(), deleteById() etc.
// <Note, Long> — Note is the entity type, Long is the type of the primary key
