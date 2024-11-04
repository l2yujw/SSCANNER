package com.sscanner.team.trashcan.service;

import com.sscanner.team.trashcan.entity.Trashcan;
import com.sscanner.team.trashcan.requestDto.RegisterTrashcanRequestDto;
import com.sscanner.team.trashcan.requestDto.UpdateTrashcanRequestDto;
import com.sscanner.team.trashcan.responseDto.TrashcanResponseDto;
import com.sscanner.team.trashcan.responseDto.TrashcanWithImgResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface TrashcanService {


    TrashcanWithImgResponseDto registerTrashcan(RegisterTrashcanRequestDto requestDto, MultipartFile file);
    TrashcanWithImgResponseDto getTrashcanInfo(Long trashcanId);
    void deleteTrashcanInfo(Long trashcanId);
    TrashcanResponseDto updateTrashcanInfo(Long trashcanId, UpdateTrashcanRequestDto requestDto);
    Trashcan getTrashcanById(Long trashcanId);
    List<TrashcanResponseDto> getTrashcanByCoordinate(BigDecimal latitude, BigDecimal longitude);
    List<Trashcan> findAllTrashcans();
    void saveTrashcan(Trashcan trashcan);
}
