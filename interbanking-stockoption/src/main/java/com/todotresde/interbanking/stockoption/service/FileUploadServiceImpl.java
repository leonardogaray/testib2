package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.commons.CSVUtils;
import com.todotresde.interbanking.stockoption.model.StockOption;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Path root = Paths.get("uploads");

    @Autowired
    private com.todotresde.interbanking.stockoption.service.StockOptionSimulationService stockOptionSimulationService;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
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
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public void generateCSV(String filename){
        Path file = root.resolve(filename);
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
    public List<StockOption> getCSV(String filename){
        return stockOptionSimulationService.readFile(filename);
    }

}
