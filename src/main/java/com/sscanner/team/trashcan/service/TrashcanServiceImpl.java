package com.sscanner.team.trashcan.service;

import com.sscanner.team.global.configure.aop.TimeTrace;
import com.sscanner.team.global.exception.BadRequestException;
import com.sscanner.team.global.utils.GeoUtils;
import com.sscanner.team.trashcan.entity.Trashcan;
import com.sscanner.team.trashcan.entity.TrashcanImg;
import com.sscanner.team.trashcan.repository.TrashcanRepository;
import com.sscanner.team.trashcan.requestDto.RegisterTrashcanRequestDto;
import com.sscanner.team.trashcan.requestDto.UpdateTrashcanRequestDto;
import com.sscanner.team.trashcan.responseDto.TrashcanResponseDto;
import com.sscanner.team.trashcan.responseDto.TrashcanWithImgResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.sscanner.team.global.exception.ExceptionCode.NOT_EXIST_TRASHCAN_ID;
import static com.sscanner.team.global.exception.ExceptionCode.NOT_FOUND_NEARBY_TRASHCANS;

@RequiredArgsConstructor
@Service
public class TrashcanServiceImpl implements TrashcanService {

    //쓰레기통 위도, 경도 검색 범위 설정 미터단위
    private static final double RADIUS_FOR_SEARCH_METERS = 200.0;

    private final TrashcanRepository trashcanRepository;
    private final TrashcanImgService trashcanImgService;


    @Override
    @Transactional
    public TrashcanWithImgResponseDto registerTrashcan(RegisterTrashcanRequestDto requestDto, MultipartFile file) {

        Trashcan trashcan = requestDto.toEntity();
        trashcanRepository.save(trashcan);

        String imgUrl = trashcanImgService.uploadTrashcanImg(file);
        TrashcanImg trashcanImg = trashcanImgService.saveTrashcanImg(trashcan.getId(), imgUrl);

        return TrashcanWithImgResponseDto.of(trashcan, trashcanImg);
    }



    @Override
    public TrashcanWithImgResponseDto getTrashcanInfo(Long trashcanId) {

        Trashcan trashcan = getTrashcanById(trashcanId);
        TrashcanImg trashcanImg = trashcanImgService.getTrashcanImg(trashcanId);

        return TrashcanWithImgResponseDto.of(trashcan, trashcanImg);
    }

    public Trashcan getTrashcanById(Long trashcanId) {
        return trashcanRepository.findById(trashcanId)
                .orElseThrow(() -> new BadRequestException(NOT_EXIST_TRASHCAN_ID));
    }

    @Override
    public List<TrashcanResponseDto> getTrashcanByCoordinate(BigDecimal latitude, BigDecimal longitude) {

        BigDecimal[] boundingBox = GeoUtils.getBoundingBox(latitude, longitude, RADIUS_FOR_SEARCH_METERS);

        BigDecimal minLat = boundingBox[0];
        BigDecimal maxLat = boundingBox[1];
        BigDecimal minLon = boundingBox[2];
        BigDecimal maxLon = boundingBox[3];

        List<Trashcan> trashcans = getTrashcansByLatAndLon(minLat, maxLat, minLon, maxLon);

        return convertToTrashcanResponses(trashcans);
    }

    @Override
    public List<Trashcan> findAllTrashcans() {

        return trashcanRepository.findAllByDeletedAtIsNull();
    }



    @TimeTrace
    private List<Trashcan> getTrashcansByLatAndLon(BigDecimal minLat, BigDecimal maxLat, BigDecimal minLon, BigDecimal maxLon) {
        return trashcanRepository.findTrashcansWithinBoundingBox(minLat, maxLat, minLon, maxLon)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_NEARBY_TRASHCANS));
    }

    private List<TrashcanResponseDto> convertToTrashcanResponses(List<Trashcan> trashcans) {
        List<TrashcanResponseDto> trashcanResponseDtos = new ArrayList<>();
        for (Trashcan trashcan : trashcans) {
            trashcanResponseDtos.add(TrashcanResponseDto.from(trashcan));
        }
        return trashcanResponseDtos;
    }




    @Transactional
    public void deleteTrashcanInfo(Long trashcanId) {

        try {
            trashcanRepository.deleteById(trashcanId);
        } catch (IllegalArgumentException e){
            throw new BadRequestException(NOT_EXIST_TRASHCAN_ID);
        }
    }

    @Transactional
    public TrashcanResponseDto updateTrashcanInfo(Long trashcanId, UpdateTrashcanRequestDto requestDto) {
        Trashcan trashcan = getTrashcanById(trashcanId);
        trashcan.updateInfo(requestDto);
        return TrashcanResponseDto.from(trashcan);
    }

    @Override
    public void saveTrashcan(Trashcan trashcan) {
        trashcanRepository.save(trashcan);
    }
}
