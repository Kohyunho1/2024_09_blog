package com.korea.blog.domain.main;

import com.korea.blog.domain.main.note.dto.NoteDto;
import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.dto.NotebookDto;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.global.dto.UrlParamManager;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

  private final MainService mainService;

  @PostConstruct
  public void init() {
    mainService.init();
  }

  // 초기 화면 -> 첫번째 노트북의 첫번째 노트가 선택되도록 약속
  @GetMapping("/")
  public String main(Model model, UrlParamManager urlParamManager) {

    MainDataDto mainDataDto = mainService.getDefaultMainDataDto(urlParamManager.getKeyword(), urlParamManager.getSortTarget());
    model.addAttribute("mainDataDto", mainDataDto);

    return "main";
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SearchDto {
    List<NotebookDto> searchedNotebookList;
    List<NoteDto> searchedNoteList;
  }

  @GetMapping("/api/search")
  @ResponseBody
  public SearchDto search(String keyword) {

    // 명령형
    List<Notebook> searchedNotebookList = mainService.getSearchedNotebookList(keyword);
    List<NotebookDto> searchedNotebookDtoList = new ArrayList<>();

    for(Notebook notebook : searchedNotebookList) {
      NotebookDto notebookDto = notebook.toDto();
      searchedNotebookDtoList.add(notebookDto);
    }

    // 선언형

    List<Note> searcheNoteList = mainService.getSearchedNoteList(keyword);
    List<NoteDto> searchedNoteDtoList = searcheNoteList.stream().map(Note::toDto).toList();

    return new SearchDto(searchedNotebookDtoList, searchedNoteDtoList);
  }

//  // 메인 화면
//  @GetMapping("/notes/{noteId}")
//  public String main(@PathVariable long noteId, Model model) {
//    List<Note> noteList = mainService.getNoteList();
//    Note selectedNote = mainService.getNote(noteId);
//
//    model.addAttribute("noteList", noteList);
//    model.addAttribute("selectedNote", selectedNote);
//
//    return "main";
//  }
}
