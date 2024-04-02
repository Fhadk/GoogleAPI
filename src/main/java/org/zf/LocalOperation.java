package org.zf;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalOperation {
    static List<Path> sourceLocation;

    public static boolean searchFileNameAndCopyToFolder(String sourcePath, String destination,
                                                        FileList files) throws IOException {

        for(File file: files.getFiles()) {
            Path path = Paths.get(sourcePath);
            sourceLocation = findByFileName(path, file.getName());
            sourceLocation.forEach(x -> System.out.println(x));
        }

//        for(Path p: sourceLocation){
//            Path sourcepath = Paths.get(p.toString());
//            Path destinationepath = Paths.get(destination);
//            Files.walk(sourcepath)
//                    .forEach(source -> copy(source, destinationepath.resolve(sourcepath.relativize(source))));
//        }
        return true;
    }

    static void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static List<Path> findByFileName(Path path, String fileName)
            throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) ->
                        p.getFileName().toString().equalsIgnoreCase(fileName))
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }

    public static void copyingFile() {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("C:\\Users\\user\\Downloads\\WeddingPictureFhadxHyba\\Walima Complete\\F53A9980.JPG");
            out = new FileOutputStream("C:\\Users\\user\\Downloads\\WeddingPictureFhadxHyba\\F53A9980.JPG");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O Error");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }

}
