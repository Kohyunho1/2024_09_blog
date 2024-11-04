package com.korea.blog.domain;

import com.korea.blog.domain.note.entity.Note;
import com.korea.blog.domain.note.service.NoteService;
import com.korea.blog.domain.notebook.entity.Notebook;
import com.korea.blog.domain.notebook.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

  private final NoteService noteService;
  private final NotebookService notebookService;

  @Transactional
  public void init() {

    List<Note> noteList = noteService.getList();
    List<Notebook> notebookList = notebookService.getList();

    if (notebookList.isEmpty()) {
      saveDefaultNotebook();
    }
  }

//  public void deleteNoteBook(long bookId) {
//    Notebook deleteTarget = notebookService.getOne(bookId);
//    List<Notebook> subNotebookList = deleteTarget.getSubNotebookList();
//
//    for (Notebook subNotebook : subNotebookList) {
//      List<Note> noteList = subNotebook.getNoteList();
//      noteService.deleteAll(noteList);
//      notebookService.delete(subNotebook);
//    }
//
//    noteService.deleteAll(deleteTarget.getNoteList());
//    notebookService.delete(deleteTarget);
//  }

  @Transactional
  public Notebook saveSubNotebook(long parentId) {
    Notebook subNotebook = notebookService.saveSubNotebook(parentId);
    Note note = noteService.saveDefault();

    // 서브 노트북의 기본 노트 생성
    subNotebook.addNote(note);

    return subNotebook;
  }

  @Transactional
  public Notebook saveDefaultNotebook() {
    Notebook notebook = notebookService.saveDefault();
    Note note = noteService.saveDefault();
    notebook.addNote(note);

    return notebook;
  }

  public Note saveDefaultNote(long bookId) {
    Notebook notebook = notebookService.getOne(bookId);
    Note note = noteService.saveDefault();

    notebook.addNote(note);
    notebook = notebookService.save(notebook);

    return note;
  }

  public List<Note> getNoteList() {
    return noteService.getList();
  }

  public Note getNote(long noteId) {
    return noteService.getOne(noteId);
  }

  public List<Notebook> getNoteBookList() {
    return notebookService.getList();
  }
}
