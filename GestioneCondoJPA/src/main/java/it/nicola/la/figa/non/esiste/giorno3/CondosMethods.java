package it.nicola.la.figa.non.esiste.giorno3;

import java.util.List;
import java.util.Scanner;

public class CondosMethods {

	public void creazioneCondos(ServicesCrud crud_services, Scanner scanner) {
		System.out.println("#---Quanti Condomini vuoi inserire?---#");
		int numeroCondos = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < numeroCondos; i++) {
			System.out.println("#---Creazione Condominio " + (i + 1) + "---#");
			Condominio condominio = new Condominio();
			System.out.println("#---Quanti appartamenti ha il condominio?---#");
			int nAppartamenti = scanner.nextInt();
			scanner.nextLine();
			condominio.setnAppartamenti(nAppartamenti);
			crud_services.jpaCreate(condominio);
			for (int j = 0; j < nAppartamenti; j++) {
				System.out.println("#--Creazione Appartamento--#");
				Appartamento appartamento = new Appartamento();
				System.out.println("#--Inserisci la dimensione dell'appartamento--#");
				int dimensioneAppartamento = scanner.nextInt();
				scanner.nextLine();
				appartamento.setDimensioneAppartamento(dimensioneAppartamento);
				appartamento.setCondominio(condominio);
				System.out.println("#--Creazione Proprietario dell'Appartamento--#");
				// Controllo e Inserimento Codice Fiscale del Proprietario dell'Appartamento
				Proprietario proprietario = new Proprietario();
				checkCF(scanner,proprietario);
				proprietario.getListaAppartamenti().add(appartamento);
				crud_services.jpaCreate(proprietario);
				condominio.getListaAppartamenti().add(appartamento);
				
				appartamento.setProprietario(proprietario);
				crud_services.jpaCreate(appartamento);
				
				crud_services.jpaUpdate(condominio);
			}
		}
	}
	
	public void updateCondos(ServicesCrud crud_services, Scanner scanner){
		System.out.println("#---Quale Condominio vuoi aggiornare?---#");
		List<Condominio> resultList = crud_services.jpaRead("select c from Condominio c").getResultList();
		resultList.stream().forEach(System.out::println);
		System.out.println("#---Selziona un Condominio tramite il suo ID---#");
		int scelta = scanner.nextInt();
		scanner.nextLine();
		System.out.println("#---Cosa vuoi modificare?---#");
		System.out.println("1 - Numero Appartamenti - 2 - Dimensione Appartamento - 3 - CF Proprietario");
		int scelta2 = scanner.nextInt();
		scanner.nextLine();
		switch (scelta2) {
		case 1:
			Condominio condominio = (Condominio) crud_services.jpaRead("select c from Condominio c").getSingleResult();
			List<Integer> nApp = crud_services.jpaRead("select c.nAppartamenti from Condominio c where idCondominio="+scelta).getResultList();
			System.out.println("Quanti appartamenti vuoi aggiungere?");
			int appAgg = scanner.nextInt();
			scanner.nextLine();
			for(int i = 0; i<(appAgg-nApp.size());i++){
				System.out.println("#--Creazione Appartamento--#");
				Appartamento appartamento = new Appartamento();
				System.out.println("#--Inserisci la dimensione dell'appartamento--#");
				int dimensioneAppartamento = scanner.nextInt();
				scanner.nextLine();
				appartamento.setDimensioneAppartamento(dimensioneAppartamento);
				appartamento.setCondominio(condominio);
				System.out.println("#--Vuoi associare l'appartamento ad un nuovo proprietario o ad uno esistente?--#");
				System.out.println("#--Premi 1 per nuovo || Premi 2 per esistente--#");
				int scelta3 = scanner.nextInt();
				scanner.nextLine();
				if (scelta3==1) {
					System.out.println("#--Creazione Proprietario dell'Appartamento--#");
					// Controllo e Inserimento Codice Fiscale del Proprietario dell'Appartamento
					Proprietario proprietario = new Proprietario();
					checkCF(scanner, proprietario);
					proprietario.getListaAppartamenti().add(appartamento);
					crud_services.jpaCreate(proprietario);
					condominio.getListaAppartamenti().add(appartamento);
					
					appartamento.setProprietario(proprietario);
				} else if(scelta3==2) {
					System.out.println("#--Ecco la lista dei Proprietari--#");
					List<Proprietario> resultList1 = crud_services.jpaRead("select p from Proprietario p").getResultList();
					resultList1.stream().forEach(System.out::println);
					System.out.println("#--Selziona un proprietario tramite il suo ID--#");
					int sceltaP = scanner.nextInt();
					scanner.nextLine();
					Proprietario prop = (Proprietario) crud_services.jpaRead("select p from Proprietario p where idProprietario="+sceltaP).getSingleResult();
					prop.getListaAppartamenti().add(appartamento);
					condominio.getListaAppartamenti().add(appartamento);
				} else {
					System.out.println("#--Selezione errata--#");
				}
				crud_services.jpaCreate(appartamento);
				crud_services.jpaUpdate(condominio);
			}
			break;
		case 2:
			System.out.println("#--Ecco la lista degli Appartamenti--#");
			List<Appartamento> resultList1 = crud_services.jpaRead("select a from Appartamento a").getResultList();
			resultList1.stream().forEach(System.out::println);
			System.out.println("#--Selziona un appartamento tramite il suo ID--#");
			int sceltaA = scanner.nextInt();
			scanner.nextLine();
			Appartamento app = (Appartamento) crud_services.jpaRead("select a from Appartamento a where idAppartamento="+sceltaA).getSingleResult();
			System.out.println("#--Inserisci la nuova dimensione--#");
			int dimensioneAppartamento = scanner.nextInt();
			scanner.nextLine();
			app.setDimensioneAppartamento(dimensioneAppartamento);
			crud_services.jpaUpdate(app);
			break;
		case 3:
			System.out.println("#--Ecco la lista dei Proprietari--#");
			List<Proprietario> resultList2 = crud_services.jpaRead("select p from Proprietario p").getResultList();
			resultList2.stream().forEach(System.out::println);
			System.out.println("#--Selziona un proprietario tramite il suo ID--#");
			int sceltaP = scanner.nextInt();
			scanner.nextLine();
			Proprietario prop = (Proprietario) crud_services.jpaRead("select p from Proprietario p where idProprietario="+sceltaP).getSingleResult();
			System.out.println("#--Inserisci il C.F. del Proprietario (che sia valido PORCODIO!)--#");
			String codiceFiscale = scanner.nextLine();
			checkCF(scanner, prop);
			crud_services.jpaUpdate(prop);
			break;
		default:
			System.out.println("###---ALLORA E' VERO CHE SEI STRONZO---###");
			System.out.println("###---RITENTA SARAI PIU' FORTUNATO---###");
			updateCondos(crud_services, scanner);
			break;
		}
		
	}
	
	public void deleteCondos(ServicesCrud crud_services, Scanner scanner) {
		System.out.println("#--Ecco la lista dei Condomini--#");
		List<Condominio> resultList = crud_services.jpaRead("select c from Condominio c").getResultList();
		resultList.stream().forEach(System.out::println);
		System.out.println("#--Selziona un Condominio da cancellare tramite il suo ID--#");
		int scelta = scanner.nextInt();
		scanner.nextLine();
		Condominio cond = (Condominio) crud_services.jpaRead("select c from Condominio c where idCondominio="+scelta).getSingleResult();
		crud_services.jpaDelete(cond);
		System.out.println("#--Bye Bye Condominio--#");
	}
	
	
	private Proprietario checkCF(Scanner scanner,Proprietario proprietario) {
		System.out.println("#--Inserisci il C.F. del Proprietario (che sia valido PORCODIO!)--#");
		String codiceFiscale = scanner.nextLine();

		if (codiceFiscale.matches(Proprietario.REGEX)) {
			System.out.println("#--Codice Fiscale Valido--#");
			proprietario.setCodiceFiscale(codiceFiscale);
		} else {
			System.out.println("#--Codice Fiscale NON Valido--#");
			System.out.println("#--Reinserire il codice Fiscale--#");
			checkCF(scanner,proprietario);
		}
		return proprietario;
	}
	
	public void proprietarioPaga(ServicesCrud crud_services, Scanner scanner){
		System.out.println("#--Ecco la lista dei Proprietari--#");
		List<Proprietario> resultList = crud_services.jpaRead("select p from Proprietario p").getResultList();
		resultList.stream().forEach(System.out::println);
		System.out.println("#--Selziona un proprietario tramite il suo ID--#");
		int scelta = scanner.nextInt();
		scanner.nextLine();
		Proprietario prop = (Proprietario) crud_services.jpaRead("select p from Proprietario p where idProprietario="+scelta).getSingleResult();
		Condominio cond = (Condominio) crud_services.jpaRead("select c from Condominio c").getSingleResult();
		Appartamento app = (Appartamento) crud_services.jpaRead("select a from Appartamento a where fKey_Proprietario="+scelta).getSingleResult();
		double dimAppCond = cond.getListaAppartamenti().stream().mapToDouble(c->c.getDimensioneAppartamento()).sum();
		
		double costoGestioneApp = dimAppCond/app.getDimensioneAppartamento();
		double costoCond = cond.speseGestione();
		
		double costoAppPerProp = app.costoManutenzione();
		double costoTot = (costoAppPerProp + costoCond)/costoGestioneApp;
		System.out.println("#--Il proprietario "+prop+" paga complessivamente:"+ costoTot+"€--#");
	}

	public void condominioCaro(ServicesCrud crud_services, Scanner scanner) {
		System.out.println("#--Vuoi sapere il Condominio dei ricchi di merda?--#");
		System.out.println("#--Sei nel posto giusto, porco din don dan--#");
		Condominio condominoDeiRicchi = (Condominio) crud_services.jpaRead
				("select c from Condominio c where c.nAppartamenti = (select max(cc.nAppartamenti) from Condominio cc where cc.idCondominio = c.idCondominio)").getSingleResult();
		System.out.println(condominoDeiRicchi);
	}
	
	public void appartamentoCaro(ServicesCrud crud_services, Scanner scanner) {
		System.out.println("#--Vuoi sapere l'Appartamento dei ricchi di merda?--#");
		System.out.println("#--Sei nel posto giusto, porco din don dan--#");
		Appartamento appartamentoDeiRicchi = (Appartamento) crud_services.jpaRead
				("select c from Appartamento c where c.dimensioneAppartamento = (select max(cc.dimensioneAppartamento) from Appartamento cc where cc.idAppartamento = c.idAppartamento)").getSingleResult();
		appartamentoDeiRicchi.toString();
	}
	
	public void costoMedioCondomini(ServicesCrud crud_services, Scanner scanner) {
		System.out.println("#--Vuoi sapere il costo medio dei Condomini?--#");
		System.out.println("#--Sei nel posto giusto, porco din don dan--#");	
		int condo = 0;
		List<Condominio> cond = crud_services.jpaRead("select c from Condominio c").getResultList();
		for (Condominio condominio : cond) {
			List<Appartamento> listaApp = condominio.getListaAppartamenti();
			for (Appartamento appartamento : listaApp) {
				condo += appartamento.costoManutenzione();
			}
			condo += condominio.speseGestione();
		}
		condo = condo / cond.size();
		System.out.println("#--Il costo medio per condominio è "+condo+"€--#");		
	}
}
