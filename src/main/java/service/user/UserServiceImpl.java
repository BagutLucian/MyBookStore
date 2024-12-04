package service.user;

import model.Role;
import model.User;
import repository.book.BookRepository;
import repository.user.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> findAllRoles() {
        return userRepository.findAllRoles();
    }

    public boolean deleteById(Long userId)
    {
        return userRepository.deleteById(userId);
    }
    public boolean save(User user)
    {
        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
    private String hashPassword(String password) {
        try {
            // Sercured Hash Algorithm - 256
            // 1 byte = 8 biți
            // 1 byte = 1 char
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
