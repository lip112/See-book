package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
public class ChangeImageRequestDTO {

    @NotBlank
    private Long userId;
}
