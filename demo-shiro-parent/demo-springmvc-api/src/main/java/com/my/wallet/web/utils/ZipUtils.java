package com.my.wallet.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* this class is for unpack the files in the zip file
 */
public class ZipUtils {

	private static Log logger = LogFactory.getLog(ZipUtils.class);
			
	private String comment = "";

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void zip(String src, String dest, List filter) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dest));
		File srcFile = new File(src);
		zip(out, srcFile, "", filter);
		out.close();
	}

	public void zip(ZipOutputStream out, File srcFile, String base, List filter) throws Exception {
		if (srcFile.exists() == false) {
			throw new Exception("压缩目录不存在!");
		}

		if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			base = base.length() == 0 ? "" : base + "/";
			if (isExist(base, filter)) {
				out.putNextEntry(new ZipEntry(base));
			}
			for (int i = 0; i < files.length; i++) {
				zip(out, files[i], base + files[i].getName(), filter);
			}
		} else {
			if (isExist(base, filter)) {
				base = base.length() == 0 ? srcFile.getName() : base;
				ZipEntry zipEntry = new ZipEntry(base);
				zipEntry.setComment(comment);
				out.putNextEntry(zipEntry);
				FileInputStream in = new FileInputStream(srcFile);
				int length = 0;
				byte[] b = new byte[1024];
				while ((length = in.read(b, 0, 1024)) != -1) {
					out.write(b, 0, length);
				}
				in.close();
			}
		}
	}

	public boolean isExist(String base, List list) {
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (base.indexOf((String) list.get(i)) >= 0) {
					return true;
				}
			}
		}
		return false;
	}
	//isUnzipToBase将所有文件解压到制定目录
	//
	public void unzip(File file, String dest, boolean deleteFile,boolean isUnzipToBase,UnzipFilter filter) {
		try {
			if (!file.exists()) {
				throw new RuntimeException("解压文件不存在!");
			}
			if(!dest.endsWith("\\")&&!dest.endsWith("/")){
				dest += File.separator;
			}
			ZipFile zipFile = new ZipFile(file);
			Enumeration e = zipFile.entries();
			while (e.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) e.nextElement();
				if (zipEntry.isDirectory()&&!isUnzipToBase) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(dest + name);
					f.mkdirs();
				} else {
					//logger.debug(dest+" | "+zipEntry.getName());
					String name = isUnzipToBase?zipEntry.getName().substring(zipEntry.getName().lastIndexOf("/")+1):zipEntry.getName();
					if(name.indexOf("Thumbs.db")>=0||"".equals(name)){
						continue;
					}
					if(filter!=null){
						if(!filter.valid(name)){
							continue;
						}
						name = filter.nameHandle(name,zipEntry);
					}
					File f = new File(dest + name);
					f.getParentFile().mkdirs();
					f.createNewFile();
					InputStream is = zipFile.getInputStream(zipEntry);
					FileOutputStream fos = new FileOutputStream(f);
					int length = 0;
					byte[] b = new byte[1024];
					while ((length = is.read(b, 0, 1024)) != -1) {
						fos.write(b, 0, length);
					}
					is.close();
					fos.close();
				}
			}

			if (zipFile != null) {
				zipFile.close();
			}

			if (deleteFile) {
				file.delete();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void unzip(File file, String dest, boolean deleteFile) {
		unzip(file, dest, deleteFile, false,null);
	}

	public void unzipToBase(File file, String dest, boolean deleteFile) {
		unzip(file,dest, deleteFile, true,null);
	}

	public void unzipToBase(String srcFile, String dest, boolean deleteFile,UnzipFilter filter){
		File file = new File(srcFile);
		unzip(file,dest, deleteFile, true,filter);
	}
	
	public void unZip(String srcFile, String dest, boolean deleteFile) {
		File file = new File(srcFile);
		this.unzip(file, dest, deleteFile);
	}

	public static String getZipComment(String srcFile) {
		String comment = "";
		try {
			ZipFile zipFile = new ZipFile(srcFile);
			Enumeration e = zipFile.entries();
			while (e.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) e.nextElement();
				comment = ze.getComment();
				if (comment != null && !comment.equals("") && !comment.equals("null")) {
					break;
				}
			}
			zipFile.close();
		} catch (Exception e) {
			logger.debug("获取zip文件注释信息失败:" + e.getMessage());
		}

		return comment;
	}

	public static void main(String[] args) throws Exception {
//		long begin = System.currentTimeMillis();
//		ZipUtils zu = new ZipUtils();
//		List<String> filter = new ArrayList<String>();
//		filter.add("3RDPARTY");
//		filter.add("BANNER.GIF");
//		zu.setComment("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		zu.zip("C:/VALUEADD", "c:/hh.zip", filter);
//		logger.debug(ZipUtils.getZipComment("c:/hh.zip"));
//		// new ZipUtil().unZip("c:/tt.zip", "c:/mmmmmmmmmmmmmmmmmmm/", true);
//		// File f = new File("c:/hh.zip");
//		// f.deleteOnExit();
//		long end = System.currentTimeMillis();
//		logger.debug(end - begin);
		ZipUtils zu = new ZipUtils();
		zu.unzipToBase("F:/leiyou.zip", "F:/aa/", false,null);
		logger.debug("完成");
	}

	
	/*
	 * 
	 * new UnzipFilterAdapter() {
		
			@Override
			public boolean valid(String name) {
				// TODO Auto-generated method stub
				return super.valid(name);
			}
		
			@Override
			public String nameHandle(String name, ZipEntry zipEntry) {
				// TODO Auto-generated method stub
				return super.nameHandle(name, zipEntry);
			}
		
		}
	 * 
	 */
	
	// 如果不需要上面的filter，可以添加fileter为空list或者用下面的程序：

	// 不带filter的zip压缩：
	// 
	// * @author WeiMiao
	// * @param out： ZipOutputStream
	// * @param srcFile： 要压缩的目录
	// * @param base： 根路径
	// * @throws Exception
	// 
	public static void zip(ZipOutputStream out, File srcFile, String base) throws Exception {
		if (!srcFile.exists()) {
			throw new Exception("压缩目录不存在！");
		}
		if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			base = base.length() == 0 ? "" : base + "/";
			if (base.length() > 0) {
				out.putNextEntry(new ZipEntry(base));
			}
			for (int i = 0; i < files.length; i++) {
				zip(out, files[i], base + files[i].getName());
			}
		} else {
			base = base.length() == 0 ? srcFile.getName() : base;
			out.putNextEntry(new ZipEntry(base));
			FileInputStream fis = new FileInputStream(srcFile);
			int length = 0;
			byte[] b = new byte[4096];
			while ((length = fis.read(b, 0, 4096)) != -1) {
				out.write(b, 0, length);
			}
			fis.close();
		}

	}
	
	public abstract class UnzipFilterAdapter implements UnzipFilter{

		public String nameHandle(String name, ZipEntry zipEntry) {
			return name;
		}

		@Override
		public boolean valid(String name) {
			return true;
		}
		
	}
	
	public interface UnzipFilter{
		boolean valid(String name);

		String nameHandle(String name, ZipEntry zipEntry);
	}
	
	/**
     * GZIP压缩
     */
    public static byte[] compress(byte[] data) throws Exception {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        GZIPOutputStream gos = new GZIPOutputStream(baos);  
        gos.write(data, 0, data.length);  
        gos.finish();  
        byte[] output = baos.toByteArray();  
        baos.flush();  
        baos.close();  
        return output;  
    }
}
