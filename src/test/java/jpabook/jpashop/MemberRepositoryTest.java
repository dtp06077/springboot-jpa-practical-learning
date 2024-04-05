package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

//    @Autowired MemberRepository memberRepository;
//
//
//    @Test
//    @DisplayName("회원 리포지토리 동작 테스트")
//    @Transactional
//    @Rollback(false) //데이터베이스가 롤백되지 않음(테스트에서 사용한 멤버 데이터가 db에 남아있음)
//    public void testMember() {
//        //given
//        Member member=new Member();
//        member.setUserName("KimHuiSeong");
//        //when
//        Long memberId=memberRepository.save(member);
//        Member findMember=memberRepository.find(memberId);
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
//        Assertions.assertThat(findMember).isEqualTo(member);
//        System.out.println("findMember == member -> " + (findMember==member));
//    }
}