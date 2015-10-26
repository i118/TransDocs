package com.td.filetransfer;

import java.io.File;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class DirUtil {

    public static File getBaseDir(){
        String userHome = System.getProperty("user.home");
        String transDocsPath = userHome + File.separator + ".td";
        File tdDir = new File(transDocsPath);
        if (!tdDir.exists()) {
            tdDir.mkdirs();
        }
        return tdDir;
    }
}
