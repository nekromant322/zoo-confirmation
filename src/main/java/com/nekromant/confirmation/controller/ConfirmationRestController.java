package com.nekromant.confirmation.controller;

import com.nekromant.confirmation.model.SMSCode;
import com.nekromant.confirmation.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/smsCode")
public class ConfirmationRestController {

    @Autowired
    private SMSCodeService smsCodeService;

    @PostMapping("create")
    public void createSMSCode(@RequestBody Map<String, String> payload) {
        if (payload.containsKey("phoneNumber")){
            Integer code = smsCodeService.generateCode();
            smsCodeService.addCode(code, payload.get("phoneNumber"));
            //sendSms(code, phoneNumber);
        }
    }

    @PostMapping("delete")
    public void removeSMSCode(@RequestBody Map<String, String> payload) {
        if (payload.containsKey("code") && payload.containsKey("phoneNumber")) {
            SMSCode codeEntity = smsCodeService.getCode(Integer.valueOf(payload.get("code")), payload.get("phoneNumber"));
            smsCodeService.deleteCode(codeEntity);
        }
    }
}
