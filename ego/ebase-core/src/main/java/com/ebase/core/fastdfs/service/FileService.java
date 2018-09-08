package com.ebase.core.fastdfs.service;

import java.io.InputStream;

import javax.validation.constraints.NotNull;

import com.ebase.core.exception.BusinessException;
import com.ebase.core.fastdfs.dto.FileDTO;

/**
 * 文件上传下载接口
 * @author Kim
 *
 */
public interface FileService {

	public String uploadFile(@NotNull String groupName, @NotNull InputStream inputStream,String fileName, String fileExtName) throws BusinessException;

	public String uploadFile(@NotNull String groupName, @NotNull byte[] contents,String fileName, String fileExtName)
			throws BusinessException;

	public FileDTO downloadFile(String fileId) throws BusinessException ;

	public boolean exist(String fileId) throws BusinessException;

	public boolean delete(String fileId) throws BusinessException;

}
