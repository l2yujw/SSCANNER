package com.sscanner.team.points.common;

import lombok.Getter;

@Getter
public enum InitialPoint {
    DEFAULT(0),
    NEW_USER(10),
    PROMOTION(50),
    VIP_USER(100);

    private final int point;

    InitialPoint(int point) {
        this.point = point;
    }

}
