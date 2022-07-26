package com.jimmy.util;

import java.time.LocalDate;

public class JourDeLaSemaine {

	public static String nomCourt(LocalDate date) {

		String nomCourt = null;

		switch (date.getDayOfWeek().getValue()) {
		case 1:
			nomCourt = "Lun";
			break;
		case 2:
			nomCourt = "Mar";
			break;
		case 3:
			nomCourt = "Mer";
			break;
		case 4:
			nomCourt = "Jeu";
			break;
		case 5:
			nomCourt = "Ven";
			break;
		case 6:
			nomCourt = "Sam";
			break;
		case 7:
			nomCourt = "Dim";
		}

		return nomCourt;

	}
}
