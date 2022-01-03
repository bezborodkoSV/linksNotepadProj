package com.example.linksNotepad.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name = "exchange_rates")
public class ExchangeRates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @JsonProperty("ccy")
    private String ccy;
    @Column
    @JsonProperty("base_ccy")
    private String baseCcy;
    @Column
    @JsonProperty("buy")
    private float buy;
    @Column
    @JsonProperty("sale")
    private float sale;

    public ExchangeRates(String ccy, String baseCcy, float buy, float sale) {
        this.ccy = ccy;
        this.baseCcy = baseCcy;
        this.buy = buy;
        this.sale = sale;
    }
}
