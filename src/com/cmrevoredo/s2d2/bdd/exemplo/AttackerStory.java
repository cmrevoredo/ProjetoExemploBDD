package com.cmrevoredo.s2d2.bdd.exemplo;

import org.jbehave.scenario.Scenario;

import com.cmrevoredo.s2d2.bdd.exemplo.steps.AtaqueXSS;

public class AttackerStory extends Scenario {

	public AttackerStory() {
		addSteps(new AtaqueXSS());
	}

}
