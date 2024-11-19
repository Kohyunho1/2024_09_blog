package com.korea.blog.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@Scope("request")
public class ParamDto {
  private String keyword;
  private String isModalOpen;
  private String sortTarget;

  public ParamDto() {
    keyword = "";
    isModalOpen = "false";
    sortTarget = "id";
  }

  public String getQueryParam() {
    return "keyword=" + keyword + "&isModalOpen=" + isModalOpen + "&sortTarget=" + sortTarget;
  }

  public String getParamUrl(String url) {
    return url + "?" + getQueryParam();
  }
}
