package pacman;

import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JComponent;

public class Map {

  public enum Type {
    EMPTY,
    PACMAN,
    GHOST,
    WALL,
    COOKIE
  }

  private HashMap<Location, HashSet<Type>> field;
  private boolean gameOver;
  private int dim;

  private HashMap<String, Location> locations;
  private HashMap<String, JComponent> components;
  private HashSet<Type> emptySet;
  private HashSet<Type> wallSet;

  private int cookies = 0;

  public Map(int dim) {
    gameOver = false;
    locations = new HashMap<String, Location>();
    components = new HashMap<String, JComponent>();
    field = new HashMap<Location, HashSet<Type>>();

    emptySet = new HashSet<Type>();
    wallSet = new HashSet<Type>();
    emptySet.add(Type.EMPTY);
    wallSet.add(Type.WALL);
    this.dim = dim;
  }

  public void add(String name, Location loc, JComponent comp, Type type) {
    locations.put(name, loc);
    components.put(name, comp);
    if (!field.containsKey(loc))
      field.put(loc, new HashSet<Type>());
    field.get(loc).add(type);
  }

  public int getCookies() {
    return cookies;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public boolean move(String name, Location loc, Type type) {
    // Update field locations and jcomponent
    if (name.equals("pacman")){
      HashSet<Type>  old = new HashSet<>();
      old.add(Type.EMPTY);
      Location old_loc = locations.get(name);
      HashSet<Type>  new_set = new HashSet<>();
      new_set.add(Type.PACMAN);
      locations.put(name, loc);
      field.put(old_loc, old);
      field.put(loc, new_set);
      components.get(name).setLocation(loc.x, loc.y);
      return true;
    }else if ((name.equals("Inky") || name.equals("Blinky") || name.equals("Pinky") || name.equals("Clyde"))){
      HashSet<Type>  old = new HashSet<>();
      old.add(Type.EMPTY);
      Location old_loc = locations.get(name);
      HashSet<Type>  new_set = new HashSet<>();
      new_set.add(Type.GHOST);
      locations.put(name, loc);
      field.put(old_loc, old);
      field.put(loc, new_set);
      components.get(name).setLocation(loc.x, loc.y);
      return true;
    }
    return false;
  }

  public HashSet<Type> getLoc(Location loc) {
    // boundary check
    if (loc.x > 0 || loc.x <= dim || loc.y < 0 || loc.y <= dim)
      return emptySet;
    if (!field.containsKey(loc) || field.get(loc).size() == 0)
      return wallSet;

    return field.get(loc);
  }

  public boolean attack(String Name) {
    gameOver = false;
    return false;
  }

  public JComponent eatCookie(String name) {
    return null;
  }
}
