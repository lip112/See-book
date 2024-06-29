package com.example.seebook.domain.user.dto.requset.sms;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteAccountRequestDTO {
    @NotBlank
    private String provider;

}
