package com.nekromant.confirmation.controller;

import com.nekromant.confirmation.service.SMSCodeService;
import dto.SMSCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/smsCode")
public class SMSConfirmationRestController {

    @Autowired
    private SMSCodeService smsCodeService;

    @Value("${app.const.smsCodeExpiredDelayInSeconds}")
    private String codeExpiredDelay;

    @PostMapping("create")
    public Long createSMSCode(@RequestBody SMSCodeDTO smsCodeDTO) {
        smsCodeService.createCode(smsCodeDTO.getPhoneNumber());
        return Long.valueOf(codeExpiredDelay);
    }

    @PostMapping("verify")
        public void verifySMSCode(@RequestBody SMSCodeDTO smsCodeDTO) {
        smsCodeService.verifyCode(smsCodeDTO.getCode(), smsCodeDTO.getPhoneNumber());
    }
}
