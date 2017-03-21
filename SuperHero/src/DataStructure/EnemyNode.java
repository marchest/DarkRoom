package DataStructure;

public class EnemyNode<T> {
	private T enemy;
	private EnemyNode<T> next;
	
	public EnemyNode(T enemy) {
	
		this.enemy = enemy;
	}
	public EnemyNode(T enemy,EnemyNode<T> next){
		this.next=next;
		this.enemy=enemy;
	}
	public T getEnemy() {
		return enemy;
	}
	public void setEnemy(T enemy) {
		this.enemy = enemy;
	}
	public EnemyNode<T> getNext() {
		return next;
	}
	public void setNext(EnemyNode<T> next) {
		this.next = next;
	}
	
	
}
