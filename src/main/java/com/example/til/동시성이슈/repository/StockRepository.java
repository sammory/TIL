package com.example.til.동시성이슈.repository;

import com.example.til.동시성이슈.domain.Stock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

// 재고에 대한 CRUD를 위해 생성
public interface StockRepository extends JpaRepository<Stock, Long> {

    // PessimisticLock은 충돌이 빈번하게 일어날 경우 사용
    // but lock을 잡기 때문에 성능감소
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithPessimisticLock(Long id);
}
