package com.elastic.poc.service.impl;

import com.elastic.poc.annotation.LogInputApi;
import com.elastic.poc.constants.TradeSizeEnum;
import com.elastic.poc.exception.ValidationFailedException;
import com.elastic.poc.model.payloads.Trade;
import com.elastic.poc.model.trade.TradeEntity;
import com.elastic.poc.model.trade.TradeFactory;
import com.elastic.poc.model.trade.TradeType;
import com.elastic.poc.repo.TradeRepository;
import com.elastic.poc.service.ITradeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeManagerImpl implements ITradeManager {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    @Async
    @LogInputApi
    public void manyInsert(TradeType tradeType) {
        TradeEntity tradeEntity = TradeFactory.getTradeEntityType(tradeType);
        Duration duration = Duration.ZERO;

        for (int i = 0; i < tradeType.getCount(); i++) {
            Trade tradeToBeInserted = tradeEntity.getTrade(i);
            Instant instantStart = Instant.now();
            tradeRepository.save(tradeToBeInserted);
            Instant instantEnd = Instant.now();
            duration = duration.plus(Duration.between(instantStart, instantEnd));
        }
        log.info("Time to Insert All Records in Seconds :  {} and In Milliseconds : {} ", duration.getSeconds(), duration.toMillis());
        log.info("Total Trades Inserted in Bulk of Type {} : {}", TradeSizeEnum.valueOf(tradeType.getPayloadSize()), tradeType.getCount());
    }

    @LogInputApi
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Async
    @LogInputApi
    public void findAllUsingCursor(int totalRecordCount, int batchSize) {
        Duration duration = Duration.ZERO;

        try {
            for (int i = 0; i < totalRecordCount / batchSize; i++) {
                final Pageable pageableRequest = PageRequest.of(0, batchSize);
                Instant instantStart = Instant.now();
                tradeRepository.findAll(pageableRequest);
                Instant instantEnd = Instant.now();
                duration = duration.plus(Duration.between(instantStart, instantEnd));
            }
            log.info("Time to Fetch All Records in Seconds :  {} and In Milliseconds : {} ", duration.getSeconds(), duration.toMillis());
            log.info("Total Trades Fetched : {} of batch size: {}", totalRecordCount, batchSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @LogInputApi
    public void findAll() {
        //It is not a good practise to use findAll in elastic search elastic if record count > 10,000
    }

    @LogInputApi
    public Page<Trade> findAllWithPaginationAndSorting(Pageable pageable) {
        return tradeRepository.findAll(pageable);
    }


    @LogInputApi
    public Optional<Trade> getADocumentById(String id) {
        try {
            return tradeRepository.findById(id);
        } catch (Exception e) {
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @LogInputApi
    public long findCount() {
        try {
            return tradeRepository.count();
        } catch (Exception e) {
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    @LogInputApi
    public List<Trade> searchByExternalRefNumber(Integer externalRefNumber) {
        try {
            PageRequest pageable = PageRequest.of(0, 100);
            return tradeRepository.findByExternalReference(externalRefNumber, pageable).stream().toList();
        } catch (Exception e) {
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    @LogInputApi
    public List<Trade> searchByContractId(Integer contractId) {
        try {
            PageRequest pageable = PageRequest.of(0, 100);
            return tradeRepository.findByContractId(contractId, pageable).stream().toList();
        } catch (Exception e) {
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    @LogInputApi
    @Async
    public void searchByGlobalKey(String globalKey) {
        try {
            PageRequest pageable = PageRequest.of(0, 100);
            tradeRepository.findByGlobalKey(globalKey, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @LogInputApi
    public void searchByMetaGlobalKey(String globalKey, Integer size, String sort) {
        try {
            PageRequest pageable = PageRequest.of(0, size, Sort.by(sort).ascending());
            tradeRepository.findByMetaGlobalKey(globalKey, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
