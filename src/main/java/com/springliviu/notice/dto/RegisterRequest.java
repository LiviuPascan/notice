package com.springliviu.notice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // Lombok generates getter methods
@Setter // Lombok generates setter methods
public class RegisterRequest {

    private String email; // User email for registration

    private String password; // Raw password to be hashed and stored
}
