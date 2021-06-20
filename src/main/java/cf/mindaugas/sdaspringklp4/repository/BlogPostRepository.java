package cf.mindaugas.sdaspringklp4.repository;

import cf.mindaugas.sdaspringklp4.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> { }
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    Optional<List<BlogPost>> findBlogPostsByAuthorContains(String author); // SELECT ... WHERE author LIKE '%author_name%'
    Optional<List<BlogPost>> findBlogPostsByAuthorContainsOrderByPost(String author);
    Optional<List<BlogPost>> findBlogPostsByPostContains(String postText); // SELECT ... WHERE post LIKE '%text%'
}
