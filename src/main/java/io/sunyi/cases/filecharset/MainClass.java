package io.sunyi.cases.filecharset;

import java.io.IOException;

public class MainClass
{
	public static void main(String[] args) throws IOException
	{
		String gb2312 = FileCharsetUtils.getFileCharset("src/main/resource/io/sunyi/cases/filecharset/gb2312.txt");
		System.out.println(gb2312);
		
		String utf8 = FileCharsetUtils.getFileCharset("src/main/resource/io/sunyi/cases/filecharset/utf-8.txt");
		System.out.println(utf8);
		
		String test = FileCharsetUtils.getFileCharset("src/main/resource/io/sunyi/cases/filecharset/test1.csv");
		System.out.println(test);
	}
}
