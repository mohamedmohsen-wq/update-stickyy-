package com.springmvcproject.stickynotes.model.dto;

import java.time.LocalDateTime;

import com.springmvcproject.stickynotes.model.enums.StickyNoteStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;

}
