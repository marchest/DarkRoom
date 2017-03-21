package Battlefield;

import BaseClasses.Creature;

public final class Lion extends Creature {

	public Lion() {
		super();
	}


	public Lion(int healthpoint, String name, int attackPower, int position) {
		super(healthpoint, name, attackPower, position);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Lion";
	}
	
	
}
