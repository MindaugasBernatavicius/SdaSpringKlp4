package cf.mindaugas.sdaspringklp4.repository;

import cf.mindaugas.sdaspringklp4.model.BlogPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> { }
