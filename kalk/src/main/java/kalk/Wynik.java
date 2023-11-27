package kalk;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the wynik database table.
 * 
 */
@Entity
@NamedQuery(name="Wynik.findAll", query="SELECT w FROM Wynik w")
public class Wynik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idwynik;

	private float czas;

	private float kwota;

	private float oprocentowanie;

	private float rata;

	public Wynik() {
	}

	public int getIdwynik() {
		return this.idwynik;
	}

	public void setIdwynik(int idwynik) {
		this.idwynik = idwynik;
	}

	public float getCzas() {
		return this.czas;
	}

	public void setCzas(float czas) {
		this.czas = czas;
	}

	public float getKwota() {
		return this.kwota;
	}

	public void setKwota(float kwota) {
		this.kwota = kwota;
	}

	public float getOprocentowanie() {
		return this.oprocentowanie;
	}

	public void setOprocentowanie(float oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public float getRata() {
		return this.rata;
	}

	public void setRata(float rata) {
		this.rata = rata;
	}

}