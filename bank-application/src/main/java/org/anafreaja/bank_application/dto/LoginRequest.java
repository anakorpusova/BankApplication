package org.anafreaja.bank_application.dto;

public record LoginRequest (String email, String password){
    public LoginRequest(){
        this ("", "");
    }
}

