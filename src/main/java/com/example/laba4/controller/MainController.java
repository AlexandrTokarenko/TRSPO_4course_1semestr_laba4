package com.example.laba4.controller;

import com.example.laba4.data.Letter;
import com.example.laba4.data.User;
import com.example.laba4.service.LetterService;
import com.example.laba4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final LetterService letterService;

    @GetMapping()
    public String index(Model model) {

        List<User> users = userService.findAll();

        model.addAttribute("users",users);
        return "index";
    }

    @GetMapping("/all-letters/{userId}")
    public String showAllLetters(Model model, @PathVariable int userId) {

        List<Letter> sentLetters = letterService.findAllBySenderIdOrderByDate(userId);

        List<Letter> receivedLetters = letterService.findAllByReceiverIdOrderByDate(userId);

        List<Letter> letters = Stream.concat(sentLetters.stream(), receivedLetters.stream()).toList();

        model.addAttribute("userId",userId);
        model.addAttribute("letters",letters);
        return "letters";
    }

    @GetMapping("/send-letter/{userId}")
    public String sendLetter(Model model, @PathVariable int userId) {

        model.addAttribute("senderId",userId);
        model.addAttribute("letter",new Letter());

        return "add-letter";
    }

    @PostMapping("/add-letter/{senderId}")
    public String saveLetter(Model model, @PathVariable int senderId, @ModelAttribute Letter letter) {

        if (userService.findById(letter.getReceiverId()) != null) {

            letter.setSenderId(senderId);

            letterService.save(letter);
        }
        return "redirect:/all-letters/"+ senderId;
    }

    @GetMapping("/update-letter/{letterId}")
    public String showUpdateLetterWindow(@PathVariable int letterId, Model model) {

        model.addAttribute("letter",letterService.findById(letterId));

        return "update-letter";
    }

    @PostMapping("/update-letter/{letterId}")
    public String updateLetter(@PathVariable int letterId, @ModelAttribute Letter letter) {

        if (userService.findById(letter.getReceiverId()) != null) {
            letter.setId(letterId);
            letterService.updateWithoutSenderId(letter);
        }
        return "redirect:/";
    }

    @GetMapping("/delete-letter/{letterId}/{userId}")
    public String deleteLetter(@PathVariable int letterId, @PathVariable int userId) {

        letterService.deleteById(letterId);

        return "redirect:/all-letters/" + userId;
    }
}
