package com.opchaves.web;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import com.opchaves.entity.Post;
import com.opchaves.web.dto.PostRequest;
import com.opchaves.web.dto.PostResponse;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/posts")
public class PostResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<List<PostResponse>> list() {
    return Post.listPosts();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Uni<Response> addPost(PostRequest req) {
    Post post = req.toEntity();
    post.creationDate = LocalDateTime.now();
    return post.<Post>persist()
        .map(p -> Response.created(URI.create("/api/posts/" + p.id.toString())).entity(p).build());
  }
}
