package com.td.filetransfer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantinchipunov on 05.10.14.
 */
public class FileLauncherFactory implements  IFileLauncherFactory{

    private Set<IFileLauncher> launchers;

    public FileLauncherFactory(){
        launchers = new HashSet<IFileLauncher>();
        launchers.add(new MacFileLauncher());
        launchers.add(new WindowsFileLauncher());
        launchers.add(new LinuxFileLauncher());
    }

    public IFileLauncher getLauncher(){
        for(IFileLauncher launcher : launchers){
            if(launcher.isApplicable()){
                return launcher;
            }
        }
      return null;
    }
}
