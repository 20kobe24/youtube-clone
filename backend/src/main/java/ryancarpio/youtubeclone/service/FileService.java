package ryancarpio.youtubeclone.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file);
    //needed if it's not going to be uploaded to AWS S3
}
