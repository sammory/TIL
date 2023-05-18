package com.example.til.동시성이슈.facade;

import com.example.til.동시성이슈.Service.StockService;
import com.example.til.동시성이슈.repository.LockRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NamedLockStockFacade {
    // PessimisticLock은 타임아웃을 구현하기 힘들지만 NamedLockStock은 쉽게 가능
    // 데이터 삽입 시 정합성을 맞춰야 하는 경우 사용
    // but 트랜잭션 종료 시 락해제와 세션관리를 잘해줘야 하기 때문에 주의해서 사용
    private final LockRepository lockRepository;
    private final StockService stockService;

    public NamedLockStockFacade(LockRepository lockRepository, StockService stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        try {
            lockRepository.getLock(id.toString());
            stockService.decrease(id, quantity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
