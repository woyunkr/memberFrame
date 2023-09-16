package com.pmember.dto;

import com.pmember.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식을 입력해 주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    private static ModelMapper modelMapper = new ModelMapper();

    public static MemberFormDto of(Member member) {
        return modelMapper.map(member, MemberFormDto.class);
    }
}
