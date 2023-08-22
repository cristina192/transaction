package com.transaction.transaction.dtos;

import com.transaction.transaction.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, UserType userType,String password) {
}
