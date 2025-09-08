package org.example.exceptions;

public class AccountDuplicationException extends Throwable {
    public AccountDuplicationException(String message) {
        super(message);
    }
}
