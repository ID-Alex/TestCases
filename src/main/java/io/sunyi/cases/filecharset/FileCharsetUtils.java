package io.sunyi.cases.filecharset;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

public class FileCharsetUtils
{

	public static String getFileCharset(String filePath) throws IOException
	{
		return getFileCharset2(new File(filePath));
	}

	/** 只使用 org.mozilla.intl.chardet.nsDetector. 可以自定义检查长度.
	 * 
	 * @param file
	 * @return
	 * @throws IOException */
	public static String getFileCharset2(File file) throws IOException
	{
		nsICharsetDetectionObserverImpl observer = new nsICharsetDetectionObserverImpl();
		nsDetector det = new nsDetector(nsDetector.ALL);
		det.Init(observer);

		BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));

		byte[] buf = new byte[1024];
		int len;

		while ((len = stream.read(buf, 0, buf.length)) != -1)
		{

			det.DoIt(buf, len, false);
		}

		det.DataEnd();

		return observer.encoding;
	}

//	/** http://www.iteye.com/topic/108540
//	 * 
//	 * @param file
//	 * @return
//	 * @throws IOException */
//	@SuppressWarnings("deprecation")
//	public static String getFileCharset(File file) throws IOException
//	{
//		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//		detector.add(new ParsingDetector(false));
//		detector.add(JChardetFacade.getInstance());
////		detector.add(ASCIIDetector.getInstance());
////		detector.add(UnicodeDetector.getInstance());
//		java.nio.charset.Charset charset = null;
//		try
//		{
//			charset = detector.detectCodepage(file.toURL());
//		} catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//		return charset.displayName();
//	}

	private static class nsICharsetDetectionObserverImpl implements nsICharsetDetectionObserver
	{
		private String encoding = "";

		public void Notify(String charset)
		{
			this.encoding = charset;
		}
	}
}
