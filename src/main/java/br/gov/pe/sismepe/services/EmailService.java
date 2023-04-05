package br.gov.pe.sismepe.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(MimeMessage msg);
}