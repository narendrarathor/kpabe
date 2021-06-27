package com.openshift.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.openshift.model.FileModel;

public interface FileRepository extends JpaRepository<FileModel, Long> {
 
}
