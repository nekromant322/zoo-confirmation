package com.nekromant.confirmation.service;

import com.nekromant.confirmation.dao.SMSCodeDAO;
import com.nekromant.confirmation.model.SMSCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
public class SMSCodeService {

    @Autowired
    private SMSCodeDAO smsCodeDAO;

    @Value("${app.const.smsCodeExpiredDelayInSeconds}")
    private String codeExpiredDelay;

    /**
     * Создание нового смс кода {@link SMSCode} и его отправка
     *
     * @param phoneNumber - номер телефона, на который будет отправлен код {@link SMSCode}
     */
    public void createCode(String phoneNumber) {
        if (phoneNumber == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректные входные данные!");

        Integer code = generateCode();
        addCode(code, phoneNumber);
        //sendSms(code, phoneNumber);
    }

    public void verifyCode(Integer code, String phoneNumber) {
        if (code == null || phoneNumber == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректные входные данные!");

        SMSCode codeEntity = getCode(code, phoneNumber);
        deleteCode(codeEntity);
        log.info("Код {} для номера {} успешно прошел проверку!", code, phoneNumber);
    }

    /**
     * Генерация случайного кода для смс подтверждения
     *
     * @return случайное число (код) - {@link SMSCode}
     */
    public Integer generateCode() {
        Random random = new Random();
        return random.nextInt((9999 - 100) + 1) + 10;
    }

    public SMSCode getCode(Integer code, String phoneNumber) {
        return smsCodeDAO.findByCodeAndPhoneNumber(code, phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Код не найден! code = " + code + "; phone = " + phoneNumber));
    }

    public void addCode(Integer code, String phoneNumber) {
        smsCodeDAO.save(new SMSCode(code, phoneNumber, LocalDateTime.now().plusSeconds(Long.parseLong(codeExpiredDelay))));
        log.info("Код {} был успешно сохранен для номера {}!", code, phoneNumber);
    }

    public void deleteCode(SMSCode smsCode) {
        smsCodeDAO.delete(smsCode);
    }
}
