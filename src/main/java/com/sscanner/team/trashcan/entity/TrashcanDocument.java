package com.sscanner.team.trashcan.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Document(indexName = "trashcan")
public class TrashcanDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String roadNameAddress;

    @Field(type = FieldType.Double)
    private BigDecimal latitude;

    @Field(type = FieldType.Double)
    private BigDecimal longitude;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public TrashcanDocument(Long id, String roadNameAddress, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
