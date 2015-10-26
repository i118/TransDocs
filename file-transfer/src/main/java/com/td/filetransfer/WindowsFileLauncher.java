package com.td.filetransfer;

import java.io.File;
import java.io.IOException;

/**
 * Created by konstantinchipunov on 05.10.14.
 */
public class WindowsFileLauncher implements IFileLauncher {

    @Override
    public void launch(File file) {
        try {
            System.out.println("opened file: "+file.getCanonicalPath());
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", "\""+file.getCanonicalPath()+"\""});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isApplicable() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("win")) {
            return true;
        }
        return false;
    }
}
