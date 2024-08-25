package com.springmvcproject.stickynotes.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvcproject.stickynotes.model.dto.responsSendDto;
import com.springmvcproject.stickynotes.model.dto.responsUserDTO;
import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.service.NoteService;
import com.springmvcproject.stickynotes.service.StickyNoteService;
import com.springmvcproject.stickynotes.service.userServers;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final userServers userService;

    @GetMapping("")
    public String findAll(Model model) {
    List<responsUserDTO>users=	this.userService.findAll();
    model.addAttribute("users", users);
    	return"users";
    }
    
    
    @GetMapping("/user")
    public String findAllsend(Model model) {
        List<responsSendDto> userssend = this.userService.findAllsend();
        model.addAttribute("userssend", userssend);
        return "userssend"; 
    }
    
//    <a th:href="@{'/note/delete/' + ${sticky.id}}" class="btn btn-sm btn-danger">send</a>
}
