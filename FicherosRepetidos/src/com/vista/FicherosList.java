package com.vista;

public class FicherosList {

	private String nombreFichero;
	private String nombreDirectorio;
	private int arrayHash;
	
	public FicherosList(String nombreDirectorio, String nombreFichero, int arrayHash  ){
		this.nombreFichero = nombreFichero;
		this.nombreDirectorio = nombreDirectorio;
		this.arrayHash = arrayHash;
	}
	
	public String getNombreFichero() {
		return nombreFichero;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	public String getNombreDirectorio() {
		return nombreDirectorio;
	}
	public void setNombreDirectorio(String nombreDirectorio) {
		this.nombreDirectorio = nombreDirectorio;
	}
	public int getArrayHash() {
		return arrayHash;
	}
	public void setArrayHash(int arrayHash) {
		this.arrayHash = arrayHash;
	}

//
//
//	@Override
//	public int compare(FicherosList o1, FicherosList o2) {
// 		double f = o1.getArrayHash() - (o2.getArrayHash());
//		if (f==0) 
//			return 0;
//		else if (f < 0.0)
//			return -1;
//		else
//			return 1;
//	}
}