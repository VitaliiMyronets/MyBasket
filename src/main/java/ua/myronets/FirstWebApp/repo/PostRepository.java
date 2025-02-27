package ua.myronets.FirstWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.myronets.FirstWebApp.models.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
