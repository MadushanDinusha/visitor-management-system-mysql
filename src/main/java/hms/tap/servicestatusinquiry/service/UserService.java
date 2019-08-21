package hms.tap.servicestatusinquiry.service;

import hms.tap.servicestatusinquiry.domain.User;

public interface UserService {
    public User findUserByEmail(String email);

    void saveUser(User user);
}
