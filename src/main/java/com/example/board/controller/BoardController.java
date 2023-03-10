package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.dto.CommentDto;
import com.example.board.result.PageResult;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController{

    private final BoardService boardService;
    private final CommentService commentService;


    @GetMapping(value = "/board")
    public String List(Model model,@PageableDefault(page = 0,size = 5) Pageable pageable) {
        PageResult<BoardDto> pageResult = boardService.setBoarderList(pageable);
        model.addAttribute("pageable",pageable);
        model.addAttribute("boardList",pageResult.getContent());
        model.addAttribute("pageResult",pageResult);

        return "List.html";
    }

    @GetMapping(value = "/userboard")
    public String User(Model model) {
        List<BoardDto> boardDtoList = boardService.UserBoard();

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
        model.addAttribute("boardDto", boardDto);

        List<CommentDto> commentList = commentService.getComments(id);
        model.addAttribute("commentList", commentList);
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
    @PostMapping(value = "/board/comment")
    public String comment(CommentDto commentDto) {
        commentService.saveComment(commentDto);
        return "redirect:/list/" + commentDto.getBoardId();
    }
}
