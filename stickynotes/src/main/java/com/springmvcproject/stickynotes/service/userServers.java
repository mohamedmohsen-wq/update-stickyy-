package com.springmvcproject.stickynotes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvcproject.stickynotes.model.dto.responsSendDto;
import com.springmvcproject.stickynotes.model.dto.responsUserDTO;
import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.model.mapper.NoteMapper;
import com.springmvcproject.stickynotes.repository.NoteRepo;



@Service
public interface userServers {
	List<responsUserDTO> findAll();

	List<UserEntity> getAllUsers();
	
	List<responsSendDto> findAllsend();



}
