package com.epam.web.util.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
	private static PasswordEncryptor instance = new PasswordEncryptor();
	
	private PasswordEncryptor() {
		
	}
	
	public static PasswordEncryptor getInstance() {
		return instance;
	}

	public static String encodePassword(String password) {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashed;
	}
	
	public static boolean checkPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
