package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostNewAccount {
    private Long id;
    private String account_type;
    private float balance;
}
