package ryancarpio.youtubeclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ryancarpio.youtubeclone.model.Video;

public interface VideoRepository extends MongoRepository<Video, String>{
}
