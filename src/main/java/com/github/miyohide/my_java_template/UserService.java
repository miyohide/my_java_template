package com.github.miyohide.my_java_template;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Long> getUserMap() {
        Iterable<User> users = userRepository.findAllByOrderById();
        Map<String, Long> rval = new LinkedHashMap<>();

        for (User user : users) {
            rval.put(user.getName(), user.getId());
        }

        return rval;
    }
}
