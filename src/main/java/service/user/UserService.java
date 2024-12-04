package service.user;

import model.Book;
import model.Role;
import model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public List<Role> findAllRoles();
    public boolean deleteById(Long userId);
    public boolean save(User user);
}
