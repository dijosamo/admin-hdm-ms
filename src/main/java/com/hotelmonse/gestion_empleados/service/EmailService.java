package com.hotelmonse.gestion_empleados.service;

public interface EmailService {
	void enviarEmail(String to, String subject, String texto);
}
