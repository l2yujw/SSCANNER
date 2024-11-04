package com.sscanner.team.trashcan.service;

import com.sscanner.team.global.configure.aop.TimeTrace;
import com.sscanner.team.trashcan.entity.Trashcan;
import com.sscanner.team.trashcan.entity.TrashcanDocument;
import com.sscanner.team.trashcan.repository.TrashcanDocumentRepository;
import com.sscanner.team.trashcan.responseDto.TrashcanSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TrashcanDocumentService {

    private final TrashcanDocumentRepository documentRepository;

    public TrashcanDocument save(TrashcanDocument document) {
        return documentRepository.save(document);
    }

    @TimeTrace
    public List<TrashcanSearchResponseDto> findByRoadNameAddress(String roadNameAddress) {

        return documentRepository.findByRoadNameAddressContaining(roadNameAddress)
                .stream()
                .map(TrashcanSearchResponseDto::from)  // 팩토리 메서드를 사용하여 변환
                .toList();
    }

    public void saveTrashcanDocuments(List<Trashcan> trashcans) {
        List<TrashcanDocument> trashcanDocuments = convertTrashcansToDocuments(trashcans);
        documentRepository.saveAll(trashcanDocuments);
    }

    private List<TrashcanDocument> convertTrashcansToDocuments(List<Trashcan> trashcans) {

        List<TrashcanDocument> documents = new ArrayList<>();

        for (Trashcan trashcan : trashcans) {

            TrashcanDocument document = TrashcanDocument.builder()
                    .id(trashcan.getId())
                    .roadNameAddress(trashcan.getRoadNameAddress())
                    .longitude(trashcan.getLongitude())
                    .latitude(trashcan.getLatitude())
                    .build();

            documents.add(document);
        }
        return documents;
    }



}

