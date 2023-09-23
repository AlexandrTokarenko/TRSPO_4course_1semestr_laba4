package com.example.laba4.service;

import com.example.laba4.dao.LetterDao;
import com.example.laba4.data.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterDao letterDao;

    public List<Letter> findAllBySenderId(int senderId) {
        return letterDao.findAllBySenderId(senderId);
    }

    public List<Letter> findAllByReceiverId(int receiverId) {
        return letterDao.findAllByReceiverId(receiverId);
    }

    public void save(Letter letter) {

        letter.setDateOfShipment(new Date(System.currentTimeMillis()));
        letterDao.save(letter);
    }

    public Letter findById(int letterId) {

        return letterDao.findById(letterId);
    }

    public void updateWithoutSenderId(Letter letter) {

        letterDao.updateWithoutSenderId(letter);
    }

    public void deleteById(int letterId) {

        letterDao.deleteById(letterId);
    }

    public List<Letter> findAllBySenderIdOrderByDate(int senderId) {
        return letterDao.findAllBySenderIdOrderByDate(senderId);
    }

    public List<Letter> findAllByReceiverIdOrderByDate(int receiverId) {

        return letterDao.findAllByReceiverIdOrderByDate(receiverId);
    }
}
