package Battlefield;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import BaseClasses.Creature;
import BaseClasses.IHero;

public final class Hero extends Creature implements IHero {

	private int enemyIndex;
	public Hero() {
		super();
	}
	public Hero(int healthpoint, String name, int attackPower, int position) {
		super(healthpoint, name, attackPower, 0);
		// TODO Auto-generated constructor stub
	}
	public int attack(Creature creature) {
		
		this.setHealthpoint((calculateHp()- creature.attack()) / this.getAttackPower());
		return this.getHealthpoint();
	}

	@Override
	public void walk() {

		this.setPosition(this.getPosition()+1);
	}

	@Override
	public String toString() {

		return "Hero started journey with "+this.getHealthpoint()+" HP";
	}

	@Override
	public int calculateHp() {

		return this.getAttackPower() * this.getHealthpoint();
	}
	public int getEnemyIndex() {
		return enemyIndex;
	}
	public void setEnemyIndex(int enemyIndex) {
		this.enemyIndex = enemyIndex;
	}
	@Override
	public boolean isRisky(ArrayList<Object> checkForEnemies) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		int index=0;
		for (Object object : checkForEnemies) {
			
			if(this.getPosition()==(int)object.getClass().getSuperclass().getMethod("getPosition").invoke(object)){
				this.setEnemyIndex(index);
				index++;
				return true;

			}
			break;
		}
		return false;
	}




}
