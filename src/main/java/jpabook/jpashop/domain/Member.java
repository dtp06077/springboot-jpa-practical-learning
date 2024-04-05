package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") //기본키
    private Long id;

    private String name; //회원 이름

    @Embedded
    private Address address; //회원 주소

    @OneToMany(mappedBy = "member") //일대다 관계
    private List<Order> orders = new ArrayList<>();
}
