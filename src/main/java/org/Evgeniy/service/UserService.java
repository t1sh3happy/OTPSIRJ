package org.Evgeniy.service;

import org.Evgeniy.dao.UserDao;
import org.Evgeniy.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private final UserDao userDao;

    // Конструктор, принимающий UserDao
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // Регистрация нового пользователя с шифрованием пароля
    public void registerUser(String username, String password, String role) {
        if (userDao.getUserByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        String passwordHash = hashPassword(password);
        User user = new User(0, username, passwordHash, role);
        userDao.addUser(user);
    }

    // Изменение роли пользователя
    public void changeUserRole(String username, String newRole) {
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            user.setRole(newRole);
            userDao.updateUser(user);
        }
    }

    // Аутентификация пользователя
    public boolean authenticate(String username, String password) {
        User user = userDao.getUserByUsername(username);
        return user != null && user.getPasswordHash().equals(hashPassword(password));
    }

    // Обновление информации о пользователе
    public void updateUser(User user) {
        String passwordHash = hashPassword(user.getPasswordHash());
        user.setPasswordHash(passwordHash);
        userDao.updateUser(user);
    }


    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
