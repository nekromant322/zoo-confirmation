package com.nekromant.confirmation.controller;

import com.nekromant.confirmation.mapper.ConfirmationTokenMapper;
import com.nekromant.confirmation.model.ConfirmationToken;
import com.nekromant.confirmation.service.ConfirmationTokenService;
import dto.ConfirmationTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class ConfirmationTokenRestController {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private ConfirmationTokenMapper confirmationTokenMapper;

    @GetMapping
    public ConfirmationToken getToken(@RequestParam String token) {
        return confirmationTokenService.getToken(token);
    }

    @GetMapping("encoded")
    public String getEncodedToken(@RequestParam String email, @RequestParam String phone) {
        return confirmationTokenService.getEncodedToken(email, phone);
    }

    @PostMapping("create")
    public void createToken(@RequestBody ConfirmationTokenDTO confirmationTokenDTO) {
        confirmationTokenService.addToken(confirmationTokenDTO.getToken(), confirmationTokenDTO.getEmail());
    }

    @PostMapping("delete")
    public void removeToken(@RequestBody ConfirmationTokenDTO confirmationTokenDTO) {
        confirmationTokenService.deleteToken(confirmationTokenMapper.dtoToEntity(confirmationTokenDTO));
    }
}
