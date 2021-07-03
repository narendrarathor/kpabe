package com.openshift.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class FileModel {
	private Long id;
    private String name;
    private String fileType;
    private long size;
    private long kpabetime;
    private long modifiedkpabetime;
    private String uploadtime;
    
    @Lob
    private byte[] data;
    
    
    public FileModel() {};
    public FileModel(Long id, String name, String fileType, byte[] data) {
		super();
		this.id = id;
		this.name = name;
		this.fileType = fileType;
		this.data = data;
	}
	public FileModel(String name, String fileType, long size, long kpabetime, long modifiedkpabetime,
			String uploadtime, byte[] data) {
		super();
		this.name = name;
		this.fileType = fileType;
		this.size = size;
		this.kpabetime = kpabetime;
		this.modifiedkpabetime = modifiedkpabetime;
		this.uploadtime = uploadtime;
		this.data = data;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getKpabetime() {
		return kpabetime;
	}
	public void setKpabetime(long kpabetime) {
		this.kpabetime = kpabetime;
	}
	public long getModifiedkpabetime() {
		return modifiedkpabetime;
	}
	public void setModifiedkpabetime(long modifiedkpabetime) {
		this.modifiedkpabetime = modifiedkpabetime;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	
	
}
