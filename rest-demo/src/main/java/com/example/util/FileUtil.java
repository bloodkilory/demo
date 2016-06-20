package com.example.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.example.exception.FileHandleException;

/**
 * Created by bloodkilory on 15/4/16.
 */
public class FileUtil {

    public enum Method {
        LINE,
        BYTE,
        EASY
    }

    /**
     * copy file with old way
	 *
	 * @param fromPath
	 * @param toPath
	 * @throws java.io.IOException
	 * @should test
	 */
	public static void copyFile(String fromPath, String toPath) throws IOException {
        try(InputStream input = new FileInputStream(fromPath);
            OutputStream output = new FileOutputStream(toPath)) {
            byte[] buffer = new byte[1024];
            int len;
            while((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
        } catch(Exception e) {
            throw new FileHandleException(10001, "Copy File Error!", e);
        }
    }

	/**
     * copy file with fusion mode
     *
     * @param fromPath
	 * @param toPath
     * @param charset {@link StandardCharsets#UTF_8 UTF_8}
     * @throws IOException
	 */
    public static void copyFileWithNioBuffer(String fromPath, String toPath, Charset charset) throws IOException {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(fromPath), charset);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(toPath), charset,
                    StandardOpenOption.WRITE)) {
            String line;
            while((line = reader.readLine()) != null) {
                writer.write(line);
                writer.flush();
            }
        } catch(Exception e) {
            throw new FileHandleException(10001, "Copy File Error!", e);
        }
    }

	/**
     * copy file with NIO
     *
	 * @param fromPath
	 * @param toPath
     * @param charset {@link StandardCharsets#UTF_8 UTF_8}
     * {@link StandardOpenOption#APPEND, #WRITE, #CREATE_NEW...}
     * @throws IOException
	 */
    public static void copyFileWithNio(String fromPath, String toPath, Charset charset, Method method) {
        switch(method) {
            case LINE:
                try {
					List<String> lines = Files.readAllLines(Paths.get(fromPath), charset);
                    Files.write(Paths.get(toPath), lines, charset, StandardOpenOption.APPEND);
                } catch(Exception e) {
                    throw new FileHandleException(10001, "Copy File Error!, " +
                            "from:[" + fromPath + "], to:[" + toPath + "]\"", e);
                }
            case BYTE:
                try {
					byte[] bytes = Files.readAllBytes(Paths.get(fromPath));
					Files.write(Paths.get(toPath), bytes, StandardOpenOption.WRITE);
				} catch(Exception e) {
                    throw new FileHandleException(10001, "Copy File Error!, " +
                            "from:[" + fromPath + "], to:[" + toPath + "]\"", e);
                }
            case EASY:
                try { // 存在即覆盖
                    Files.copy(Paths.get(fromPath), Paths.get(toPath), StandardCopyOption.REPLACE_EXISTING);
                } catch(Exception e) {
                    throw new FileHandleException(10001, "Copy File Error!, " +
                            "from:[" + fromPath + "], to:[" + toPath + "]\"", e);
                }
            default:
				throw new IllegalArgumentException("check you parameters");
		}
	}
}
