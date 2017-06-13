package it.nicola.la.figa.non.esiste.giorno3;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Condominio", schema="gestione_condomini")
public class Condominio {
	
	@Id
	@GeneratedValue
	private int idCondominio;
	@Column(nullable = false)
	private int nAppartamenti;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "condominio")
	private List<Appartamento> listaAppartamenti = new ArrayList<>();
	
	final static int PREZZO_APP_COND = 50;
		
	public int getIdCondominio() {
		return idCondominio;
	}

	public void setIdCondominio(int idCondominio) {
		this.idCondominio = idCondominio;
	}

	public int getnAppartamenti() {
		return nAppartamenti;
	}

	public void setnAppartamenti(int nAppartamenti) {
		this.nAppartamenti = nAppartamenti;
	}

	public List<Appartamento> getListaAppartamenti() {
		return listaAppartamenti;
	}

	public void setListaAppartamenti(List<Appartamento> listaAppartamenti) {
		this.listaAppartamenti = listaAppartamenti;
	}
	
	@Override
	public String toString() {
		return "Condominio [idCondominio=" + idCondominio + ", nAppartamenti=" + nAppartamenti + "]";
	}

	public int speseGestione() {
		return nAppartamenti * PREZZO_APP_COND;
	}	

}
