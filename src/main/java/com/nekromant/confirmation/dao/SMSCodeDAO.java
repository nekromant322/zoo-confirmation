package com.nekromant.confirmation.dao;

import com.nekromant.confirmation.model.SMSCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SMSCodeDAO extends CrudRepository<SMSCode, Long> {
    Optional<SMSCode> findByCodeAndPhoneNumber(Integer code, String phoneNumber);
}