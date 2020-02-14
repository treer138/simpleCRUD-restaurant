package com.example.eatgo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequeset {

    private String email;
    private String password;
}
