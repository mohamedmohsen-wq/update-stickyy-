package com.springmvcproject.stickynotes.service.impl;

import com.springmvcproject.stickynotes.model.dto.AddStickyNoteDTO;
import com.springmvcproject.stickynotes.model.dto.StickyNoteDTO;
import com.springmvcproject.stickynotes.model.dto.UpdateStickyNoteDTO;
import com.springmvcproject.stickynotes.model.entity.StickyNote;
import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.model.enums.StickyNoteStatus;
import com.springmvcproject.stickynotes.model.mapper.StickyNoteMapper;
import com.springmvcproject.stickynotes.repository.StickyNoteRepo;
import com.springmvcproject.stickynotes.repository.UserRepo;
import com.springmvcproject.stickynotes.service.StickyNoteService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StickyNoteServiceImpl implements StickyNoteService {

    private final StickyNoteRepo stickyNoteRepo;
    private final StickyNoteMapper stickyNoteMapper;
    private final UserRepo userropo;
    @Override
    public AddStickyNoteDTO addStickyNote(AddStickyNoteDTO dto) {
        StickyNote entity = this.stickyNoteMapper.toEntity(dto);
        
       
        entity.setStatus(StickyNoteStatus.ACTIVE);
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> user = this.userropo.findByUsername(username);
        entity.setIdUser(user.get().getId());
        
        StickyNote savedEntity = this.stickyNoteRepo.save(entity);
        
        AddStickyNoteDTO addStickyNoteDTO = this.stickyNoteMapper.toAddDto(savedEntity);
        return addStickyNoteDTO;
    }


    @Override
    
    public List<StickyNoteDTO> allStickyNotes(Long idUser) {
        System.out.println("User ID: " + idUser); // تتبع قيمة idUser
        List<StickyNote> all = this.stickyNoteRepo.findAllByIdUserAndStatus(idUser, StickyNoteStatus.ACTIVE);
        return this.stickyNoteMapper.toDtos(all);
    }
    @Override
    public StickyNoteDTO findById(Long id) {
        Optional<StickyNote> entity = this.stickyNoteRepo.findById(id);
        return this.stickyNoteMapper.toDto(entity.get());
    }

    @Override
    public void update(UpdateStickyNoteDTO dto, Long id) {
        StickyNote entity = this.stickyNoteMapper.toEntity(dto);
        entity.setId(id);
        
        this.stickyNoteRepo.save(entity);
    }

	@Override
	public void delete(Long id) {
	this.stickyNoteRepo.deleteById(id);
		
	}


	@Override
//	public void updateUserIdByUsername(String username, Long stickyNoteId) {
//
//        Optional<UserEntity> user = userropo.findByUsername(username);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//        
//        StickyNote stickyNote = stickyNoteRepo.findById(stickyNoteId)
//                                              .orElseThrow(() -> new RuntimeException("Sticky Note not found"));
//
//        stickyNote.setIdUser(user.get().getId());
//
//        stickyNoteRepo.save(stickyNote);
//    }
	
	
	public void updateUserIdByUsername(String username, Long stickyNoteId) {
	    // الحصول على UserEntity بناءً على اسم المستخدم
	    Optional<UserEntity> user = userropo.findByUsername(username);
	    
	    // الحصول على StickyNote بناءً على الـ ID الخاص بها
	    StickyNote stickyNote = stickyNoteRepo.findById(stickyNoteId)
	                                          .orElseThrow(() -> new RuntimeException("Sticky Note not found"));
	    
	    // تحديث userId الخاص بالملاحظة
	    stickyNote.setIdUser(user.get().getId());
	    
	    // تعيين أن الملاحظة تم إرسالها
	    stickyNote.setSent(true);
	    
	    // حفظ الملاحظة المحدثة
	    stickyNoteRepo.save(stickyNote);
	}
}