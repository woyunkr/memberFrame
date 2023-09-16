package com.pmember.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "mypage")
public class MyPage {

    @Id
    @Column(name = "myPage_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static MyPage createMyPage(Member member) {
        MyPage myPage = new MyPage();
        myPage.setMember(member);
        return myPage;
    }

}
