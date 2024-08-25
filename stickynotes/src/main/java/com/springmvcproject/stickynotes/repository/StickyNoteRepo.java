package com.springmvcproject.stickynotes.repository;

import com.springmvcproject.stickynotes.model.entity.StickyNote;
import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.model.enums.StickyNoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StickyNoteRepo extends JpaRepository<StickyNote, Long> {
	List<StickyNote> findAllByIdUserAndStatus(Long iduser,StickyNoteStatus status);

    List<StickyNote> findByUserEntityUsername(String username);
	}
