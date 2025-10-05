package com.example.demo.utils;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageHandler {

    private static final String IMAGE_DIR = "images";

    // Save image from ImageView to 'images' folder and return the stored path
    public static String saveImage(Image image) throws IOException {
        if (image == null) return null;

        // Ensure images directory exists
        File imagesDir = new File(IMAGE_DIR);
        if (!imagesDir.exists()) imagesDir.mkdir();

        // Get source file from Image URL
        String sourcePath = image.getUrl().replace("file:/", "").replace("%20", " ");
        File sourceFile = new File(sourcePath);

        if (!sourceFile.exists()) return null;

        String newName = System.currentTimeMillis() + "_" + sourceFile.getName();
        File destination = new File(imagesDir, newName);

        Files.copy(sourceFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return destination.getPath();
    }

    // Load image from file path
    public static Image loadImage(String path) {
        if (path == null || path.isEmpty()) return null;

        File file = new File(path);
        if (!file.exists()) return null;

        return new Image(file.toURI().toString());
    }
}
