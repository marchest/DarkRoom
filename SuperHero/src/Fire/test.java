package Fire;

import java.awt.List;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import BaseClasses.Definiton;
import BaseClasses.RoadToResources;
import BaseClasses.CommonStrings;
import BaseClasses.Creature;
import Battlefield.Bug;
import Battlefield.Hero;
import DataStructure.EnemiesOnTheRoad;
import DataStructure.EnemyNode;
import FileOperations.Chooser;
import FileOperations.ReadFile;
import FileOperations.WriteOutput;

import java.lang.reflect.Method;;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		/*
		Reflections reflections = new Reflections("BaseClasses");
		 Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
		 */
		Chooser c =new Chooser();
		ReadFile rr=new ReadFile();
		RoadToResources myRoad=null;
		Class<?> myclass = null;
		Hero myHero=null;
		String outputFile="";
		int counter=1;
		ArrayList<Object> classNames=new ArrayList<>();
		ArrayList<String> myList=rr.read(c.pickFile());
		for (String line : myList) 
		{
			String className="";
			if (line.contains(CommonStrings.RESOURCES)) 
			{
				myRoad=new RoadToResources();
				myRoad.setRoad(Integer.parseInt(line.split(" ")[2]));
			}
			if (line.contains(CommonStrings.HAS)&&line.split(" ")[0].equals(CommonStrings.HERO))
			{
				int attackPower=0;
				int healthpoint=Integer.parseInt(line.split(" ")[2]);
				className=line.split(" ")[0];
				for (String item : myList) {
					if (item.contains(CommonStrings.ATTACK)&&item.split(" ")[0].equals(CommonStrings.HERO)) {
						attackPower=Integer.parseInt(item.split(" ")[3]);
						break;
					}
				}
				myHero=new Hero(healthpoint,"Hero",attackPower,0);
			}

			if (line.contains(CommonStrings.HAS)&&!line.split(" ")[0].equals(CommonStrings.HERO))
			{
				int attackPower=0;
				int enemyPos=0;
				int enemyPos2=0;
				className=line.split(" ")[0];
				int healthpoint=Integer.parseInt(line.split(" ")[2]);
				myclass=Class.forName("Battlefield."+className);
				Object enemy = myclass.newInstance();
				for (String string : myList) {
					if (string.contains(CommonStrings.ATTACK)&&string.split(" ")[0].equalsIgnoreCase(className)) {
						attackPower=Integer.parseInt(string.split(" ")[3]);
					}
					if (string.contains(CommonStrings.POSITION)&&string.split(" ")[3].equals(className)) {
						if(counter<2)
						{
							counter++;
							enemyPos=Integer.parseInt(string.split(" ")[6]);
							Method setAttackMethod=enemy.getClass().getSuperclass().getMethod("setAttackPower", Integer.TYPE); 
							Method setPositionMethod=enemy.getClass().getSuperclass().getMethod("setPosition", Integer.TYPE);
							Method setHealthpointMethod=enemy.getClass().getSuperclass().getMethod("setHealthpoint", Integer.TYPE);
							Method setNameMethod=enemy.getClass().getSuperclass().getMethod("setName", String.class);
							setAttackMethod.invoke(enemy,attackPower);
							setPositionMethod.invoke(enemy,enemyPos);
							setHealthpointMethod.invoke(enemy,healthpoint);
							setNameMethod.invoke(enemy,className);
							classNames.add(enemy);
						}
						else
						{
							//TODO:METHOD INVOKE
							Object enemynew = myclass.newInstance();
							enemyPos2=Integer.parseInt(string.split(" ")[6]);
							Method setAttackMethod=enemynew.getClass().getSuperclass().getMethod("setAttackPower", Integer.TYPE); 
							Method setPositionMethod=enemynew.getClass().getSuperclass().getMethod("setPosition", Integer.TYPE);
							Method setHealthpointMethod=enemynew.getClass().getSuperclass().getMethod("setHealthpoint", Integer.TYPE);
							Method setNameMethod=enemynew.getClass().getSuperclass().getMethod("setName", String.class);
							setAttackMethod.invoke(enemynew,attackPower);
							setPositionMethod.invoke(enemynew,enemyPos2);
							setHealthpointMethod.invoke(enemynew,healthpoint);
							setNameMethod.invoke(enemy,className);
							classNames.add(enemynew);
						}
					}
				}
			}
			counter=1;

		}
		//  sort by enemies position 
		Collections.sort(classNames,new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {

				try {
					return (int)(o1.getClass().getSuperclass().getMethod("getPosition").invoke(o1)) - (int)(o2.getClass().getSuperclass().getMethod("getPosition").invoke(o2));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});
		Creature currentEnemy=null;
		//classNames.get(0).getClass()
		//EnemyNode<Bug> s=new EnemyNode<Bug>((Bug)classNames.get(0));
		Method m=null;
		boolean endOfTheRoad=false;
		outputFile+=myHero.toString()+"\n";
		while ( myHero.getHealthpoint()>0) {
			
			while(!myHero.isRisky(classNames)&&!endOfTheRoad){
				if(myRoad.isRoadFinished(myHero.getPosition())){
					endOfTheRoad=true;
					break;
				}
				myHero.walk();	
				
			}
			if(endOfTheRoad){
				break;
			}
			m= classNames.get(myHero.getEnemyIndex()).getClass().getSuperclass().getMethod("getHealthpoint");
			int enemyHealt=(int)m.invoke(classNames.get(myHero.getEnemyIndex()));
			if (myHero.getHealthpoint()>enemyHealt) {
				// TODO:sout kaldýr
				myHero.attack((Creature)classNames.get(myHero.getEnemyIndex()));
				outputFile+=myHero.getName()+" defeated "+classNames.get(myHero.getEnemyIndex()).getClass().getCanonicalName().toString()+" with "+myHero.getHealthpoint()+" HP remaining\n";
				//kill enemy
				//System.out.println(outputFile);
				classNames.remove(myHero.getEnemyIndex());
				myHero.walk();
			}
			else{
				currentEnemy=(Creature)classNames.get(myHero.getEnemyIndex());
				int fightResult=(currentEnemy.attack()-myHero.calculateHp())/myHero.getAttackPower();
				outputFile+=currentEnemy.getName()+" defeated "+myHero.getName()+" with "+currentEnemy.getHealthpoint()+" HP remaining\n";
				myHero.setHealthpoint(0);
						 
			}		
		}
		if (!(myHero.getHealthpoint()>0)) {
			outputFile +="Hero is Dead! Last seen at position "+myHero.getPosition()+"!!";
		}
		else{
			outputFile +="Hero Survived!!";
		}
		System.out.println(outputFile);
		String path=c.outputDir();
		WriteOutput exportFile=new WriteOutput();
		exportFile.writing(path, outputFile);
		
/*
		Bug bug=new Bug(50,"buggie",2,123);
		System.out.println(bug.getAttackPower());

		EnemyNode<Bug> bb=new EnemyNode<Bug>(bug);
		EnemiesOnTheRoad<Bug> ll=new EnemiesOnTheRoad<>();
		ll.addEnemy(bb);

		System.out.println(ll.toString());
*/
	}


}
