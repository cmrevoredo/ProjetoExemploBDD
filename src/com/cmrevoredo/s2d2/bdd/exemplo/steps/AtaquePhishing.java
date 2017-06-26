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

	@When("Consultar no PhishTank $filtro a url $url")
	public void preencherDadosDoProduto(String filtro, String url) {
		try {
			String resultado = HttpRequestor.get(filtro+"?url="+url);
			isPhishing = FiltroPhishing.isPhishing(resultado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Then("Reconhecer o ataque e identifica-lo como $resultado")
	public void atualizarDadosDoProduto(String resultado) {
		Assert.assertTrue(isPhishing);
		System.out.println("O filtro capturou um ataque do tipo: " + resultado);
	}
}
