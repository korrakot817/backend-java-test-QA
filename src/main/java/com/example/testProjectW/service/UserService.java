package com.example.testProjectW.service;

import com.example.testProjectW.Repository.UserRepository;
import com.example.testProjectW.entity.User;
import com.example.testProjectW.exception.NotFoundException;
import com.example.testProjectW.model.user.UserRequestDto;
import com.example.testProjectW.model.user.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static int lastAccountNumber = 0;


    public List<User> listAll() throws NotFoundException {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException();
        }

        return all;
    }

    public User getUserByAccntNo(String accntNo) throws NotFoundException {
        Optional<User> byAccntNo = userRepository.findByAccntNo(accntNo);
        if (byAccntNo.isEmpty()) {
            throw new NotFoundException();
        }

        return byAccntNo.get();
    }

    public User createUser (UserRequestDto request){
        User user = new User();

        user.setRowId(UUID.randomUUID().toString());
        user.setAccntNo(this.generateAccountNumber());
        user.setCreated(Calendar.getInstance().getTime());
        user.setCreateBy(request.getRelateParty().getOrderCreator().getName());
        user.setLastUpd(Calendar.getInstance().getTime());
        user.setLastUpdBy((request.getRelateParty().getOrderCreator().getName()));
        user.setFirstName(request.getFistName());
        user.setIdCardType(request.getIdCardType());
        user.setIdCardNo(request.getIdCardNo());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        if (Objects.nonNull(request.getEmail())) {
            user.setEmail(request.getEmail());
        }

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public User updateUser (UserRequestDto request, User oldUser) {
        User userO = oldUser;

        userO.setLastUpd(Calendar.getInstance().getTime());
        userO.setLastUpdBy((request.getRelateParty().getOrderCreator().getName()));

        if (Objects.nonNull(request.getFistName())) {
            userO.setFirstName(request.getFistName());
        }

        if (Objects.nonNull(request.getLastName())) {
            userO.setLastName(request.getLastName());
        }

        if (Objects.nonNull(request.getPhoneNumber())) {
            userO.setPhoneNumber(request.getPhoneNumber());
        }

        if (Objects.nonNull(request.getEmail())) {
            userO.setEmail(request.getEmail());
        }

        if (Objects.nonNull(request.getRole())) {
            userO.setRole(request.getRole());
        }

        return userRepository.save(userO);
    }

    public void deleteUserById(String accntNo) throws Exception {
        Optional<User> findByAccntNo = userRepository.findByAccntNo(accntNo);

        if (Objects.isNull(accntNo)) {
            throw new Exception("user not found");
        }

        User user = findByAccntNo.get();
        userRepository.delete(user);
    }

    public UserResponseDto composeResponse(User user) {
        UserResponseDto response = new UserResponseDto();

        response.setAccountNo(user.getAccntNo());
        response.setFistName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;
    }

    public String generateAccountNumber() {
        // ตรวจสอบว่าเลขที่บัญชีซ้ำกันหรือไม่
        String accntNo;
        do {
            // เพิ่มค่าล่าสุดของเลขที่บัญชี
            lastAccountNumber++;

            // สร้างเลขที่บัญชีในรูปแบบ "10000XX"
            accntNo = "10000" + String.format("%02d", lastAccountNumber);
        } while (userRepository.existsByAccntNo(accntNo));

        return accntNo;
    }
}
