package it.nicola.la.figa.non.esiste.giorno3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ServicesCrud crud_services = new ServicesCrud("porco-din-don-dan");
		Scanner scanner = new Scanner(System.in);
		CondosMethods gestione = new CondosMethods();
		while(true){
		System.out.println("#----Benvenuto nel sistema di gestione CONDO(M)s----#");
		System.out.println("(siamo poveri come le merde, perciò necessitiamo che qualcuno ci aggiunga almeno un condominio)");
		System.out.println("###--Che vuoi fare ora?--##");
		System.out.println("#--Premi 1 per creare un condos "
				+ "/ 2 Per aggiornare "
				+ "/ 3 per cancellare "
				+ "/ 0 per altre mansioni "
				);
		int scelta = scanner.nextInt();
		scanner.nextLine();
		switch (scelta) {
		case 1:
			gestione.creazioneCondos(crud_services, scanner);
			System.out.println("###---BRAVO COJONE! HAI CREATO UN CONDOMINIO E AGGIUNTO A I SUOI APPARTAMENTI UN PROPRIETARIO---###");
			break;
		case 2:
			gestione.updateCondos(crud_services, scanner);
			System.out.println("###---BRAVO COJONE! HAI AGGIORNATO IL CAZZO CHE TE PARE---###");
			break;
		
		case 3:
			gestione.deleteCondos(crud_services,scanner);
			System.out.println("###---BRAVO COJONE! HAI CANCELLATO IL MONDO---###");
		case 0:
			System.out.println("#---Che voi fa?---#");
			System.out.println("#--Premi "
					+ "1 Per sapere quanto paga un proprietario "
					+ "/ 2 per sapere il Condos più caro "
					+ "/ 3 per sapere l'appartamento più caro "
					+ "/ 4 per sapere il costo medio dei condomini");
			int scelta2 = scanner.nextInt();
			scanner.nextLine();
			switch(scelta2)
			{
			case 1:
				gestione.proprietarioPaga(crud_services, scanner);
				System.out.println("###---NON POTRAI MAI PERMETTERTI UN NOSTRO APPARTAMENTO---###");
				break;
			case 2:
				gestione.condominioCaro(crud_services,scanner);
				System.out.println("###---AMMAPPA QUANTO JE COSTA---###");
				break;
			case 3:
				gestione.appartamentoCaro(crud_services, scanner);
				System.out.println("###---PORCODIO QUANTO SEI POVERO---###");
				break;
			case 4:
				gestione.costoMedioCondomini(crud_services, scanner);
				System.out.println("###---TUTTO SOMMATO ... NO SEI POVERO---###");
				break;
			default:
				System.out.println("###---SE TI DICO DI PREMERE DA 1 A 4, PORCA LA MADONNA NON SBAGLIARE---###");
				System.out.println("###--DATO CHE MI HAI FATTO GIRARE LE PALLE--###");
				System.out.println("###-MI SA MI SA CHE TI BRUCIO IL PC. ADDIOS-###");
				System.out.close();
				break;
			}
	}
		}

}
	
}
