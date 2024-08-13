import { memo } from 'react';
import { useGetApiPosts } from '../../gen/endpoints/post-resource/post-resource';

export const HomePage = memo(() => {
  const posts = useGetApiPosts({});

  if (posts.isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Home Page</h1>
      {posts.data?.map((post) => (
        <div key={post.id}>
          <h2>{post.title}</h2>
          <p>{post.content}</p>
        </div>
      ))}
    </div>
  );
});
HomePage.displayName = 'HomePage';
