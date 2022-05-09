package com.example.limupashope.service;

public interface EmailSenderService {

	void sendSimpleMessage(String to, String subject, String text);

}
