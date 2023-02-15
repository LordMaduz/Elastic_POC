package com.elastic.poc.repo;

import com.elastic.poc.model.payloads.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends ElasticsearchRepository<Trade, String> {

    @Query("{\"match\": {\"externalRefNumber\": {\"query\": ?0}}}")
    Page<Trade> findByExternalReference(Integer reference, Pageable pageable);

    @Query("{\"match\": {\"contractDetails.contractId\": {\"query\": ?0}}}")
    Page<Trade> findByContractId(Integer contractId, Pageable pageable);

    @Query("{\"match\": {\"contractDetails.description.meta.globalKey\": {\"query\": \"?0\"}}}")
    Page<Trade> findByGlobalKey(String key, Pageable pageable);

    @Query("{\"match\": {\"meta.globalKey\": {\"query\": \"?0\"}}}")
    Page<Trade> findByMetaGlobalKey(String key, Pageable pageable);

}
