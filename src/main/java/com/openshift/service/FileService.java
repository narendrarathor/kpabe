package com.openshift.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.openshift.exception.FileStorageException;
import com.openshift.exception.MyFileNotFoundException;
import com.openshift.model.FileModel;
import com.openshift.repository.FileRepository;
import com.openshift.security.KP_ABE;
import com.openshift.security.Modified_KP_ABE;
 
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
    
    public void storeFile(MultipartFile file) {
    	Date dNow = new Date();
    	 long kpabetime;
         long modifiedkpabetime;
         KP_ABE kpabe=new KP_ABE();
         kpabetime=System.nanoTime();
			modifiedkpabetime=System.nanoTime();;
         Modified_KP_ABE mkpabe=new Modified_KP_ABE();
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
        	 //get curretn date 
			
			SimpleDateFormat ft = new SimpleDateFormat(
					" dd-MM-yyyy, hh:mm");
			String date = ft.format(dNow).toString();
			kpabe.encrption(file.getBytes());
			kpabetime=System.nanoTime()-kpabetime;
			mkpabe.encrption(file.getBytes());
			modifiedkpabetime=System.nanoTime()-modifiedkpabetime;
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }      
            FileModel dbFile = new FileModel(fileName, file.getContentType(), file.getSize(), kpabetime, modifiedkpabetime, date, file.getBytes());
            repo.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public FileModel getFile(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }
}
