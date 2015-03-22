package sw.airborne.firmwares.rotorcraft.stabilization;

public class Stabilization_attitude_common {
	
	public static void stabilization_attitude_common_int_SetKiPhi(int _val) {	
		stabilization_gains.i.x = _val;             
		stabilization_att_sum_err.phi = 0;          
	}
}
