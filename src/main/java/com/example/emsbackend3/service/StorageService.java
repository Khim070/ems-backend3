package com.example.emsbackend3.service;

import com.example.emsbackend3.entity.ImageData;
import com.example.emsbackend3.repository.FileDataRepository;
import com.example.emsbackend3.repository.StorageRepository;
import com.example.emsbackend3.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    private final String FOLDER_PATH="/Users/user/Desktop/University/Java/exercise/ems-backend3/src/main/java/com/example/emsbackend3/assets/";
    @Autowired
    private FileDataRepository fileDataRepository;

    // upload image
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    // get image
    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    //update image
    public String updateImage(long imageId, MultipartFile file) throws IOException {
        Optional<ImageData> existingData = repository.findById(imageId);

        ImageData updatedData;
        if (existingData != null) {
            updatedData = existingData.get().toBuilder()
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build();
        } else {
            return "Image not found with ID: " + imageId;
        }
        repository.save(updatedData);
        return "Image updated successfully : " + file.getOriginalFilename();
    }

    //delete image
    public String deleteImage(Long imageId) {
        Optional<ImageData> existingData = repository.findById(imageId);
        if (existingData.isPresent()) {
            repository.deleteById(imageId);
            return "Image deleted successfully with ID: " + imageId;
        } else {
            return "Image not found with ID: " + imageId;
        }
    }


//    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
//        String filePath=FOLDER_PATH+file.getOriginalFilename();
//
//        FileData fileData=fileDataRepository.save(FileData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .filePath(filePath).build());
//
//        file.transferTo(new File(filePath));
//
//        if (fileData != null) {
//            return "file uploaded successfully : " + filePath;
//        }
//        return null;
//    }
//
//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
//        String filePath=fileData.get().getFilePath();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }
}