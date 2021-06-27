package com.openshift.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openshift.model.FileModel;
import com.openshift.repository.FileRepository;
 
@Service
@Transactional
public class FileService {
 
    @Autowired
    private FileRepository repo;
     
    public List<FileModel> listAll() {
        return repo.findAll();
    }
     
    public void save(FileModel file) {
        repo.save(file);
    }
     
    public FileModel get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
