package sw.airborne.firmwares.rotorcraft;
import static sw.airborne.firmwares.rotorcraft.stabilization.Stabilization_none.*;
import static sw.airborne.firmwares.rotorcraft.stabilization.Stabilization_rate.*;
import static sw.airborne.firmwares.rotorcraft.stabilization.Stabilization_attitude_common.*;


public class Stabilization {

	private static final int COMMANDS_NB = 10;
	private static final boolean STABILIZATION_SKIP_RATE = false;
	public static int stabilization_cmd[] = new int[COMMANDS_NB];

	public static void stabilization_init() {
		if(! STABILIZATION_SKIP_RATE){
			stabilization_none_init();
			stabilization_rate_init();
		}
		stabilization_attitude_init();
	}
}
