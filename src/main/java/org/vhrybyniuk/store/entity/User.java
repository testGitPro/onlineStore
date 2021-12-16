package org.vhrybyniuk.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String user_name;
    private String email;
    private String password;


    public User(String user_name,String email, String password) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }


    public  enum  ROLE {
        USER, ADMIN
    }
}
