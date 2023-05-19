package com.example.til.동시성이슈.facade;

import com.example.til.동시성이슈.Service.StockService;
import com.example.til.동시성이슈.repository.RedisLockRepository;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    // 구현이 간단하다는 장점
    // but 스핀락 방식이기 때문에 redis에 부하를 줄 수 있음
    private RedisLockRepository redisLockRepository;

    private StockService stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long key, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(key)) {
            Thread.sleep(100);
        }

        try {
            stockService.decrease(key, quantity);
        } finally {
            redisLockRepository.unlock(key);
        }
    }
}
