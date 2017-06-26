package com.cmrevoredo.s2d2.bdd.exemplo;

import org.jbehave.scenario.Scenario;

public class PhishingTest extends Scenario {

	public PhishingTest() {
		addSteps(new AtaquePhishing());
	}

}
