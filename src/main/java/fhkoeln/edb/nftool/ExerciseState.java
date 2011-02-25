package fhkoeln.edb.nftool;

public enum ExerciseState {

	INTRO, KEYS, NORMALFORM1, NORMALFORM2, NORMALFORM3, SOLVED;

	public static ExerciseState next(ExerciseState current) {
		switch (current) {
		case INTRO:
			return KEYS;
		case KEYS:
			return NORMALFORM1;
		case NORMALFORM1:
			return NORMALFORM2;
		case NORMALFORM2:
			return NORMALFORM3;
		case NORMALFORM3:
			return SOLVED;
		case SOLVED:
		default:
			return null;
		}
	}

	public static ExerciseState previous(ExerciseState current) {
		switch (current) {
		case KEYS:
			return INTRO;
		case NORMALFORM1:
			return KEYS;
		case NORMALFORM2:
			return NORMALFORM1;
		case NORMALFORM3:
			return NORMALFORM2;
		case SOLVED:
			return NORMALFORM3;
		default:
			return null;
		}
	}
}
