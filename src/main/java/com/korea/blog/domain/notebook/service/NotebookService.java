package com.korea.blog.domain.notebook.service;

import com.korea.blog.domain.note.entity.Note;
import com.korea.blog.domain.notebook.entity.Notebook;
import com.korea.blog.domain.notebook.repository.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {

  private final NotebookRepository notebookRepository;

  public List<Notebook> getList() {
    return notebookRepository.findAll();
  }

  public Notebook saveDefault() {
    Notebook notebook = Notebook.builder()
        .name("새노트북")
        .build();

    return notebookRepository.save(notebook);
  }

  @Transactional
  public Notebook saveSubNotebook(long parentId) {
    Notebook parentNotebook = getOne(parentId);
    Notebook subNotebook = saveDefault(); // 서브 노트북

    parentNotebook.addSubNotebook(subNotebook);

    return subNotebook;
  }

  public Notebook getOne(long bookId) {
    return notebookRepository.findById(bookId).orElseThrow();
  }

  public Notebook save(Notebook notebook) {
    return notebookRepository.save(notebook);
  }

  public void checkSubNotebook(long bookId) {
    Notebook notebook = getOne(bookId);

    if (notebook.getParent() != null) {
      throw new RuntimeException("서브 노트북에 서브 노트북을 추가할 수 없습니다. 추후 업데이트 예정");
    }
  }

  public void delete(long bookId) {
    notebookRepository.deleteById(bookId);
  }

  public void delete(Notebook subNotebook) {
    notebookRepository.delete(subNotebook);
  }

//  public void delete(long bookId) {
//
//    Notebook deleteTarget = getOne(bookId);
//
//    List<Notebook> subNotebookList = deleteTarget.getSubNotebookList();
//
//    for (Notebook subNotebook : subNotebookList) {
//      notebookRepository.delete(subNotebook);
//
//      List<Note> noteList = subNotebook.getNoteList();
//    }
//
//    deleteTarget.getNoteList(); // 자식
//
//    notebookRepository.deleteById(bookId);
//  }
}
