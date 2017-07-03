package com.cmrevoredo.s2d2.bdd.exemplo;

import org.jbehave.scenario.Scenario;

import com.cmrevoredo.s2d2.bdd.exemplo.steps.AtaquePhishing;

public class TricksterStoryPhishTank extends Scenario {

	public TricksterStoryPhishTank() {
		addSteps(new AtaquePhishing());
	}

}
