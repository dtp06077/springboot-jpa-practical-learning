package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //Given
        Member member = new Member();
        member.setName("KimHuiSeong");
        //When
        Long saveId=memberService.join(member);
        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원예외() {
        //Given
        //이름이 같은 회원 추가
        Member member1 = new Member();
        member1.setName("min");

        Member member2 = new Member();
        member2.setName("min");
        //When
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
        //Then
        fail("예외 발생");
    }
}