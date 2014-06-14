package com.vista;

import java.util.Comparator;

public class OrdenarPorNumero implements Comparator<FicherosList>{

	@Override
	public int compare(FicherosList o1, FicherosList o2) {
		double f = o1.getArrayHash() - (o2.getArrayHash());
		if (f==0) 
			return 0;
		else if (f < 0.0)
			return -1;
		else
			return 1;
	}

}
