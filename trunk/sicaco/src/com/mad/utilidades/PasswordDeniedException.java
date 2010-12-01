package com.mad.utilidades;

public class PasswordDeniedException extends Exception {
		public PasswordDeniedException(){
			super();
		}
		public PasswordDeniedException(String keyMessage){
			super(keyMessage);
		}
}
