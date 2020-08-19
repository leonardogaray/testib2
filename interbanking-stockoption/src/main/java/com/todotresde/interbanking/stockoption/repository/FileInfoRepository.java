package com.todotresde.interbanking.stockoption.repository;

import com.todotresde.interbanking.stockoption.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    List<FileInfo> findByUsername(String username);

    FileInfo findByUsernameAndName(String username, String name);
}
