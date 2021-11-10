package group1.model.level;

public enum LevelId {
	OBSERVER_LEVEL(1),LEVEL1(2);

	private int levelId;
	private LevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getLevelId() {
		return this.levelId;
	}
}
