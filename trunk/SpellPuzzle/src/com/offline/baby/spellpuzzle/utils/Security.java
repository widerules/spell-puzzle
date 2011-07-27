package com.offline.baby.spellpuzzle.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class Security {
	protected static char HEXDIGITS[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getHash(InputStream fis) throws Exception {
		long time = System.currentTimeMillis();
		byte[] buffer = new byte[1024 * 8];
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		time = System.currentTimeMillis() - time;
		return toHexString(md5.digest());
	}

	public static String getHash(String fileName) throws Exception {
		long time = System.currentTimeMillis();
		InputStream fis = new FileInputStream(fileName);
		byte[] buffer = new byte[1024 * 8];
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		time = System.currentTimeMillis() - time;
		return toHexString(md5.digest());
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEXDIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEXDIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

}
