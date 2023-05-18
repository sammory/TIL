package com.example.til.동시성이슈.facade;

import com.example.til.동시성이슈.Service.OptimisticLockStockService;
import org.springframework.stereotype.Service;

@Service
// OptimisticLock은 실패 시 재시도를 해줘야 함
public class OptimisticLockStockFacade {
    private OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFacade(OptimisticLockStockService optimisticLockStockService) {
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                optimisticLockStockService.decrease(id, quantity);

                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
