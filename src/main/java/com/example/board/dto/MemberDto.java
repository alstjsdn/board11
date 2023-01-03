package com.example.board.dto;

import com.example.board.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {



    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;


    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8,max = 16, message = "비밀번호는 8자이상 16자 이하로 입력해주세요")
    private String password;




}
