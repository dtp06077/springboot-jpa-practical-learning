package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    private Address address;

    //EnumType.ORDINAL을 사용하면 숫자로 순서를 나타내기에 삽입시 에러가 발생
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //배송 상태 [READY, COMP]
}
