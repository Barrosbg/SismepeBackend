package br.gov.pe.sismepe.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Random random = new Random();
	
	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) { //gera um dígito
			return (char) (random.nextInt(10) + 48);
			
		} else if(opt == 1) { //gera uma letra maiuscula
			return (char) (random.nextInt(26) + 65);
			
		} else { //gera uma letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}

}