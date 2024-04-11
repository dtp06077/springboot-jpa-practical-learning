package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
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
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    @DisplayName("상품 주문")
    public void 상품주문() {
        //Given
        Member member = createMember();
        Item item = createBook("spring JPA", 10000, 10);
        int orderCount = 2;

        //When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        //==상품 주문시 상태는 ORDER==//
        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        //==주문한 상품 종류 수가 정확해야 한다==//
        assertEquals(1, getOrder.getOrderItems().size());
        //==주문 가격은 가격*수량==//
        assertEquals(orderCount*item.getPrice(), getOrder.getTotalPrice());
        //==주문 수량만큼 재고가 줄어야 한다==//
        assertEquals(8, item.getStockQuantity());
    }

    @Test
    @DisplayName("주문 취소")
    public void 주문취소() {
        //Given
        Member member = createMember();
        Item item = createBook("spring JPA", 20000, 10);
        int orderCount = 4;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //When
        orderService.cancelOrder(orderId);

        //Then
        //주문을 취소해도 주문 엔티티는 남아있음
        Order order = orderRepository.findOne(orderId);
        //==주문 취소 시 상태는 CANCEL 이다==//
        assertEquals(OrderStatus.CANCEL, order.getStatus());
        //==주문이 취소된 상품은 그만큼 재고가 증가해야 한다==//
        assertEquals(10,item.getStockQuantity());
    }

    @Test
    @DisplayName("재고 수량 초과")
    public void 재고수량초과() {
        //Given
        Member member = createMember();
        Item item = createBook("spring JPA", 30000, 5);
        int orderCount = 10;

        //When
        try {
            //재고보다 많은 수량으로 예외가 발생해야 함.
            orderService.order(member.getId(), item.getId(), orderCount);
        } catch (NotEnoughStockException e) {
            return;
        }

        //Then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }
    //==중복되는 회원, 아이템 생성 코드 만들어놓기==//
    private Member createMember() {
        Member member= new Member();
        member.setName("KimHuiSeong");
        member.setAddress(new Address("대전", "태평로", "53"));
        em.persist(member);
        return  member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
}