package com.springmvcproject.stickynotes.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springmvcproject.stickynotes.model.enums.StickyNoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "sticky_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StickyNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sticky_note_id")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    private String description;
    private String name;

    @Enumerated(EnumType.STRING)
    private StickyNoteStatus status;

    private String color;
   

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CurrentTimestamp
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
     boolean isSent;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity userEntity;
}
