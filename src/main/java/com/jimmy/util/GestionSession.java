package com.jimmy.util;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GestionSession {

	public static void effacerTousLesAttributs(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Enumeration<String> enumNomAttribut = session.getAttributeNames();

		String nomAttribut = null;
		while (enumNomAttribut.hasMoreElements()) {
			nomAttribut = enumNomAttribut.nextElement();
			System.out.println("On supprime l'attribut de session " + nomAttribut);
			session.removeAttribute(nomAttribut);
		}
	}
}
