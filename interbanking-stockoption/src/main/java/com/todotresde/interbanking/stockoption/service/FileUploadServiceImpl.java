package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.commons.CSVUtils;
import com.todotresde.interbanking.stockoption.model.FileInfo;
import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * The type File upload service.
 */
@Service
public class FileUploadServiceImpl implements com.todotresde.interbanking.stockoption.service.FileUploadService {

    @Value( "${upload.folder}" )
    private String uploadFolder;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private Environment env;

    @Autowired
    private StockOptionSimulationService stockOptionSimulationService;

    @Override
    public void init() {
        try {
            Files.createDirectory(getRoot());
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void initForUser(String username) {
        try {
            if(!Files.exists(getRootForUser(username)))
                Files.createDirectory(getRootForUser(username));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, String username) {
        try {
            initForUser(username);
            Files.deleteIfExists(this.getRootForUser(username).resolve(file.getOriginalFilename()));

            Files.copy(file.getInputStream(), this.getRootForUser(username).resolve(file.getOriginalFilename()));
            FileInfo fileInfo = fileInfoRepository.findByUsernameAndName(username, file.getOriginalFilename());

            if(fileInfo == null)
                fileInfoRepository.save(new FileInfo(username, file.getOriginalFilename(), file.getOriginalFilename()));

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename, String username) {
        try {
            Path file = getRootForUser(username).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(getRoot().toFile());
    }

    @Override
    public List<FileInfo> loadAll(String username) {
        return fileInfoRepository.findByUsername(username);
    }

    @Override
    public void generateCSV(String username, String filename){
        Path file = getRootForUser(username).resolve(filename);
        FileSystemUtils.deleteRecursively(file.toFile());

        HashMap<String, Float> stockOptions = new HashMap<>();
        stockOptions.put("YPF", 200f);
        stockOptions.put("TS", 10f);
        stockOptions.put("GGAL", 280f);
        stockOptions.put("ML", 320f);

        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file.toString()), StandardCharsets.UTF_8);
            CSVUtils.writeLine(writer, Arrays.asList("Acción", "Fecha", "Precio"));
            Date initialDate = new Date();
            for(int i = 0; i < 100; i++) {
                for (Map.Entry<String, Float> entry : stockOptions.entrySet()) {
                    String stockOption = entry.getKey();

                    Float max = entry.getValue() * 0.1f;
                    Float stockOptionValue = entry.getValue() + ((float) Math.random() * max) ;

                    DateFormat formatter = new SimpleDateFormat("d/M/y");
                    CSVUtils.writeLine(writer, Arrays.asList(stockOption, formatter.format(initialDate), "$" + String.format("%.02f", stockOptionValue)));
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(initialDate);
                calendar.add(Calendar.DATE, 1);

                initialDate = calendar.getTime();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StockOption> getCSV(String username, String filename){
        return stockOptionSimulationService.readFile(username, filename);
    }

    @Override
    public Path getRoot(){
        return Paths.get(env.getProperty("upload.folder"));
    }

    @Override
    public Path getRootForUser(String username){
        return Paths.get(env.getProperty("upload.folder") + "/" + username);
    }
}
