package com.ebase.core.fastdfs.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebase.core.exception.BusinessException;
import com.ebase.core.fastdfs.dto.FileDTO;
import com.ebase.core.fastdfs.service.FileService;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * 上传文件接口
 * 
 * @author Kim
 *
 */
@Service
public class FileServiceImpl implements FileService {
	private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	static final String CONSTANT_FILE_NAME = "FILE_NAME";

	@Autowired
	private FastFileStorageClient storageClient;

	public String uploadFile(@NotNull String groupName, @NotNull InputStream inputStream,String fileName,
			String fileExtName) throws BusinessException {
		try {
			byte[] byt = new byte[inputStream.available()];
			StorePath store = this.storageClient.uploadFile(groupName, inputStream, byt.length, fileExtName);
			Set<MateData> metaDataSet = new HashSet<MateData>();
			metaDataSet.add(new MateData(CONSTANT_FILE_NAME, fileName));
			this.storageClient.overwriteMetadata(groupName, store.getPath(), metaDataSet);
			return store.getFullPath();
		} catch (Exception ex) {
			logger.error("上传文件出错!", ex);
			throw new BusinessException("f0101", null);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	public String uploadFile(@NotNull String groupName, @NotNull byte[] contents,String fileName,
			String fileExtName) throws BusinessException {
		ByteArrayInputStream in = null;
		try {
			in = new ByteArrayInputStream(contents);
			StorePath store = this.storageClient.uploadFile(groupName, in, contents.length, fileExtName);
			Set<MateData> metaDataSet = new HashSet<MateData>();
			metaDataSet.add(new MateData(CONSTANT_FILE_NAME, fileName));
			this.storageClient.overwriteMetadata(groupName, store.getPath(), metaDataSet);
			return store.getFullPath();
		} catch (Exception ex) {
			logger.error("上传文件出错!", ex);
			throw new BusinessException("f0101", null);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public FileDTO downloadFile(String fileId) throws BusinessException {
		try {
			StorePath storePath = StorePath.praseFromUrl(fileId);
			byte[] contents = this.storageClient.downloadFile(storePath.getGroup(), storePath.getPath(),
					new DownloadByteArray());

			FileDTO file = new FileDTO();
			file.setContents(contents);
			file.setFileId(fileId);

			Set<MateData> metaDataSet = this.storageClient.getMetadata(storePath.getGroup(), storePath.getPath());
			for (Iterator<MateData> itr = metaDataSet == null ? null : metaDataSet.iterator(); itr != null
					&& itr.hasNext();) {
				MateData element = itr.next();
				if (StringUtils.equalsIgnoreCase(CONSTANT_FILE_NAME, element.getName())) {
					file.setFileName(element.getValue());
					break;
				}
			}

			return file;
		} catch (Exception ex) {
			logger.error("下载文件出错!", ex);
			throw new BusinessException("f0102", null);
		}
	}

	public boolean exist(String fileId) throws BusinessException {
		try {
			StorePath storePath = StorePath.praseFromUrl(fileId);
			this.storageClient.queryFileInfo(storePath.getGroup(), storePath.getPath());
			return true;
		} catch (Exception ex) {
			return false; // 文件找不到会报错，直接返回false
		}
	}

	public boolean delete(String fileId) throws BusinessException {
		StorePath storePath = StorePath.praseFromUrl(fileId);
		try {
			this.storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
			return true;
		} catch (Exception ex) {
			return false; // 文件找不到会报错，直接返回false
		}
	}

}
