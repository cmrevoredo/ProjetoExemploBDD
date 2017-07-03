package com.cmrevoredo.s2d2.bdd.exemplo.steps;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;
import org.junit.Assert;

import com.cmrevoredo.s2d2.bdd.exemplo.filtros.FiltroPhishing;
import com.cmrevoredo.s2d2.bdd.utils.HttpRequestor;

public class AtaquePhishing extends Steps {

	private boolean isPhishing = false; 
	
	@Given("Acessar a url $url")
	public void informarURL(String url) {

	}

	@When("Consultar no $repositorio a url $url")
	public void preencherDadosDoProduto(String repositorio, String url) {
		switch (repositorio.toLowerCase()) {
		case "phishtank":			
			try {
				String resultado = HttpRequestor.get(FiltroPhishing.URL_PHISHTANK+url);
				isPhishing = FiltroPhishing.getInstance().isPhishingPhishTank(resultado);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}				
			break;
		case "safebrowsing":			
			try {
				String payload = FiltroPhishing.getInstance().getJsonPayLoadString().replace("$XXX___XXX$", url);
				String resultado = HttpRequestor.post(FiltroPhishing.URL_SAFE_BROWSING, payload);
				isPhishing = FiltroPhishing.getInstance().isPhishingSafeBrowsing(resultado);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}				
			break;
		default:
			break;
		}
	}

	@Then("O resultado do filtro será $resultado")
	public void atualizarDadosDoProduto(String resultado) {
		switch (resultado) {
		case "PHISHING":
			Assert.assertTrue(isPhishing);
			break;
		case "NO-PHISHING":
			Assert.assertFalse(isPhishing);
			break;
		default:
			break;
		}		
		System.out.println("O filtro resultou em " + resultado);
	}
}
