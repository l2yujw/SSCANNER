package com.sscanner.team.trashcan.repository;

import com.sscanner.team.trashcan.entity.TrashcanDocument;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TrashcanDocumentRepository extends ElasticsearchRepository<TrashcanDocument, String> {


    List<TrashcanDocument> findByRoadNameAddressContaining(String roadNameAddress);
    List<TrashcanDocument> findByRoadNameAddress(String roadNameAddress);
}

