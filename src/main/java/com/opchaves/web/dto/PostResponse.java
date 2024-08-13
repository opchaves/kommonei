package com.opchaves.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Post", description = "Entity that represents a post.")
public class PostResponse {
  public String id;
  public String title;
  public String content;
  public String author;
  public String creationDate;

  public PostResponse() {
  }

  public PostResponse(String id, String title, String content, String author, String creationDate) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
    this.creationDate = creationDate;
  }
}
