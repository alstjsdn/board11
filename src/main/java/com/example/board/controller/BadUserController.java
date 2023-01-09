package com.example.board.controller;

import com.example.board.dto.BadUserDto;
import com.example.board.service.BadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BadUserController {
    private final BadUserService badUserService;

    @GetMapping("/baduser")
    public String badUserBoard(Model model) {
        List<BadUserDto> badUserDtoList = badUserService.BadUserList();
        model.addAttribute("badUserList",badUserDtoList);

        return "badUserList";

    }
    @GetMapping("/baduserwrite")
    public String badUserWrite() {
        return "badUserWrite";
    }

    @PostMapping("/baduserwrite")
    public String badUserWrite(BadUserDto badUserDto) {
        badUserService.saveBadUser(badUserDto);

        return "redirect:/";
    }

}
