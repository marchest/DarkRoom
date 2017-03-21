package BaseClasses;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface IHero {
 public void walk();
 public int calculateHp();
 public boolean isRisky(ArrayList<Object> checkForEnemies) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
 
}
