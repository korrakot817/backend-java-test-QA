package com.example.testProjectW.validate;

import com.example.testProjectW.Repository.UserRepository;
import com.example.testProjectW.entity.User;
import com.example.testProjectW.exception.ErrorDetail;
import com.example.testProjectW.exception.NotFoundException;
import com.example.testProjectW.exception.ValidateException;
import com.example.testProjectW.model.user.UserRequestDto;
import com.example.testProjectW.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserValidate {

    @Autowired
    UserRepository userRepository;

    public void validateUser(UserRequestDto request) throws Exception {
        List<ErrorDetail> errorDetails = new ArrayList<>();

        if (Objects.isNull(request.getFistName())) {
            errorDetails.add(new ErrorDetail("firstName", "Parameter not found"));
        }

        if (Objects.isNull(request.getLastName())) {
            errorDetails.add(new ErrorDetail("lastName", "Parameter not found"));
        }

        if (Objects.isNull(request.getIdCardType())) {
            errorDetails.add(new ErrorDetail("idCardType", "Parameter not found"));
        }else {
            if (!request.getIdCardType().equals("IdCard")) {
                throw new ValidateException("Id card type is not supported");
            }
        }

        if (Objects.isNull(request.getIdCardNo())) {
            errorDetails.add(new ErrorDetail("idCardNo", "Parameter not found"));
        }

        if (Objects.isNull(request.getPhoneNumber())) {
            errorDetails.add(new ErrorDetail("phoneNumber", "Parameter not found"));

        }else {
            if (!ValidateUtils.prefixNumber(request.getPhoneNumber())) {
                throw new ValidateException("Invalid mobile format");
            }
        }

        if (Objects.isNull(request.getRole())) {
            errorDetails.add(new ErrorDetail("role", "Parameter not found"));
        }

        if (Objects.isNull(request.getRelateParty())) {
            errorDetails.add(new ErrorDetail("relateParty", "Parameter not found"));
        }else {

            if (Objects.isNull(request.getRelateParty().getOrderCreator())) {
                errorDetails.add(new ErrorDetail("relateParty.orderCreator", "Parameter not found"));

            }else {
                if (Objects.isNull(request.getRelateParty().getOrderCreator().getName())) {
                    errorDetails.add(new ErrorDetail("relateParty.orderCreator.name", "Parameter not found"));
                }
            }
        }

        if (errorDetails.size() > 0) {
            throw new ValidateException("Missing or invalid parameter", errorDetails);
        }

        List<User> byIdCardNoAndIdType = userRepository.findByIdCardTypeAndIdCardNo(request.getIdCardType(), request.getIdCardNo());
        if (!byIdCardNoAndIdType.isEmpty()) {
            throw new ValidateException("can not create user : id card duplicate");
        }

        List<User> checkDupName = userRepository.findByIdCardNoAndFirstNameAndLastName(request.getIdCardNo(), request.getFistName(), request.getLastName());
        if (!checkDupName.isEmpty()) {
            throw new ValidateException("can not create user : account duplicate");
        }

        if (request.getIdCardNo().length() != 13) {
            throw new ValidateException("length of id card no :" + ""  + request.getIdCardNo() + " " + "digits");
        }

    }

    public User vildaterUpdate(String accntNo, UserRequestDto request) throws NotFoundException, ValidateException {
        List<ErrorDetail> errorDetails = new ArrayList<>();

        if (Objects.isNull(request.getRelateParty())) {
            errorDetails.add(new ErrorDetail("relateParty", "Parameter not found"));
        }else {

            if (Objects.isNull(request.getRelateParty().getOrderCreator())) {
                errorDetails.add(new ErrorDetail("relateParty.orderCreator", "Parameter not found"));

            }else {
                if (Objects.isNull(request.getRelateParty().getOrderCreator().getName())) {
                    errorDetails.add(new ErrorDetail("relateParty.orderCreator.name", "Parameter not found"));
                }
            }
        }

        if (errorDetails.size() > 0) {
            throw new ValidateException("Missing or invalid parameter", errorDetails);
        }

        Optional<User> byAccntNo = userRepository.findByAccntNo(accntNo);
        if (byAccntNo.isEmpty()) {
            throw new NotFoundException();
        }

        User user = byAccntNo.get();

        return user;
    }

}
