package hgu.isel.reader;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


/**
 * This ByteReader class is related to the File I/O tasks.
 * By using the input path, which can be a path of directory or class file, it can read the entire bytecodes.
 * Also, it can generate analyzed / tokenized bytecode files in txt format.
 */
public class ByteReader {
    private final String inputPath;

    /**
     * Constructor of the ByteReader.
     * @param inputPath It is related to the 1) directory path or 2) single file path
     */
    public ByteReader(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * This method reads the entire single bytecode file.
     * @return It returns the array of byte which represents the entire bytecodes.
     */
    public byte[] readClassFile() {
        try {

            return Files.readAllBytes(Paths.get(inputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method reads the entire class file paths from the input path. If there are 3 class files in the input directory, then it will return 3 paths.
     * @return It returns List to represent all paths from the input directory.
     */
    public List<String> readClassFilePaths() {
        Path startPath = Paths.get(inputPath);
        List<String> filePaths = new ArrayList<>();

        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".class")) {
                        String fileName = file.toString(); // 파일 이름
                        filePaths.add(fileName); // 파일 경로를 리스트에 추가
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePaths;
    }

    /**
     * This method reads the entire single bytecode file.
     * @param path This method reads a single bytecode file which is in the path variable.
     * @return It returns an array of byte which is a bytecode.
     */

    public byte[] readClassFile(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
