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
    @PostMapping("/upload/{username}")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String username) {
        String message = "";
        try {
            fileUploadService.save(file, username);

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
    @GetMapping("/files/{username}")
    public ResponseEntity<List<FileInfo>> getFiles(@PathVariable String username) {
        List<FileInfo> fileInfos = fileUploadService.loadAll(username);

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    /**
     * Gets file.
     *
     * @param filename the filename
     * @return the file
     */
    @GetMapping("/file/{filename:.+}/{username}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename, @PathVariable String username) {
        Resource file = fileUploadService.load(filename, username);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * Generate csv response entity.
     *
     * @param filename the filename
     * @return the response entity
     */
    @GetMapping("/files/generateCSV/{filename:.+}/{username}")
    public ResponseEntity<?> generateCSV(@PathVariable String filename, @PathVariable String username) {
        fileUploadService.generateCSV(username, filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Gets csv.
     *
     * @param filename the filename
     * @return the csv
     */
    @GetMapping("/files/getCSV/{filename:.+}/{username}")
    public ResponseEntity<?> getCSV(@PathVariable String filename, @PathVariable String username) {
        List<StockOption> stockOptions = fileUploadService.getCSV(username, filename);
        return ResponseEntity.ok(stockOptions);
    }
}
