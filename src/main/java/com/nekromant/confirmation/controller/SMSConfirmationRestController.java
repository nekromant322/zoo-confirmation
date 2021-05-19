package com.nekromant.confirmation.controller;

import com.nekromant.confirmation.service.SMSCodeService;
import dto.SMSCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/smsCode")
public class SMSConfirmationRestController {

    @Autowired
    private SMSCodeService smsCodeService;

    @PostMapping("create")
    public void createSMSCode(@RequestBody SMSCodeDTO smsCodeDTO) {
        smsCodeService.createCode(smsCodeDTO.getPhoneNumber());
    }

    @PostMapping("verify")
        public void verifySMSCode(@RequestBody SMSCodeDTO smsCodeDTO) {
        smsCodeService.verifyCode(smsCodeDTO.getCode(), smsCodeDTO.getPhoneNumber());
    }
}
