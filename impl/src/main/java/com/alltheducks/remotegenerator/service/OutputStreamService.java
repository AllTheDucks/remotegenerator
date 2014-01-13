package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.exception.ObtainOutputStreamException;

import java.io.OutputStream;

public interface OutputStreamService {

    public OutputStream getOutputStreamForClass(String className) throws ObtainOutputStreamException;

}
