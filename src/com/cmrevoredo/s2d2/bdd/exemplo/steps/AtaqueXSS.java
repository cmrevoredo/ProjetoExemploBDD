package com.cmrevoredo.s2d2.bdd.exemplo.steps;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;
import org.junit.Assert;

import com.cmrevoredo.s2d2.bdd.exemplo.entity.Produto;

public class AtaqueXSS extends Steps {

	private boolean possuiXSS = false;
	
	@Given("Localizar produto $id")
	public void LocalizarDadosDoProduto(Long id) {
		
	}

	@When("Atualizar o produto $id com a descricao $descricao")
	public void preencherDadosDoProduto(Long id, String descricao) {
		try {
			Produto produto = new Produto(id, descricao);
			possuiXSS = possuiXSS(produto.getDescricao());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Then("O filtro deve capturar o ataque e identifica-lo como $resultado")
	public void atualizarDadosDoProduto(String resultado) {
		Assert.assertTrue(possuiXSS);
		System.out.println("O filtro capturou um ataque do tipo: " + resultado);
	}
	
	private boolean possuiXSS(String texto){
		return (texto.toLowerCase().contains("<script>"));
	}
}
