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
    Location oldLocation = locations.get(name);
    JComponent component = components.get(name);

    field.get(oldLocation).remove(type);

    locations.put(name, loc);
    component.setLocation(loc.y, loc.x);
    if (field.containsKey(loc))
      field.put(loc, new HashSet<Type>());
    field.get(loc).add(type);
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
