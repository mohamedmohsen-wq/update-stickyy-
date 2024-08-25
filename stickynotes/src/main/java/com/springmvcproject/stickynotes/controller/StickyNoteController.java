package com.springmvcproject.stickynotes.controller;

import com.springmvcproject.stickynotes.config.SecurityUtils;
import com.springmvcproject.stickynotes.model.dto.AddStickyNoteDTO;
import com.springmvcproject.stickynotes.model.dto.NoteDTO;
import com.springmvcproject.stickynotes.model.dto.StickyNoteDTO;
import com.springmvcproject.stickynotes.model.dto.UpdateStickyNoteDTO;
import com.springmvcproject.stickynotes.model.entity.StickyNote;
import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.service.NoteService;
import com.springmvcproject.stickynotes.service.StickyNoteService;
import com.springmvcproject.stickynotes.service.userServers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
public class StickyNoteController {
	 private final StickyNoteService stickyNoteService;
	    private final NoteService noteService;
	    private final userServers userService;
	    private final SecurityUtils securityUtils;
	    @Autowired
	    public StickyNoteController(StickyNoteService stickyNoteService, NoteService noteService, SecurityUtils securityUtils, userServers userService) {
	        this.stickyNoteService = stickyNoteService;
	        this.noteService = noteService;
			this.userService = userService;
	        this.securityUtils = securityUtils;
	    }

//    @GetMapping("")
//    public String home(Model model) {
//        Long idUser = securityUtils.getCurrentUserId(); // استدعاء idUser من المستخدم المسجل حاليًا
//        List<StickyNoteDTO> stickyNoteDTOS = this.stickyNoteService.allStickyNotes(idUser);
//        model.addAttribute("stickyNotes", stickyNoteDTOS);
//        return "home";
//    }  
//  
	    @GetMapping("")
	    public String home(@RequestParam(value = "idUser", required = false) Long idUser, Model model) {
	        Long currentUserId = securityUtils.getCurrentUserId(); // استدعاء idUser من المستخدم المسجل حاليًا
	        Long userIdToUse = (idUser != null) ? idUser : currentUserId;
	        List<StickyNoteDTO> stickyNoteDTOS = this.stickyNoteService.allStickyNotes(userIdToUse);
	        model.addAttribute("stickyNotes", stickyNoteDTOS);

	        // إضافة قائمة المستخدمين لعرضها في الصفحة (إذا كنت بحاجة إلى ذلك)
	        model.addAttribute("users", userService.findAll());

	        return "home";
	    }





    @GetMapping("sticky-note/{id}")
    public String stickyNote(@PathVariable(name = "id") Long id, Model model) {
        StickyNoteDTO stickyNote = this.stickyNoteService.findById(id);
        List<NoteDTO> notes = this.noteService.getAllNotes(id);
        model.addAttribute("stickyNote", stickyNote);
        model.addAttribute("notes", notes);
        return "sticky-note";
    }

    @GetMapping("edit-sticky-note/{id}")
    public String editStickyNote(@PathVariable(name = "id") Long id, Model model) {
        StickyNoteDTO stickyNote = this.stickyNoteService.findById(id);
        model.addAttribute("stickyNote", stickyNote);
        return "edit-sticky-note";
    }

    @PostMapping("/save")
    public String addNewStickyNote(@ModelAttribute("dto") AddStickyNoteDTO dto) {
        this.stickyNoteService.addStickyNote(dto);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateStickyNote(@ModelAttribute("dto") UpdateStickyNoteDTO dto,
                                   @PathVariable("id") Long id) {
        this.stickyNoteService.update(dto, id);
        return "redirect:/edit-sticky-note/" + id;
    }
    @GetMapping("/note/delete/{stickyNoteId}")
    public String deleteStickyNote(@PathVariable("stickyNoteId") Long stickyNoteId, HttpServletRequest request) {
        this.stickyNoteService.delete(stickyNoteId);
        String referer = request.getHeader("Referer");
        return "redirect:/";
    }
    @PostMapping("/updatesend/{id}")
    public String updatesend(@RequestParam("username") String username,
                             @PathVariable("id") Long id) {
        stickyNoteService.updateUserIdByUsername(username, id);
        return "redirect:/";
    }
   

}
