package com.nekromant.confirmation.mapper;

import com.nekromant.confirmation.model.ConfirmationToken;
import dto.ConfirmationTokenDTO;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationTokenMapper {

    public ConfirmationToken dtoToEntity(ConfirmationTokenDTO confirmationTokenDTO) {
        return new ConfirmationToken(
                confirmationTokenDTO.getId(),
                confirmationTokenDTO.getToken(),
                confirmationTokenDTO.getEmail(),
                confirmationTokenDTO.getExpiredDate()
        );
    }

    public ConfirmationTokenDTO entityToDto(ConfirmationToken confirmationToken) {
        return new ConfirmationTokenDTO(
                confirmationToken.getId(),
                confirmationToken.getToken(),
                confirmationToken.getEmail(),
                confirmationToken.getExpiredDate()
        );
    }
}
