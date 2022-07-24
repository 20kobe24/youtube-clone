package ryancarpio.youtubeclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ryancarpio.youtubeclone.model.Video;
import ryancarpio.youtubeclone.repository.VideoRepository;
/*
 *After implementing the file upload functionality
 * we need to call the upload file method of s3 service class, into the Video Service.
 *
 */
@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile){
        // Upload file to AWS S3
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        // Save Video Data to Database
        videoRepository.save(video);
    }
}
