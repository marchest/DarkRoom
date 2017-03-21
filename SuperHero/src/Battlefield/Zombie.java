package Battlefield;

import BaseClasses.Creature;

public final class Zombie extends Creature {

	public Zombie() {
		super();
	}



	public Zombie(int healthpoint, String name, int attackPower, int position) {
		super(healthpoint, name, attackPower, position);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return this.getName()+" ";
	}

	


}
