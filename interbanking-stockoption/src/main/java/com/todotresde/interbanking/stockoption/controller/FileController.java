package com.todotresde.interbanking.stockoption.controller;

import com.todotresde.interbanking.stockoption.message.ResponseMessage;
import com.todotresde.interbanking.stockoption.model.FileInfo;
import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type File controller.
 */
@RequestMapping("/stockoption/file")
@RestController
public class FileController {

    /**
     * The File upload service.
     */
    @Autowired
    FileUploadService fileUploadService;

    /**
     * Upload file response entity.
     *
     * @param file the file
     * @return the response entity
     */
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileUploadService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Gets list files.
     *
     * @return the list files
     */
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfoInfos = fileUploadService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfoInfos);
    }

    /**
     * Gets file.
     *
     * @param filename the filename
     * @return the file
     */
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileUploadService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * Generate csv response entity.
     *
     * @param filename the filename
     * @return the response entity
     */
    @GetMapping("/files/generateCSV/{filename:.+}")
    public ResponseEntity<?> generateCSV(@PathVariable String filename) {
        fileUploadService.generateCSV(filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Gets csv.
     *
     * @param filename the filename
     * @return the csv
     */
    @GetMapping("/files/getCSV/{filename:.+}")
    public ResponseEntity<?> getCSV(@PathVariable String filename) {
        List<StockOption> stockOptions = fileUploadService.getCSV(filename);
        return ResponseEntity.ok(stockOptions);
    }
}
