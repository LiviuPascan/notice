package com.springliviu.notice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // Lombok generates getter methods
@Setter // Lombok generates setter methods
public class LoginRequest {

    private String email; // User email used to log in

    private String password; // Raw password used for authentication
}
