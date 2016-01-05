package com.ej.router.controller;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Class for handling bad requests to {@link EagleController}.
 */
@RestController
public class ErrorHandler implements ErrorController {
    /**
     * @return {@code String} the message
     */
    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<EagleResponse> error() {
        EagleResponse response = new EagleResponse();
        response.setConvertedAmount("");
        response.setMessage("Sorry,the server couldn't accept such request, " +
                "please,try again.");
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
