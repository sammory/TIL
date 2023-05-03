package com.example.til.동시성이슈.repository;

import com.example.til.동시성이슈.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

// 재고에 대한 CRUD를 위해 생성
public interface StockRepository extends JpaRepository<Stock, Long> {
}
