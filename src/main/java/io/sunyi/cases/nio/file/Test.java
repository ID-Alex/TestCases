package io.sunyi.cases.nio.file;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author sunyi
 *         Created on 15/11/17
 */
public class Test {


	private static String BASE_DIR = "/Users/sunyi/Documents/test/";

	public static void main(String args[]) throws IOException {

		String fileName = "a.txt";

		String filePth = BASE_DIR + fileName;

		File file = new File(filePth);
		if (!file.exists()) {
			file.createNewFile();
		}

		RandomAccessFile raf = new RandomAccessFile(file, "rws");

		raf.seek(10);

		raf.writeChars("---------");

		raf.close();


	}
}
