package com.springmvcproject.stickynotes.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class responsUserDTO {
    private Long id;
	   private String username;
	    private String name;
	    private String role;

}
