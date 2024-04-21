package com.crowde.fenrir.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Deprecated
public class GeracaodeSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123"));

	}

}
