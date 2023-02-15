package com.elastic.poc.service;


import com.elastic.poc.model.payloads.Trade;
import com.elastic.poc.model.trade.TradeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITradeManager  {
    Trade save(Trade trade);

    void manyInsert(TradeType tradeType);

    void findAll();

    void findAllUsingCursor(int totalRecordCount, int batchSize);

    Page<Trade> findAllWithPaginationAndSorting(Pageable pageable);

    Optional<Trade> getADocumentById(String id);

    long findCount();

    List<Trade> searchByExternalRefNumber(Integer externalRefNumber);

    void searchByMetaGlobalKey(String globalKey, Integer size, String sort);

    List<Trade> searchByContractId(Integer contractId);

    void searchByGlobalKey(String globalKey);


}
