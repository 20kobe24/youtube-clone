package ryancarpio.youtubeclone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
//generates argumented constructor 20-21
public class S3Service implements FileService{

    public static final String BUCKET_NAME = "ryan-youtubeclone";
    private final AmazonS3Client awsS3Client;

    @Override
    public String uploadFile(MultipartFile file){
        //prepare a key
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        //create a UUID
        var key = UUID.randomUUID().toString() + "." + filenameExtension;
        //create object metadata
        var metadata = new ObjectMetadata();

        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        //upload file to AWS S3
        try {
            awsS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
        }catch (IOException ioException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occured while uploading the file");
        }
        //adding access control(CannedAccessControlList.PublicRead) no authentification needed, to view videos publicly
        awsS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);
        //retrieve the url from video, that had just been uploaded to S3
        return awsS3Client.getResourceUrl(BUCKET_NAME, key);
    }
}
