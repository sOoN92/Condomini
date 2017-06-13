package it.nicola.la.figa.non.esiste.giorno3;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Appartamento", schema="gestione_condomini")
public class Appartamento {
	
	@Id
	@GeneratedValue
	private int idAppartamento;
	private double dimensioneAppartamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fKey_Proprietario", nullable = false)
	private Proprietario proprietario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fKey_Condominio", nullable = false)
	private Condominio condominio;
	
	final static int PREZZO_A_MQ = 150;

	public int getIdAppartamento() {
		return idAppartamento;
	}

	public void setIdAppartamento(int idAppartamento) {
		this.idAppartamento = idAppartamento;
	}

	public double getDimensioneAppartamento() {
		return dimensioneAppartamento;
	}

	public void setDimensioneAppartamento(double dimensioneAppartamento) {
		this.dimensioneAppartamento = dimensioneAppartamento;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return "Appartamento [idAppartamento=" + idAppartamento + ", dimensioneAppartamento=" + dimensioneAppartamento
				+ ", proprietario=" + proprietario + ", condominio=" + condominio + "]";
	}

	public double costoManutenzione() {
		return dimensioneAppartamento * PREZZO_A_MQ;
	}
}
