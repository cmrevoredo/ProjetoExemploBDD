package com.cmrevoredo.s2d2.bdd.exemplo;

import org.jbehave.scenario.Scenario;

public class InjecaoScriptTest extends Scenario {

	public InjecaoScriptTest() {
		addSteps(new AtaqueXSS());
	}

}
