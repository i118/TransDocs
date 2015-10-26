package com.td.filetransfer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by konstantinchipunov on 06.10.14.
 */
public interface IFileWrapper {

    public String getName();

    public String getPath();

    public InputStream getContent();

    public String getId();

    public void edit() throws IOException;

    public void save() throws IOException;

    public void view() throws IOException;

    public void delete();

    public void cancelCheckout() throws IOException;
}
