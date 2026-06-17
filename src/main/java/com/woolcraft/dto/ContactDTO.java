package com.woolcraft.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactDTO {
    @NotBlank private String name;
    @NotBlank @Email private String email;
    private String phone;
    @NotBlank private String message;
}
