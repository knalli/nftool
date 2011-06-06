package fhkoeln.edb.nftool;

/**
 * The state, which has to be solved/answered for a Task. If you have a state NORMALFORM1, then its
 * Tables are in Normalform 0 and you have to answer the Normalform 1.
 * 
 * @author Sebastian Janzen <sebastian@janzen.it>
 * 
 */
public enum ExerciseState {

	INTRO, FDEPENDENCIES, KEYS, NORMALFORM1, NORMALFORM2, NORMALFORM3, SOLVED;

	public static ExerciseState next(ExerciseState current) {
		switch (current) {
		case INTRO:
			return FDEPENDENCIES;
		case FDEPENDENCIES:
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
			throw new IllegalStateException(
					"Method next() in enum ExerciseState is not prepared for this State: "
							+ current);
		}
	}

	public static ExerciseState previous(ExerciseState current) {
		switch (current) {
		case INTRO:
			return INTRO;
		case FDEPENDENCIES:
			return INTRO;
		case KEYS:
			return FDEPENDENCIES;
		case NORMALFORM1:
			return KEYS;
		case NORMALFORM2:
			return NORMALFORM1;
		case NORMALFORM3:
			return NORMALFORM2;
		case SOLVED:
			return NORMALFORM3;
		default:
			throw new IllegalStateException(
					"Method previous() in enum ExerciseState is not prepared for this State: "
							+ current);
		}
	}
}
