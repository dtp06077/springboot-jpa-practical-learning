package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") //기본키
    private Long id;

    //LAZY로 설정하지 않으면 n+1 쿼리 에러 발생
    @ManyToOne(fetch = FetchType.LAZY) //다대일 관계
    @JoinColumn(name = "member_id") //외래키
    private Member member; //주문 회원

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<>(); //주문 물품

    @OneToOne(fetch = FetchType.LAZY)//일대일 관계
    @JoinColumn(name = "delivery_id")//외래키
    private Delivery delivery; //배송 정보

    private LocalDateTime orderDate; //주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]
}
