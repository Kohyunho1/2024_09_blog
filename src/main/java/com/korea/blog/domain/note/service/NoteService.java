package com.korea.blog.domain.note.service;

import com.korea.blog.domain.note.entity.Note;
import com.korea.blog.domain.note.repository.NoteRepository;
import com.korea.blog.domain.notebook.entity.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;

  public List<Note> getList() {
    return noteRepository.findAll();
  }

  public Note saveDefault() {
    Note note = Note.builder()
        .title("new title")
        .content("")
        .build();

    return noteRepository.save(note);
  }

  public Note getOne(long noteId) {
    return noteRepository.findById(noteId).orElseThrow();
  }

  public Note modify(long noteId, String title, String content) {

    if(title.trim().length() == 0) {
      title = "제목 없음";
    }

    Note note = getOne(noteId);
    note.setTitle(title);
    note.setContent(content);

    return noteRepository.save(note);
  }

  public void delete(long noteId) {
    noteRepository.deleteById(noteId);
  }
}

