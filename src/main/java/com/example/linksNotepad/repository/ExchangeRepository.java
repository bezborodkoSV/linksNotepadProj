package com.example.linksNotepad.repository;

import com.example.linksNotepad.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRates, Long> {
    ExchangeRates findExchangeRatesByCcyAndBaseCcy(String ccy, String baseCcy);
}
