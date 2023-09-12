package com.pmember.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

    private Long id;

    private String name;

    private String email;

    private String password;
}
