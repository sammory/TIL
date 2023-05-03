package com.example.til.동시성이슈.Service;

import com.example.til.동시성이슈.domain.Stock;
import com.example.til.동시성이슈.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    // 테스트를 위해 임의로 데이터를 넣어줌
    @BeforeEach
    public void before() {
        Stock stock = new Stock(1L, 100L);

        stockRepository.saveAndFlush(stock);
    }
    // 테스트가 정상동작 했으면 데이터 삭제
    @AfterEach
    public void after() {
        stockRepository.deleteAll();
    }

    @Test
    public void stock_decrease() {
        //given
        stockService.decrease(1L, 1L);
        //when
        Stock stock = stockRepository.findById(1L).orElseThrow();
        //then
        // 100 - 1 = 99
        assertEquals(99, stock.getQuantity());
    }
}