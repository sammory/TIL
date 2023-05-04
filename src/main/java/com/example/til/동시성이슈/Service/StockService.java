package com.example.til.동시성이슈.Service;

import com.example.til.동시성이슈.domain.Stock;
import com.example.til.동시성이슈.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    // Stock에 대한 CRUD가 가능해야하기 때문에 stockRepository 를 필드로 갖음
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    // 재고감소 메소드를 만들기위해 Stock 아이디와 수량을 받아서 생성
    //@Transactional --> decrease 메서드가 끝났지만 트랜잭션이 종료되기전에 decrease 메서드를 실행하게 되면 정확한 데이터를 가져가지 못함
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);

    }
}
