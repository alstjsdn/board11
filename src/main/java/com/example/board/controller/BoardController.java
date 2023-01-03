package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping(value = "/board")
    public String List(Model model) {
        List<BoardDto> boardDtoList = boardService.setBoarderList();
        model.addAttribute("boardList",boardDtoList);

        return "List.html";
    }

    @GetMapping(value = "/write")
    public String Write(){
        return "Write.html";
    }
    @PostMapping("/write")
    public String Write(BoardDto dto){
        boardService.savePost(dto);

        return "redirect:/board/";

    }
    @GetMapping(value = "/list/{no}")
    public String Detail( Model model, @PathVariable("no") Long id){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto",boardDto);

        return "Detail.html";

    }
    @GetMapping(value = "/board/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return "update.html";
    }
    @PostMapping(value = "/board/edit")
    public String edit(BoardDto dto){
        boardService.savePost(dto);
        return "redirect:/board/";
    }

    @PostMapping(value = "/board/delete")
    public String Delete(Long id){
        boardService.deletePost(id);

        return "redirect:/board/";
    }


}