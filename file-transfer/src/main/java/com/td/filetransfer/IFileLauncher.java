package com.td.filetransfer;

import java.io.File;

/**
 * Created by konstantinchipunov on 05.10.14.
 */
public interface IFileLauncher {

    public void launch(File file);

    public boolean isApplicable();
}
