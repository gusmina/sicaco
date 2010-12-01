package com.mad.utilidades;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.mail.SimpleEmail;

/**
 * 
 * @author Victor Salazar
 * @category Utilidad
 *
 */

public class ThreadEmail extends Thread {
	private   String ipServer;
	private   int port;
	private   String from;
	private   String[] to;
	private   String msg;
	private   String header;
	private   String toName;
	private   String fromName;
	
	

	public ThreadEmail(String ipServer, int port, String from, String[] to,
			String msg, String header, String toName, String fromName) {
		super();
		this.ipServer = ipServer;
		this.port = port;
		this.from = from;
		this.to = to;
		this.msg = msg;
		this.header = header;
		this.toName = toName;
		this.fromName = fromName;
	}



	/**
	 * Run de ThreadEmail que es
	 * el hilo utilizado para mandar
	 * los correos electronicos
	 */
	public void run() {
		// TODO Auto-generated method stub
		if(to != null &&  to.length > 0){	
			for(String toItem : Arrays.asList(to)){	
				SimpleEmail email;
				try {
					
					  email = new SimpleEmail();
//					  email.setSSL(true);
					  System.out.println("SSL: " + email.isSSL());
					  email.setHostName(ipServer);
					  System.out.println("Servidor: " + ipServer);
					  email.setSmtpPort(port);
					  System.out.println("Puerto: " + port);
					  email.addTo(toItem,toName);
					  System.out.println("Destinatario: " + toItem + " - " + toName);
					  email.setFrom(from, fromName);
					  System.out.println("Remitente: "  + from + " - " + fromName);
					  email.setSubject(header);
					  System.out.println("Cabecera: " + header);
					  email.setMsg(msg);
					  System.out.println("Mensaje: " + msg);
					  email.setSentDate(new Date());
					  email.setDebug(true);
					  email.send();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		
	}
		

}
