package crud.service;

import crud.model.User;

import java.util.List;


public interface UserService {


    public List<User> getAllUsers();

    public User getUserById(int id);

    public void saveUser(User user);

    public void updateUser(int id, User user);

    public void deleteUser(int id);

}
