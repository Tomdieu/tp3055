package com.tp3055.GestionColis.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;
    private String username=null;
    private String firstname=null;
    private String lastname;
    private String password;
    private String town;
    private Boolean isAdmin=false;
    private Boolean isSaver=false;
    private Boolean isSender=false;

}
