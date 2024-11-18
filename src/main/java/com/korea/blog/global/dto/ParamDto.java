package com.korea.blog.global.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParamDto {
  private String keyword;
  private String isModalOpen;

  public ParamDto() {
    keyword = "";
    isModalOpen = "false";
  }
}
