package cat.mrtxema.crispetes.store;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws Exception {
        System.out.printf("Running fom directory '%s'%n", new File(".").getAbsolutePath());
        writeConfigFile("ormlite_config.txt", DtoRegistry.getDtoClassesAsArray());
    }

    public static void writeConfigFile(String fileName, Class<?>[] classes) throws SQLException, IOException {
        File currentDir = new File(new File(".").getAbsolutePath());
        File rawDir = findRawDir(currentDir);
        if (rawDir == null) {
            System.err.println("Could not find " + RAW_DIR_NAME + " directory which is typically in the "
                    + RESOURCE_DIR_NAME + " directory");
        } else {
            File configFile = new File(rawDir, fileName);
            writeConfigFile(configFile, classes);
        }
    }
}
