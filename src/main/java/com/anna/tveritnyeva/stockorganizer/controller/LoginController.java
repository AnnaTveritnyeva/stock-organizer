package com.anna.tveritnyeva.stockorganizer.controller;

import com.anna.tveritnyeva.stockorganizer.proxy.LoginProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginProxy loginProxy;

    @GetMapping("exists/email/{email}/password/{password}")
    public boolean userExists(@PathVariable String email, String password){
        return loginProxy.userExists(email,password);
    }
}
