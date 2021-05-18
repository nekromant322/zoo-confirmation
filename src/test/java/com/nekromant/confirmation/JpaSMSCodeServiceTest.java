package com.nekromant.confirmation;

import com.nekromant.confirmation.dao.SMSCodeDAO;
import com.nekromant.confirmation.model.SMSCode;
import com.nekromant.confirmation.service.SMSCodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ZooConfirmationApplication.class)
@ActiveProfiles("test")
@Transactional
public class JpaSMSCodeServiceTest {

    @Autowired
    private SMSCodeService smsCodeService;

    @Autowired
    private SMSCodeDAO smsCodeDAO;

    @Test
    public void createCodeWithValidData() {
        String phoneNumber = "89163578091";

        smsCodeService.createCode(phoneNumber);

        Assert.assertEquals(smsCodeDAO.findAll().iterator().next().getPhoneNumber(), phoneNumber);
    }

    @Test
    public void createCodeWithInvalidData() {
        Assert.assertThrows(ResponseStatusException.class, () -> smsCodeService.createCode(null));
        Assert.assertThrows(ResponseStatusException.class, () -> smsCodeService.createCode(""));
    }

    @Test
    public void verifyCodeWithValidData() {
        SMSCode smsCode = new SMSCode(1L, 1111, "89163578091", LocalDateTime.now());
        smsCodeDAO.save(smsCode);

        smsCodeService.verifyCode(1111, "89163578091");

        Assert.assertEquals(smsCodeDAO.findByCodeAndPhoneNumber(smsCode.getCode(), smsCode.getPhoneNumber()), Optional.empty());
    }

    @Test
    public void verifyCodeWithInvalidData() {
        Assert.assertThrows(ResponseStatusException.class, () -> smsCodeService.verifyCode(null, null));
        Assert.assertThrows(ResponseStatusException.class, () -> smsCodeService.verifyCode(null, ""));
        Assert.assertThrows(ResponseStatusException.class, () -> smsCodeService.verifyCode(0, ""));
        Assert.assertThrows(ResponseStatusException.class, () -> smsCodeService.verifyCode(0, ""));
    }
}
