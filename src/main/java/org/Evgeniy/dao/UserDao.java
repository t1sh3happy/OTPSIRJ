package org.Evgeniy.dao;

import org.Evgeniy.model.User;

public interface UserDao {
    void addUser(User user);
    User getUserByUsername(String username);
    void updateUser(User user);  // Добавьте эту строку

}
