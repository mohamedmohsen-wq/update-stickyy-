package com.springmvcproject.stickynotes.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springmvcproject.stickynotes.model.dto.responsSendDto;
import com.springmvcproject.stickynotes.model.dto.responsUserDTO;
import com.springmvcproject.stickynotes.model.entity.UserEntity;

@Mapper(componentModel = "spring")

public interface UserMapper {
	
List<responsUserDTO> resp(List<UserEntity> entiy);
List<UserEntity> entiy(List<responsUserDTO> resp);


List<responsSendDto> send(List<UserEntity> entiysend);
List<UserEntity> entiysend(List<responsSendDto> send);
}
