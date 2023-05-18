package com.example.til.동시성이슈.Service;

import com.example.til.동시성이슈.domain.Stock;
import com.example.til.동시성이슈.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptimisticLockStockService {

    private StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByOptimisticLock(id);
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
