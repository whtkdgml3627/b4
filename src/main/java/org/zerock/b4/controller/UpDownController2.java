// package org.zerock.b4.controller;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.FileSystemResource;
// import org.springframework.core.io.Resource;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.ResponseEntity;
// import org.springframework.util.FileCopyUtils;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
// import org.zerock.b4.dto.UploadResultDTO;

// import lombok.extern.log4j.Log4j2;
// import net.coobird.thumbnailator.Thumbnailator;

// @RestController
// @Log4j2
// public class UpDownController2 {

//   @Value("${org.zerock.upload.path}")// import 시에 springframework으로 시작하는 Value
//   private String uploadPath;
  
//   //upload
//   @PostMapping("/upload2")
//   public UploadResultDTO upload(MultipartFile file){

//     UploadResultDTO result = null;

//     String fileName = file.getOriginalFilename();

//     log.info("fileName: " + fileName);

//     long size = file.getSize();

//     String uuidStr = UUID.randomUUID().toString();

//     String saveFileName = uuidStr + "_" + fileName;

//     File saveFile = new File(uploadPath, saveFileName);

//     try {
//       //파일 확장자 체크
//       FileCopyUtils.copy(file.getBytes(), saveFile);

//       result = UploadResultDTO.builder()
//         .uuid(uuidStr)
//         .fileName(fileName)
//         .build();

//       //이미지 파일 여부 확인
//       String mimeType = Files.probeContentType(saveFile.toPath());

//       log.info("mimeType: " + mimeType);

//       if(mimeType.startsWith("image")) {
//         //업로드 성공 섬네일
//         File thumbFile = new File(uploadPath, "s_" + saveFileName);
//         Thumbnailator.createThumbnail(saveFile, thumbFile, 100, 100);

//         result.setImg(true);
//       }//end if

//     } catch (IOException e) {
//       // TODO Auto-generated catch block
//       e.printStackTrace();
//     }

//     log.info("size: " + size);

//     return result;
//   }

//   //download
//   @GetMapping("/view/{fileName}")
//   public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){

//     Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
//     String resourceName = resource.getFilename();
//     HttpHeaders headers = new HttpHeaders();

//     try{
//         headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
//     } catch(Exception e){
//         return ResponseEntity.internalServerError().build();
//     }
//     return ResponseEntity.ok().headers(headers).body(resource);
//   }

// }
