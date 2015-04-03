package sw.airborne.subsystems.ahrs;

import sw.airborne.math.*;

public class AhrsAligner {
	Int32Rates lp_gyro;
	Int32Vect3 lp_accel;
	Int32Vect3 lp_mag;
	int noise;
	int low_noise_cnt;
	int status;
}
