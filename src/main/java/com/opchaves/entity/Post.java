package com.opchaves.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import com.opchaves.web.dto.PostResponse;
import com.opchaves.web.dto.UpdatePost;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@MongoEntity(collection = "posts")
public class Post extends ReactivePanacheMongoEntity {
  public String title;
  public String content;
  public String author;
  public LocalDateTime creationDate;

  public Post() {
  }

  public Post(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public static Uni<Post> updatePost(String id, UpdatePost updatePost) {
    Uni<Post> aPost = Post.findById(new ObjectId(id));

    return aPost.onItem().transform(post -> {
      post.title = updatePost.title();
      post.content = updatePost.content();
      return post;
    }).call(post -> post.persistOrUpdate());
  }

  public static Multi<PostResponse> streamAllPosts() {
    return Post.<Post>streamAll().onItem().transform(p -> {
      var r = new PostResponse();
      r.id = p.id.toString();
      r.title = p.title;
      r.content = p.content;
      r.author = p.author;
      r.creationDate = p.creationDate.toString();
      return r;
    });
  }

  public static Uni<List<PostResponse>> listPosts() {
    return Post.<Post>listAll().onItem().transform(posts -> {
      return PostResponse.fromList(posts);
    });
  }
}
