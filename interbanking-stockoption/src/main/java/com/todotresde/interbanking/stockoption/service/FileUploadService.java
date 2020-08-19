package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.FileInfo;
import com.todotresde.interbanking.stockoption.model.StockOption;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * The interface File upload service.
 */
public interface FileUploadService {
    /**
     * Init.
     */
    public void init();

    public void initForUser(String username);

    /**
     * Save.
     *
     * @param file the file
     */
    public void save(MultipartFile file, String username);

    /**
     * Load resource.
     *
     * @param filename the filename
     * @return the resource
     */
    public Resource load(String filename, String username);

    /**
     * Delete all.
     */
    public void deleteAll();

    /**
     * Load all stream.
     *
     * @return the stream
     */
    public List<FileInfo> loadAll(String username);

    /**
     * Generate csv.
     *
     * @param filename the filename
     */
    public void generateCSV(String username, String filename);

    /**
     * Gets csv.
     *
     * @param filename the filename
     * @return the csv
     */
    public List<StockOption> getCSV(String username, String filename);

    public Path getRoot();

    public Path getRootForUser(String username);
}
