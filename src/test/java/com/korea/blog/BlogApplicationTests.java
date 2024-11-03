package com.korea.blog;

import com.korea.blog.domain.note.entity.Note;
import com.korea.blog.domain.note.repository.NoteRepository;
import com.korea.blog.domain.notebook.entity.Notebook;
import com.korea.blog.domain.notebook.repository.NotebookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BlogApplicationTests {

  @Autowired
  private NoteRepository noteRepository;

  @Autowired
  private NotebookRepository notebookRepository;

  @Test
  @DisplayName("노트북과 노트 테스트 데이터 생성")
  void t3() {

    Notebook book1 = Notebook.builder()
        .name("test book1")
        .build();

    notebookRepository.save(book1);

    Note note1 = Note.builder()
        .title("test note title")
        .content("test note content")
        .build();

//    book1.getNoteList().add(note1);

    // 엔터티 끼리 연결할 때는 외래키를 가진쪽이 관계의 주인이다.
    // 관계의 주인이 DB를 반영한다.

    note1.setParent(book1);

    // 큰거에다가 작은 것 넣는게 자연스러움
    book1.addNote(note1);

    noteRepository.save(note1);

  }

  @Test
  @DisplayName("노트 수정")
  @Rollback(false)
  @Transactional
    // 영속성 컨텍스트
  void t2() {
    Note note = noteRepository.findById(2L).get();

    note.setTitle("제목 수정2");
    note.setContent("내용 수정2");

  }

  @Test
  @DisplayName("노트 테스트 데이터 생성")
  void t1() {

    Note note = Note.builder()
        .title("title1")
        .content("content1")
        .build();

    noteRepository.save(note);

  }

}
