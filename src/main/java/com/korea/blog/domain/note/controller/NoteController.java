package com.korea.blog.domain.note.controller;

import com.korea.blog.domain.note.service.NoteService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
  private final NoteService noteService;

  @GetMapping("")
  public String list() {
    return "main";
  }

  @PostMapping("/write")
  public String write() {
    noteService.saveDefault();
    return "redirect:/";
  }
}
