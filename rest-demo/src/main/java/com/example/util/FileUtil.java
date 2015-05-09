package com.example.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by bloodkilory on 15/4/16.
 */
public class FileUtil {

	public static final String LINE = "LINE";
	public static final String BYTE = "BYTE";

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
		}
	}

	/**
	 * @param fromPath
	 * @param toPath
	 * @param charset  @see StandardCharsets.UTF_8
	 * @throws IOException
	 */
	public void copyFileWithNioBuffer(String fromPath, String toPath, Charset charset) throws IOException {
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(fromPath), charset);
		    BufferedWriter writer = Files.newBufferedWriter(Paths.get(toPath), charset,
				    StandardOpenOption.WRITE)) {
			String line;
			while((line = reader.readLine()) != null) {
				writer.write(line);
				writer.flush();
			}
		}
	}

	/**
	 *
	 * @param fromPath
	 * @param toPath
	 * @param charset @see StandardCharsets.UTF_8
	 * @throws IOException
	 */
	public void copyFileWithNio(String fromPath, String toPath, Charset charset, String method) {
		switch(method) {
			case LINE:
				try {
					List<String> lines = Files.readAllLines(Paths.get(fromPath), charset);
					Files.write(Paths.get(toPath), lines, charset, StandardOpenOption.WRITE);
				} catch(Exception e) {
					e.printStackTrace();
				}
			case BYTE:
				try {
					byte[] bytes = Files.readAllBytes(Paths.get(fromPath));
					Files.write(Paths.get(toPath), bytes, StandardOpenOption.WRITE);
				} catch(Exception e) {
					e.printStackTrace();
				}
			default:
				throw new IllegalArgumentException("check you parameters");
		}
	}
}
