package com.anna.tveritnyeva.stockorganizer.advice;

import com.anna.tveritnyeva.stockorganizer.exception.CategoryException;
import com.anna.tveritnyeva.stockorganizer.exception.ItemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ListsAdvice {

    @ExceptionHandler(value = {CategoryException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleCategoryException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {ItemException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleItemException(Exception e) {
        return e.getMessage();
    }
}
