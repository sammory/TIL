package com.example.til.동시성이슈.domain;

import jakarta.persistence.*;

@Entity
public class Stock {
    // id. productId, quantity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long quantity;

    // optimistic lock 사용시
    @Version
    private Long version;

    // 생성자 추가
    public Stock() {
    }

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    // 수량에 대한 getter 생성
    public Long getQuantity() {
        return quantity;
    }
    // 재고감소 메소드
    public void decrease(Long quantity) {
        if (this.quantity - quantity < 0) {
            throw new RuntimeException("foo");
        }

        this.quantity = this.quantity - quantity;
    }
}
