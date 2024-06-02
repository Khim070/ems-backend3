package com.example.emsbackend3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class EmsBackend3Application {

//    @Autowired
//    private StorageService service;
//
//    @PostMapping("/fileSystem")
//    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file) throws IOException {
//        String uploadImage = service.uploadImageToFileSystem(file);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//
//    @GetMapping("/fileSystem/{fileName}")
//    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
//        byte[] imageData=service.downloadImageFromFileSystem(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//
//    }


    public static void main(String[] args) {
        SpringApplication.run(EmsBackend3Application.class, args);
    }

}
