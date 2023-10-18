package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcBB {
    private String kwotaKredytu;
    private String oprocentowanie;
    private String okres;
    private Double miesiecznaRata;

    @Inject
    FacesContext ctx;

    public String getKwotaKredytu() {
        return kwotaKredytu;
    }

    public void setKwotaKredytu(String kwotaKredytu) {
        this.kwotaKredytu = kwotaKredytu;
    }

    public String getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(String oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    public String getOkres() {
        return okres;
    }

    public void setOkres(String okres) {
        this.okres = okres;
    }

    public Double getMiesiecznaRata() {
        return miesiecznaRata;
    }

    public void setMiesiecznaRata(Double miesiecznaRata) {
        this.miesiecznaRata = miesiecznaRata;
    }

    public boolean obliczRaty() {
        try {
            double kwota = Double.parseDouble(kwotaKredytu);
            double oprocentowanie = Double.parseDouble(this.oprocentowanie) / 100.0;
            int okres = Integer.parseInt(this.okres);

            double q = 1 + oprocentowanie / 12;
            double rata = kwota * Math.pow(q, okres) * ((q - 1) / (Math.pow(q, okres) - 1));
            miesiecznaRata = rata;

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obliczenia wykonane poprawnie", null));
            return true;
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
            return false;
        }
    }

    public String oblicz() {
        if (obliczRaty()) {
            return "showresult";
        }
        return null;
    }

    public String informacje() {
        return "info";
    }
}
