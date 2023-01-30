package com.tp3055.GestionColis.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String town;
    private Boolean isAdmin;
    private Boolean isSaver;
    private Boolean isSender;

}
