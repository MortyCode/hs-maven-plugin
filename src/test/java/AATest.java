import org.eclipse.jgit.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author ：河神
 * @date ：Created in 2021/4/21 7:16 下午
 */
public class AATest {

    public static void main(String[] args) throws IOException {

        File file = new File("bb");
        Path path = Paths.get("../online.base.apijar.service/bb");
        Files.copy(file.toPath(),path, StandardCopyOption.REPLACE_EXISTING);


    }

}
