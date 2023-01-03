package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> setBoarderList() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board:boards){
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }


    public void savePost(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity()).getId();

    }


    public BoardDto getPost(Long id){
        Optional<Board> boardWrapper = boardRepository.findById(id);


        if(boardWrapper.isPresent()) {
            Board board = boardWrapper.get();
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();

            return boardDto;
        }
        return null;
    }

    public void deletePost(Long id) {
        Optional<Board> optboard = boardRepository.findById(id);
        if(optboard.isPresent()){
            Board board = optboard.get();
            boardRepository.deleteById(id);
        }
    }




}
