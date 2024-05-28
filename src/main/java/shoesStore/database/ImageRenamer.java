package shoesStore.database;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageRenamer {

    public static void main(String[] args) {
        // Specify the directory containing the images
        String directoryPath = "C:\\Study\\WorkSpace eclipse\\CSE 456\\ShoesStore\\src\\main\\webapp\\view\\images"; // Change this to your image directory path

        // Rename all files in the directory
        renameFilesInDirectory(directoryPath);
    }

    public static void renameFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        // Check if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Decode the file name
                    try {
                        String decodedName = URLDecoder.decode(file.getName(), "UTF-8");
                        // Create the new file object
                        File newFile = new File(directoryPath, decodedName);

                        // Rename the file
                        if (!file.getName().equals(decodedName)) {
                            Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Renamed: " + file.getName() + " -> " + decodedName);
                        } else {
                            System.out.println("File already correctly named: " + file.getName());
                        }
                    } catch (UnsupportedEncodingException e) {
                        System.err.println("Error decoding file name: " + file.getName());
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.err.println("Error renaming file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }
}
