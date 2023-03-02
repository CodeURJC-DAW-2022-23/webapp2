// Java Program to Illustrate EmailDetails Class

package com.urjc.asociationPlatform.email;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

	// Class data members
	private String recipient;
	private String msgBody;
	private String subject;

	public void adminMode(String username, String email){
		this.recipient = "danigadeu@gmail.com"; 
		this.msgBody = "El usuario "+ username +" con el correo "+email +" quiere darse de alta como ASO\n\n https://127.0.0.1:8080/gestionarCreacion/"+email;
		this.subject = "Validar Creación Cuenta Aso";
	}

	public void acceptedMode(String email, String token){
		this.recipient = email;
		this.msgBody = "Tu petición ha sido aceptada.\n \n Entre en el siguiente enlace para acabar de darse de alta:\n\n https://127.0.0.1:8080/confirmarCreacion/"+email+"/"+token+ "\nUn saludo,\nPlataformaAsociacionesUrjc. ";
		this.subject = "Creación Cuenta Aso Aceptada";
	}

	public void rejectedMode(String email){
		this.recipient = email;
		this.msgBody = "Tu petición ha sido rechazada.\n \nUn saludo,\nPlataformaAsociacionesUrjc. ";
		this.subject = "Creación Cuenta Aso Denegada";
	}
}
