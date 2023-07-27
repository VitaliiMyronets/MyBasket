package ua.myronets.FirstWebApp.repo;

import org.springframework.data.repository.CrudRepository;
import ua.myronets.FirstWebApp.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}
