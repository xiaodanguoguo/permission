package com.ebase.core.file;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FileVO {


    private String fileName;

    private String fileUrl;

    private String uploadingName; //上传人

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date  uploadingTime; //上传时间


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public String getUploadingName() {
        return uploadingName;
    }

    public void setUploadingName(String uploadingName) {
        this.uploadingName = uploadingName;
    }

    public Date getUploadingTime() {
        return uploadingTime;
    }

    public void setUploadingTime(Date uploadingTime) {
        this.uploadingTime = uploadingTime;
    }
}
