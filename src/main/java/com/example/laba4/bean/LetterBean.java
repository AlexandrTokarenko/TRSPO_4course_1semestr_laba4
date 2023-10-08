package com.example.laba4.bean;

import com.example.laba4.data.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LetterBean {

    private int id;
    private User sender;
    private User receiver;
    private String topic;
    private String text;
    private Date dateOfShipment;
}
