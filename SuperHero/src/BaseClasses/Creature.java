package BaseClasses;

public abstract class Creature implements ICreature {
	
	private int healthpoint;
	private String name;
	private int attackPower;
	private int position;
	public Creature(int healthpoint,String name,int attackPower,int position)
	{
		this.healthpoint=healthpoint;
		this.name=name;
		this.attackPower=attackPower;
		this.position=position;
	}
	
	public Creature(){
		
	}
	

	@Override
	public int attack() {
		return getHealthpoint() * getAttackPower();
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getHealthpoint() {
		return healthpoint;
	}

	public void setHealthpoint(int healthpoint) {
		this.healthpoint = healthpoint;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public abstract String toString();
	
	
}

