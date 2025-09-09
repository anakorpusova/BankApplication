package org.anafreaja.bank_application.dto;

public record AuthRequest(String email, String password){
    public AuthRequest(){
        this ("", "");
    }
}

