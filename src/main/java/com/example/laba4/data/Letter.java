package com.example.laba4.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Letter {
    private int id;
    private int senderId;
    private int receiverId;
    private String topic;
    private String text;
    private Date dateOfShipment;
}
