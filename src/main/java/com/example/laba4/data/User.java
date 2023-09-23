package com.example.laba4.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date dateOfBirth;
}
