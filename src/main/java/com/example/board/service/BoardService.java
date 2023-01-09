package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MemberRepository memberRepository;

    public List<BoardDto> setBoarderList() {
        List<Board> boards = boardRepository.findAllByOrderByIdDesc();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boards) {
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUser = authentication.getName();
        Member member=memberRepository.findByEmail(loginUser);
        Board board=new Board();
        board.setId(boardDto.getId());
        board.setWriter(member.getName());
        board.setMember(member);
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);

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
                    .build();

            return boardDto;
        }
        return null;
    }

    public List<BoardDto> UserBoard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUserEmail = authentication.getName();

        List<Board> boardList = boardRepository.findByWriterOrderByWriterDesc(loginUserEmail);
        List<BoardDto> boardDtoList =new ArrayList<>();
        for(Board board:boardList) {
            BoardDto boardDto= BoardDto.builder()
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

    public void deletePost(Long id) {
        Optional<Board> optboard = boardRepository.findById(id);
        if(optboard.isPresent()){
            Board board = optboard.get();
            boardRepository.deleteById(id);
        }
    }





}
