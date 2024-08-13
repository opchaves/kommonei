package com.opchaves.web.dto;

import com.opchaves.entity.Post;

import jakarta.validation.constraints.NotEmpty;

public class CreatePost {

  @NotEmpty
  public String title;

  @NotEmpty
  public String content;
  public String author;

  public CreatePost() {
  }

  public Post toEntity() {
    return new Post(title, content, author);
  }
}
