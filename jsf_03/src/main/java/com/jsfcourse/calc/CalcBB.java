package com.jsfcourse.calc;

import java.io.Serializable;
import java.util.ResourceBundle;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CalcBB implements Serializable {
    private String kwotaKredytu;
    private String oprocentowanie;
    private String okres;
    private Double miesiecznaRata;

  // Resource injected
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;

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

    public String obliczRaty() {
        try {
            double kwotaKredytu = Double.parseDouble(this.kwotaKredytu);
            double oprocentowanie = Double.parseDouble(this.oprocentowanie) / 100.0;
            int okres = Integer.parseInt(this.okres);

            double q = 1 + oprocentowanie / 12;
            double miesiecznaRata = kwotaKredytu * Math.pow(q, okres) * ((q - 1) / (Math.pow(q, okres) - 1));
        
            String result = Double.toString(miesiecznaRata);

            ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, txtCalcErr.getString("calcComputationOkInfo"), null));
		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, txtCalc.getString("result") + ": " + result, null));
		return null;
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtCalcErr.getString ("calcParamIncorrect"), null));
            return null;
        }
    }


    public String informacje() {
        return "info";
    }
}

