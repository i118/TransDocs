import java.io.File;
import java.io.IOException;

/**
 * Created by konstantinchipunov on 08.10.14.
 */
public class LinuxFileLauncher implements IFileLauncher {
    @Override
    public void launch(File file) {
        try {
            System.out.println("opened file: "+ file.getCanonicalPath());
            Runtime.getRuntime().exec(new String[]{"xdg-open", file.getCanonicalPath()});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isApplicable() {
        String osName = System.getProperty("os.name").toLowerCase();
        return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0 );
    }
}
