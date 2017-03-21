package DataStructure;

import Battlefield.Bug;

public class EnemiesOnTheRoad<T> {
	private EnemyNode<T> firstEnemy;
	private int enemyCount;
	
	public EnemiesOnTheRoad(){
		firstEnemy = null;
		this.enemyCount=0;
	}
/*	public void addEnemy(EnemyNode<T> enemy){
		EnemyNode<T> temp=new EnemyNode(enemy);
		EnemyNode currentEnemy= this.firstEnemy;
		
		while (currentEnemy.getNext()!=null) {
			currentEnemy=currentEnemy.getNext();
		}
		currentEnemy.setNext(temp);
		this.enemyCount++;
	}*/
	
	
	
	public void addEnemy(EnemyNode<T> enemy){
		EnemyNode<T> temp=enemy;
		//temp.setEnemy(enemy);
		if (firstEnemy==null) {
			firstEnemy=temp;
			this.enemyCount++;
		}
		else
		{
			EnemyNode<T> currentEnemy;
			currentEnemy=getFirstEnemy();
			while(currentEnemy.getNext()!=null){
				currentEnemy=currentEnemy.getNext();
			}
			currentEnemy.setNext(temp);
			this.enemyCount++;
		}
	}
	
	public boolean killEnemy(int index){
		if (index<1 || index >getEnemyCount()) {
			return false;
		}
		EnemyNode<T> currentEnemy=getFirstEnemy();
		for (int i = 1; i < index; i++) {
			if (currentEnemy.getNext()==null) {
				return false;
			}
			currentEnemy=currentEnemy.getNext();
		
		}
		currentEnemy.setNext(currentEnemy.getNext().getNext());
		this.enemyCount--; // decrement the number of elements variable
		return true;
		
	}
	public String toString(){
		EnemyNode<T> currentEnemy= getFirstEnemy();
		//Bug b= (Bug)currentEnemy.getEnemy();
		String output="";
		while(currentEnemy!=null){
			output += (T)currentEnemy.getEnemy().toString();
			currentEnemy=currentEnemy.getNext();
		}
		return output;
	}
	public int getEnemyCount(){
		return this.enemyCount;
	}
	public EnemyNode<T> getFirstEnemy() {
		return this.firstEnemy;
	}
	
	
	
	
}
