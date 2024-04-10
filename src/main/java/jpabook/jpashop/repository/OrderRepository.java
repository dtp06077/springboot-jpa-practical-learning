package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    //주문 등록
    public void save(Order order) {
        em.persist(order);
    }

    //주문 조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }
}
