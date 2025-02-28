package ua.myronets.FirstWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.myronets.FirstWebApp.models.Product;
import ua.myronets.FirstWebApp.models.User;
import ua.myronets.FirstWebApp.repo.ProductRepository;
import ua.myronets.FirstWebApp.repo.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User createNewUser (User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> findAllUser() {
        return userRepository.findAll();
    }


    public User updateProduct(User user) {
        return userRepository.save(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



}
