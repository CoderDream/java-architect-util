package com.coderdream.freeapps.util.bbc;

import java.io.File;
import org.jetbrains.annotations.NotNull;

/**
 * @author CoderDream
 */
public class CommonUtil {

    @NotNull
    public static String getFullPathFileName(String folderName, String fileName, String extensionName) {
        String year = "20" + folderName.substring(0, 2);
        return BbcConstants.ROOT_FOLDER_NAME + year + File.separator + folderName + File.separator + fileName
            + extensionName;
    }

}
