package com.korea.blog.domain.main.note.entity;

import com.korea.blog.domain.main.note.dto.NoteDto;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @ManyToOne
  private Notebook parent;

  public NoteDto toDto() {
    return NoteDto.builder()
        .id(this.id)
        .title(this.title)
        .content(this.content)
        .build();
  }
}
