package ua.myronets.FirstWebApp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.myronets.FirstWebApp.models.Post;
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
