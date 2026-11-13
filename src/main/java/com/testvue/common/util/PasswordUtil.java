package com.testvue.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 * 비밀번호는 SHA-256 16진수로 저장한다(TBSY_LOGIN.USER_PWD).
 * </pre>
 */
public final class PasswordUtil {

	private PasswordUtil() {
	}

	public static String sha256(String plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(plain.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				sb.append(String.format("%02x", hash[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
}
