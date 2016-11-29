package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Board {

	private Tile[][] board;
	private int divisions, shuffles;
	private boolean shuffling, isTileMoving, finished;
	private Random random;
	private LinkedList<TileMove> tileMoves;
	private Location emptyLocation;

	public Board(int divisions) {
		random = new Random();
		tileMoves = new LinkedList<TileMove>();
		genBoard(divisions);

		shuffles = 0;
		shuffling = true;
		shuffle();
	}

	// TODO: Write the board generation
	public void genBoard(int divisions) {
		
	}

	/**
	 * Prepares the Board for the Tile movement
	 * @param tile the Tile to move
	 */
	public void moveTile(Tile tile) {
		if (finished) return;
		Location location = findEmptySlot(tile.getLocation());
		if (location != null) {
			emptyLocation = tile.getLocation().clone();
			if (!isTileMoving) {
				startAnimation(new TileMove(tile, location));
			} else {
				tileMoves.add(new TileMove(tile, location));
			}
		}
	}

	/**
	 * Handles the animation of the TileMove
	 * @param move the TileMove that describes what Tile will move where
	 */
	// TODO: Write the animation for the TileMove
	public void startAnimation(TileMove move) {
		
	}

	/**
	 * Updates the GUI to display the latest Board
	 */
	// TODO: update the GUI with the latest Board
	public void refreshBoard() {
		
	}

	/**
	 * Returns the empty Location around the specified Location
	 * If the specified Location isn't next to the empty Location it returns null
	 * @param location the Location of the Tile
	 * @return the Location of the empty slot
	 */
	public Location findEmptySlot(Location location) {
		if (Math.abs(location.getX() - emptyLocation.getX()) == 1 && location.getY() - emptyLocation.getY() == 0)
			return emptyLocation;
		if (Math.abs(location.getY() - emptyLocation.getY()) == 1 && location.getX() - emptyLocation.getX() == 0)
			return emptyLocation;
		return null;
	}

	/**
	 * Randomly moves one Tile on the Board
	 */
	private void shuffle() {
		shuffles++;
		
		Location empty = null;
		for (int x = 0; x < divisions; x++) {
			for (int y = 0; y < divisions; y++) {
				empty = findEmptySlot(new Location(x, y));
				if (empty != null)
					break;
			}
			if (empty != null)
				break;
		}

		if (empty != null) {
			ArrayList<Tile> tiles = new ArrayList<Tile>();
			if (empty.getX() != 0 && board[empty.getX() - 1][empty.getY()] != null)
				tiles.add(board[empty.getX() - 1][empty.getY()]);
			if (empty.getX() != divisions - 1 && board[empty.getX() + 1][empty.getY()] != null)
				tiles.add(board[empty.getX() + 1][empty.getY()]);
			if (empty.getY() != 0 && board[empty.getX()][empty.getY() - 1] != null)
				tiles.add(board[empty.getX()][empty.getY() - 1]);
			if (empty.getY() != divisions - 1 && board[empty.getX()][empty.getY() + 1] != null)
				tiles.add(board[empty.getX()][empty.getY() + 1]);
			moveTile(tiles.get(random.nextInt(tiles.size())));
		}
	}
}
