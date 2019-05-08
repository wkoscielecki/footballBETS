package pl.koscielecki.service;


import pl.koscielecki.user.User;

public interface UserService {
    User findByEmail(String name);
    void saveUser(User user);
}
