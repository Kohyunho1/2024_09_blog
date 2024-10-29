package com.korea.blog.domain.note.service;

import com.korea.blog.domain.note.entity.Note;
import com.korea.blog.domain.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;

  public List<Note> getList() {
    return noteRepository.findAll();
  }

  public void saveDefault() {
    Note note = Note.builder()
        .title("new title")
        .content("")
        .build();

    noteRepository.save(note);
  }
}

