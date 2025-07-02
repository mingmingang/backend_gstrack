package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.UserRepository;
import com.astratech.backend_gstrack.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    @Qualifier("UserRepository")
    @Autowired
    private UserRepository userRepository;

    public User getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByUserIdAsc();
    }

    public boolean saveUser(User user) {
        User result = userRepository.save(user);
        return result != null;
    }

    public boolean updateUser(User user) {
        User existingUser = userRepository.findByUserId(user.getUserId());
        if (existingUser == null) {
            return false;
        }

        if (StringUtils.hasLength(user.getNama())) {
            existingUser.setNama(user.getNama());
        }

        if (StringUtils.hasLength(user.getJabatan())) {
            existingUser.setJabatan(user.getJabatan());
        }

        if (StringUtils.hasLength(user.getLokasiKerja())) {
            existingUser.setLokasiKerja(user.getLokasiKerja());
        }

        if (StringUtils.hasLength(user.getAtasanId())) {
            existingUser.setAtasanId(user.getAtasanId());
        }

        if (StringUtils.hasLength(user.getTipeAkun())) {
            existingUser.setTipeAkun(user.getTipeAkun());
        }

        if (StringUtils.hasLength(user.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }

        userRepository.save(existingUser);
        return true;
    }

    public boolean deleteUser(String userId) {
        User existingUser = userRepository.findByUserId(userId);
        if (existingUser == null) {
            return false;
        }
        userRepository.delete(existingUser);
        return true;
    }
}
