package com.cmrevoredo.s2d2.bdd.exemplo.filtros;

public final class FiltroPhishing {

	public static boolean isPhishing(String xml){
		if (xml.contains("<in_database>")){
			String resultado = xml.substring(xml.indexOf("<in_database>")+13, xml.indexOf("</in_database>"));
			return new Boolean(resultado);
		}
		return false;
	}

}
