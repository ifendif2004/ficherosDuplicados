package com.vista;

import java.security.*;

//import javax.crypto.*;
//import javax.crypto.interfaces.*;
//import javax.crypto.spec.*;

import java.io.*;

/** Calculo del Hash al fichero de entrada.
 *  Carga el fichero que recibe como parametro y genera el resumen.
 */

public class CalculoHash {
	
    public static byte[] calcularResumen(File fichero) throws Exception {

		/* Crear función resumen */
		MessageDigest messageDigest = MessageDigest.getInstance("MD5"); // Usa MD5
		// MessageDigest messageDigest = MessageDigest.getInstance("SHA"); // Usar SHA-1

		/* Leerr fichero de 1k en 1k y pasar fragmentos leidos a la funcion resumen */
		int longitud = 10000000; 
		byte[] buffer = new byte[longitud];
		FileInputStream in = new FileInputStream(fichero);
		int leidos = in.read(buffer, 0, buffer.length);
		while (leidos != -1) {
			messageDigest.update(buffer); // Pasa texto claro a la función resumen
			leidos = in.read(buffer, 0, longitud);
		}
		in.close();
			
		byte[] resumen = messageDigest.digest(); // Completar el resumen
		return resumen;
	}

}