package net.farugames.buildbattle;

public enum GameStatus {
	LOBBY(true), GAME(false), VOTE(false), FINISH(false);

	private boolean canJoin;
	private static GameStatus currentStatus;

	private GameStatus(boolean canJoin) {
		this.canJoin = canJoin;
	}

	public boolean canJoin() {
		return this.canJoin;
	}

	public static void setStatus(GameStatus status) {
		currentStatus = status;
	}

	public static boolean isStatus(GameStatus status) {
		return currentStatus == status;
	}

	public static GameStatus getStatus() {
		return currentStatus;
	}
}
