package com.sscanner.team.global.common.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.sscanner.team.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.sscanner.team.global.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final AmazonS3 s3Client;
    @Value("${sscanner.bucket.base-url}")
    private String baseUrl;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 이미지 url 생성
     * @param file
     * @return String - 이미지 url
     */
    @Override
    public String makeImgUrl(MultipartFile file) {
        isExistFile(file);

        String originalFileName = file.getOriginalFilename();

        String ext = separateExt(originalFileName);

        String randomName = UUID.randomUUID().toString();
        String fileName = randomName + "." + ext;

        uploadS3(file, fileName); // S3에 이미지 업로드

        return baseUrl + fileName;
    }

    /**
     * 파일 존재 여부 확인
     * @param file - 이미지 파일
     */
    @Override
    public void isExistFile(MultipartFile file) {
        if(file.isEmpty() && file.getOriginalFilename() != null) {
            throw new BadRequestException(NOT_EXIST_FILE);
        }
    }

    /**
     * 파일 원본 이름에서 확장자 분리하고 이미지 확장자인지 확인
     * @param originalFileName - 원본 파일 이름
     * @return 확장자
     */
    @Override
    public String separateExt(String originalFileName) {
        Set<String> exts = new HashSet<>(){{
            add("jpg");
            add("HEIC");
            add("jpeg");
            add("png");
            add("PNG");
            add("heic");
        }};

        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        if (!exts.contains(ext)) {
            throw new BadRequestException(BAD_FILE_EXTENSION);
        }
        return ext;
    }

    /**
     * S3에 이미지 파일 업로드
     * @param file - 이미지 파일
     * @param fileName - 새로 생성한 파일 이름
     */
    @Override
    public void uploadS3(MultipartFile file, String fileName) {
        try {
            ObjectMetadata objMeta = new ObjectMetadata();
            byte[] bytes = IOUtils.toByteArray(file.getInputStream()); // 파일의 데이터를 바이트로 읽습니다.
            objMeta.setContentType(file.getContentType()); // 파일의 타입을 지정합니다.예) text, image
            objMeta.setContentLength(bytes.length); // 파일의 크기

            // PutObjectRequest를 사용하여 지정된 S3 버킷에 파일을 업로드
            s3Client.putObject(
                    new PutObjectRequest(bucket, fileName, file.getInputStream(), objMeta)
                            .withCannedAcl(CannedAccessControlList.PublicRead)); // ACL을 PublicRead로 하여 공용으로 읽을 수 있도록 권한 부여
        } catch (IOException e) {
            throw new BadRequestException(FILE_UPLOAD_FAIL);
        }
    }

    /**
     * 바코드 이미지를 S3에 업로드하는 메서드 추가
     * @param barcodeBase64 바코드 이미지 (Base64 인코딩된 문자열)
     * @return S3에 업로드된 바코드 이미지의 URL
     */
    @Override
    public String uploadBarcodeToS3(String barcodeBase64) {
        // Base64로 인코딩된 바코드 이미지를 디코딩하여 InputStream으로 변환
        byte[] decodedImg = Base64.getDecoder().decode(barcodeBase64.getBytes(StandardCharsets.UTF_8));
        InputStream inputStream = new ByteArrayInputStream(decodedImg);

        // 랜덤한 파일 이름 생성
        String fileName = UUID.randomUUID() + ".png";

        // 메타데이터 설정
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType("image/png");
        objMeta.setContentLength(decodedImg.length);

        // S3에 바코드 이미지 업로드
        s3Client.putObject(
                new PutObjectRequest(bucket, fileName, inputStream, objMeta)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

        return baseUrl + "/" + fileName;
    }
}
