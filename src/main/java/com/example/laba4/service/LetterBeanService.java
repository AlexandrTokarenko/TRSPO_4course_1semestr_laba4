package com.example.laba4.service;

import com.example.laba4.bean.LetterBean;
import com.example.laba4.data.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterBeanService {

    private final UserService userService;

    public List<LetterBean> fromLetter(List<Letter> letters) {

        List<LetterBean> letterBeans = new ArrayList<>();

        for (Letter letter : letters) {
            letterBeans.add(fromLetter(letter));
        }

        return letterBeans;
    }

    public LetterBean fromLetter(Letter letter) {

        return LetterBean.builder()
                .id(letter.getId())
                .topic(letter.getTopic())
                .text(letter.getText())
                .dateOfShipment(letter.getDateOfShipment())
                .sender(userService.findById(letter.getSenderId()))
                .receiver(userService.findById(letter.getReceiverId())).build();
    }
}
