package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.exception.ObtainOutputStreamException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileOutputStreamService implements OutputStreamService {

    private String basePath;
    private File baseDir;
    private FileNameService fileNameService;

    @Override
    public OutputStream getOutputStreamForClass(String className) throws ObtainOutputStreamException {
        try {
            return new FileOutputStream(new File(this.getBaseDir(), fileNameService.getFileNameForClass(className)));
        } catch (FileNotFoundException e) {
            throw new ObtainOutputStreamException(e);
        }
    }

    File getBaseDir() {
        if(baseDir == null) {
            baseDir = new File(basePath);
            if(!baseDir.exists()) {
                baseDir.mkdirs();
            }
        }
        return baseDir;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        this.baseDir = null;
    }

    public FileNameService getFileNameService() {
        return fileNameService;
    }

    public void setFileNameService(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }
}
