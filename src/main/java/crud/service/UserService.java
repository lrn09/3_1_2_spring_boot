package crud.service;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.orElseThrow();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User user) {
        Optional<User> optionalUser = Optional.of(userRepository.findById(id).orElseThrow());

        User userToBeUpdated = optionalUser.get();

        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());

        userRepository.save(userToBeUpdated);

    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
