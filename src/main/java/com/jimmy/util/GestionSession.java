package com.jimmy.util;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GestionSession {

	public static void enleverTousLesAttributs(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Enumeration<String> enumNomAttribut = session.getAttributeNames();

		String nomAttribut = null;
		while (enumNomAttribut.hasMoreElements()) {
			nomAttribut = enumNomAttribut.nextElement();
			session.removeAttribute(nomAttribut);
		}
	}

	public static void ajouterAttribut(HttpServletRequest request, String nomAttribut, Object attribut) {

		HttpSession session = request.getSession();
		session.setAttribute(nomAttribut, attribut);

	}

	public static void enleverAttribut(HttpServletRequest request, String nomAttribut) {

		HttpSession session = request.getSession();
		session.removeAttribute(nomAttribut);

	}

	public static Object recupererAttribut(HttpServletRequest request, String nomAttribut) {

		HttpSession session = request.getSession();
		return session.getAttribute(nomAttribut);

	}
}
