package com.sscanner.team.points.service;

import com.sscanner.team.points.common.InitialPoint;
import com.sscanner.team.points.entity.UserPoint;
import com.sscanner.team.points.dto.requestdto.PointRequestDto;
import com.sscanner.team.points.dto.responsedto.PointWithUserIdResponseDto;
import com.sscanner.team.user.entity.User;

import java.util.Set;

public interface PointService {
    void save(User user, InitialPoint initialPoint);
    PointWithUserIdResponseDto getCachedPoint();
    PointWithUserIdResponseDto addPoint(String userId, PointRequestDto pointRequestDto);
    Integer fetchCachedPoint(String userId);
    UserPoint findByUserId(String userId);
    void markUserForBackup(String userId);
    void updateUserPoint(UserPoint userPoint);
    void removeBackupFlag(String userId);
    void decrementPoint(String userId, int productPrice);
    Set<String> getFlaggedUsersForBackup();
    Integer getPoint(String userId);
    void resetDailyPointsInCache();
}
