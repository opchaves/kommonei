package com.opchaves.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.opchaves.entity.Post;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;

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

  public static List<PostResponse> fromList(List<Post> posts) {
    var result = new ArrayList<PostResponse>();
    for (final Post post : posts) {
      result.add(
          new PostResponse(
              post.id.toString(),
              post.title,
              post.content,
              post.author,
              post.creationDate.toString()));
    }
    return result;
  }
}
