package sw.airborne.math;

import sw.include.Std;
import static sw.airborne.math.Pprz_algebra_int.*;
import static sw.airborne.math.Pprz_algebra.*;
import static sw.include.Std.*;
import static sw.airborne.math.Pprz_geodetic.*;

public class Pprz_orientation_conversion {
	
	public static final int ORREP_QUAT_I = 0;
	
	public static boolean orientationCheckValid(OrientationReps orientation){
		return orientation.status == 1;
	}

	public static void orientationSetQuat_i( OrientationReps orientation, Int32Quat quat) {
		  QUAT_COPY(orientation.quat_i, quat);
		  /* clear bits for all attitude representations and only set the new one */
		  orientation.status = (1 << ORREP_QUAT_I);
		}
}
