package com.sscanner.team.trashcan.responseDto;

import com.sscanner.team.trashcan.entity.TrashcanDocument;

import java.math.BigDecimal;

public record TrashcanSearchResponseDto(
        Long id,
        BigDecimal latitude,
        BigDecimal longitude
) {
    public static TrashcanSearchResponseDto from(TrashcanDocument trashcanDocument) {
        return new TrashcanSearchResponseDto(
                trashcanDocument.getId(),
                trashcanDocument.getLatitude(),
                trashcanDocument.getLongitude()
        );
    }
}
