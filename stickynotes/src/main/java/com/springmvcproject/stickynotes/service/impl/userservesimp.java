package com.springmvcproject.stickynotes.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springmvcproject.stickynotes.config.CustomUserDetails;
import com.springmvcproject.stickynotes.model.dto.responsSendDto;
import com.springmvcproject.stickynotes.model.dto.responsUserDTO;
import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.model.mapper.NoteMapper;
import com.springmvcproject.stickynotes.model.mapper.StickyNoteMapper;
import com.springmvcproject.stickynotes.model.mapper.UserMapper;
import com.springmvcproject.stickynotes.repository.NoteRepo;
import com.springmvcproject.stickynotes.repository.StickyNoteRepo;
import com.springmvcproject.stickynotes.repository.UserRepo;
import com.springmvcproject.stickynotes.service.userServers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userservesimp implements userServers {
	  private final UserRepo repo;
	    private final UserMapper mape;
		@Override
		public List<responsUserDTO> findAll() {
			List<UserEntity> find= this.repo.findAll();
			List<responsUserDTO> findAll=this.mape.resp(find);
			
			return findAll;
		}
		
		 public List<UserEntity> getAllUsers() {
		        return repo.findAll();
		    }

		@Override
		public List<responsSendDto> findAllsend() {
			List<UserEntity> find= this.repo.findAll();
			List<responsSendDto> findAllsend=this.mape.send(find);
			
			return findAllsend;
		}
		
		    

}
