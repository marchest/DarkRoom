package BaseClasses;

public class RoadToResources {
	
	private int road;
	public RoadToResources(int road){
		
		this.road=road;
	}
	public RoadToResources(){
		
	
	}
	public int getRoad() {
		return road;
	}
	public void setRoad(int road) {
		this.road = road;
	}
	public boolean isRoadFinished(int heroPos){
		if (this.getRoad()==heroPos) {
			return true;
		}
		else
		    return false;
	}
	
}
