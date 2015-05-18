package com.td.webapp.controller.file;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by konstantinchipunov on 25.09.14.
 */
public class SizeSupportInputStreamResource extends InputStreamResource {

    private final long length;

    public SizeSupportInputStreamResource(InputStream inputStream, long length) {
        super(inputStream);
        this.length = length;
    }

    public SizeSupportInputStreamResource(InputStream inputStream, String description, long length) {
        super(inputStream, description);
        this.length = length;
    }

    @Override
    public long contentLength() throws IOException {
        return length;
    }
}
