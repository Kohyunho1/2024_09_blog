package com.korea.blog.domain;

import com.korea.blog.domain.note.entity.Note;
import com.korea.blog.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

  private final NoteService noteService;

  public void init() {

    List<Note> noteList = noteService.getList();

    if (noteList.isEmpty()) {
      noteService.saveDefault();
    }
  }

  public List<Note> getNoteList() {
    return noteService.getList();
  }
}
