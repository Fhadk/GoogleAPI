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

public class GoogleDriveAPI {

    private static final String APPLICATION_NAME = "GoogleDriveAPI-2024";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static Drive service;

    /**
     * building Authorize API Client
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void init() throws IOException, GeneralSecurityException{
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleDriveAuth.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * @param fileId: String
     * @throws IOException
     */
    public static void readFilenameFromGoogleDriveById(String fileId) throws IOException {
        File result = service.files().get(fileId).execute();
        System.out.println(result.getName());
    }

    /**
     *
     * @throws IOException
     */
    public static void readAllDriveFromGoogleDrive() throws IOException {
        DriveList result = service.drives().list()
                .execute();

        List<com.google.api.services.drive.model.Drive> drives = result.getDrives();
        if (drives == null || drives.isEmpty()) {
            System.out.println("No files found.");
        }
    }

    /**
     *
     * @param type: String file | folder
     * @throws IOException
     */
    public static void readAllSharedFilesFromGoogleDrive(String type) throws IOException{
        FileList result = service.files().list()
                .setQ("sharedWithMe and mimeType != 'application/vnd.google-apps."+type+"' and trashed = false")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name, parents)")
                .setPageToken(null)
                .execute();

        for (File file : result.getFiles()) {
            System.out.printf("Found file: %s (%s)\n",
                    file.getName(), file.getId());
        }
    }



    /**
     *
     * @param fileID: String
     * @throws IOException
     */
    public static FileList readAllFileByIdFromGoogleDrive(String fileID) throws IOException{
         return service.files().list()
                .setQ(" '"+fileID+"' in parents ")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name, parents)")
                .setPageToken(null)
                .execute();


//        for (File file : result.getFiles()) {
//            System.out.printf("Found file: %s (%s)\n",
//                    file.getName(), file.getId());
//        }
    }

    /**
     * @throws IOException
     */
    public static void readAllFilenameFromGoogleDrive() throws IOException{
        FileList result = service.files().list()
                .setQ("'root' in parents and mimeType != 'application/vnd.google-apps.folder' and trashed = false")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name, parents)")
                .setPageToken(null)
                .execute();

        for (File file : result.getFiles()) {
            System.out.printf("Found file: %s (%s)\n",
                    file.getName(), file.getId());
        }
    }
}
