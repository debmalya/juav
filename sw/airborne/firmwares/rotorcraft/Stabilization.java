package sw.airborne.firmwares.rotorcraft;

public class Stabilization {

	public static int stabilization_cmd[] = new int[COMMANDS_NB];

	public static void stabilization_init() {
		if(! STABILIZATION_SKIP_RATE){
			stabilization_none_init();
			stabilization_rate_init();
		}
		stabilization_attitude_init();
	}
}
