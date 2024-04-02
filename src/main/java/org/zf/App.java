package org.zf;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.DriveList;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class App {
    /**
     * @param args
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        GoogleDriveAPI.init();
//        GoogleDriveAPI.readAllFilenameFromGoogleDrive();
//        GoogleDriveAPI.readFilenameFromGoogleDriveById("1ZQOA5_wZt70MhNPS5p3XkiqqM3JqYSiw");
//        GoogleDriveAPI.readAllDriveFromGoogleDrive();
//        GoogleDriveAPI.readAllSharedFilesFromGoogleDrive("folder");
        LocalOperation.searchFileNameAndCopyToFolder("C:\\Users\\user\\Downloads\\WeddingPictureFhadxHyba\\",
                "C:\\Users\\user\\Downloads\\WeddingPictureFhadxHyba\\GroomSide",
                GoogleDriveAPI.readAllFileByIdFromGoogleDrive("1ZQOA5_wZt70MhNPS5p3XkiqqM3JqYSiw"));

//        GoogleDriveAPI.readAllFileByIdFromGoogleDrive("1ZQOA5_wZt70MhNPS5p3XkiqqM3JqYSiw");
    }
}
