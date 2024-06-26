package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //멤버 저장
    public void save(Member member) {
        em.persist(member);
    }
    //pk id를 통한 멤버 검색
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    //멤버 리스트 검색
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    //이름을 통한 멤버 리스트 검색
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",
                Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
