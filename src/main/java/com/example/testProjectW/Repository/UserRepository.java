package com.example.testProjectW.Repository;

import com.example.testProjectW.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByAccntNo(String accntNo);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByIdCardNoAndFirstNameAndLastName(String idCardNo, String firstName, String lastName);

    List<User>findByIdCardTypeAndIdCardNo(String idCardType, String idCardNo);

    boolean existsByAccntNo(String accntNo);

}
