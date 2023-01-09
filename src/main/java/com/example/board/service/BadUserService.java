package com.example.board.service;


import com.example.board.dto.BadUserDto;
import com.example.board.entity.BadUser;
import com.example.board.entity.Member;
import com.example.board.repository.BadUserRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadUserService {
    private final BadUserRepository badUserRepository;
    private final MemberRepository memberRepository;


    public void saveBadUser(BadUserDto badUserDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUser = authentication.getName();
        Member member=memberRepository.findByEmail(loginUser);
        BadUser badUser=new BadUser();
        badUser.setId(badUserDto.getId());
        badUser.setUser(badUserDto.getUser());
        badUser.setMember(member);
        badUser.setContent(badUserDto.getContent());
        badUserRepository.save(badUser);
    }


    public List<BadUserDto> BadUserList() {
        List<BadUser> badUserList =badUserRepository.findAllByOrderByIdDesc();
        List<BadUserDto> badUserDtoList = new ArrayList<>();

        for(BadUser baduser:badUserList) {
            BadUserDto badUserDto = BadUserDto.builder()
                    .id(baduser.getId())
                    .user(baduser.getUser())
                    .content(baduser.getUser())
                    .createdDate(baduser.getCreatedDate())
                    .build();
            badUserDtoList.add(badUserDto);
        }
        return badUserDtoList;

    }

    public BadUserDto DetailList(Long id) {
        Optional<BadUser> badUser = badUserRepository.findById(id);

        if(badUser.isPresent()){
            BadUser buser = badUser.get();
            BadUserDto badUserDto = BadUserDto.builder()
                    .id(buser.getId())
                    .user(buser.getUser())
                    .content(buser.getContent())
                    .build();

            return badUserDto;

        }
        return null;

    }

}
