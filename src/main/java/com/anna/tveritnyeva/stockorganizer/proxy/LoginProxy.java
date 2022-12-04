package com.anna.tveritnyeva.stockorganizer.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-organizer-login", url = "http://localhost:8081/users")
public interface LoginProxy {

    @GetMapping("user/email/{email}/password/{password}")
    boolean userExists(@PathVariable String email, @PathVariable String password);
}