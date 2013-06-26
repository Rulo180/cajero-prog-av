package com.application.exceptions;

public class ValidationError {
	
		private String property;
		private String error;

		//Constructores
		public ValidationError(String property, String error) {
			this.property = property;
			this.error = error;
		}
		
		//Getters y Setters
		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

	}
