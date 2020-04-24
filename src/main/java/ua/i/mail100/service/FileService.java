package ua.i.mail100.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.i.mail100.config.FileConfig;
import ua.i.mail100.util.RandomUtil;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;

@Service
public class FileService {

    @Autowired
    FileConfig fileConfig;

    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String LINE_SEP = System.getProperty("line.separator");

    // work with text
    public static void writeTextToFile(String text, String path, String fileName, boolean append) {
        checkTargetDir(path);
        try (FileWriter fileWriter = new FileWriter(path + FILE_SEP + fileName, append)) {
            if (append) {
                BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                bufferWriter.write(text);
                bufferWriter.close();
            } else {
                fileWriter.write(text);
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkTargetDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static String readTextFromFile(String path, String fileName) {
        String out = "";
        try (FileReader fileReader = new FileReader(path + FILE_SEP + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                out += line + LINE_SEP;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    // work with bytes
    public static void writeBytesToFile(byte[] bytes, String path, String fileName) {
        checkTargetDir(path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + FILE_SEP + fileName)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeObjectToFile(Object object, String path, String fileName) {
        checkTargetDir(path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + FILE_SEP + fileName);
             ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream)) {
            objectInputStream.writeObject(object);
            objectInputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getBytesFromFile(String path, String fileName) {
        File file = new File(path + FILE_SEP + fileName);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void copyFile(String sourcePath, String sourceFileName, String targetPath, String targetFileName) {
        byte[] bytes = getBytesFromFile(sourcePath, sourceFileName);
        writeBytesToFile(bytes, targetPath, targetFileName);
    }

    public static void moveFile(String sourcePath, String sourceFileName, String targetPath) {
        byte[] bytes = getBytesFromFile(sourcePath, sourceFileName);
        writeBytesToFile(bytes, targetPath, sourceFileName);
        deleteFile(sourcePath, sourceFileName);
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + FILE_SEP + fileName);
        file.delete();
    }

    public static <T> void writeCollectionToFile(Collection<T> collection, String path, String fileName, boolean append) {
        if (!append) {
            writeTextToFile("", path, fileName, false);
        }
        for (T each : collection) {
            FileService.writeTextToFile(each.toString() + LINE_SEP, path, fileName, true);
        }
    }

    public static Object readObjectFromFile(String path, String fileName) {
        Object object = new Object();
        try (FileInputStream fileInputStream = new FileInputStream(path + FILE_SEP + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            object = objectInputStream.readObject();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return object;
    }

    public static byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];

        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;
    }

    public static Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b;
        return bytes;
    }

    public File getFileUploadedName(String path, MultipartFile file) {
        checkTargetDir(path);
        String fileName = null;
        try {
            byte[] bytes = file.getBytes();
            fileName = file.getOriginalFilename();
            fileName = getUniqueFileName(path, fileName);

            File uploadedFile = new File(path + File.separator + fileName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
            stream.write(bytes);
            stream.flush();
            stream.close();

            return uploadedFile;

        } catch (Exception e) {
            return null;
        }
    }

    public String getUniqueFileName(String path, String name) {
        while (isExistSameNameFile(path, name)) {
            String[] baseAndExtention = name.split("\\.(?=[^\\.]+$)");
            String base = baseAndExtention[0];
            String extention = baseAndExtention[1];
            Integer randomDigit = RandomUtil.randomFixedLength(1);
            name = base + (extention != null ? (randomDigit + "." + extention) : randomDigit);
        }
        return name;
    }

    public boolean isExistSameNameFile(String path, String name) {
        File directory = new File(path);
        File foundFile = FileUtils
                .listFiles(directory, null, true)
                .stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (foundFile != null) return true;
        return false;
    }


}