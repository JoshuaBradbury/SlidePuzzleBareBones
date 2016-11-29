package application;

public class Tile {

	private Location location, finalLocation, size;
	
	public Tile(Board board, Location location, Location size) {
		this.location = location;
		finalLocation = location.clone();
		this.size = size;
	}
	
	public Location getSize() {
		return size;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public boolean atFinalLocation() {
		return location.equals(finalLocation);
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
}
