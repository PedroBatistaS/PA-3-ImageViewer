package persistance;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Image;
import persistance.ImageReader;


public class FileImageReader implements ImageReader{

    public File[] getFiles() {
        return files;
    }
    
    private File[] files;
    private final static String[] extensions = {".jpg", ".png" };

    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.files = folder.listFiles(imageType());
    }
    
    public FilenameFilter imageType() {
        return (File dir, String name) -> {
            boolean result = false;
            for (String extension : extensions) {
                result = name.toLowerCase().endsWith(extension);
            }
            return result;         
        };
    }

    @Override
    public Image read() {
        return imageAt(0);
    }

    

    private Image imageAt(final int index) {
        return new Image() {
            @Override
            public <T> T bitmap() {
                try {
                    return (T) ImageIO.read(files[index]);
                } catch (IOException ex) {
                    return null;
                }
            }

            @Override
            public Image next() {
                return imageAt((index == files.length-1) ? 0:index+1);
            }

            @Override
            public Image previous() {
                return imageAt((index == 0) ? files.length-1:index-1);
            }

            @Override
            public int getWidth() {
                try {
                    return ImageIO.read(files[index]).getWidth();
                } catch (IOException ex) {
                    Logger.getLogger(FileImageReader.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }

            @Override
            public int getHeight() {
                try {
                    return ImageIO.read(files[index]).getHeight();
                } catch (IOException ex) {
                    Logger.getLogger(FileImageReader.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 1;
            }
        };
    }
    
}
