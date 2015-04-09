package sw.airborne.math;
import static sw.airborne.math.Pprz_algebra_int.*;
public class Pprz_trig_int {
		public static int[] pprz_trig_int=new int[13];
	public static void PPRZ_ITRIG_SIN(int _s, int _a) {					
	    int an = _a;							
	    INT32_ANGLE_NORMALIZE(an);						
	    if (an > INT32_ANGLE_PI_2) an = INT32_ANGLE_PI - an;		
	    else if (an < -INT32_ANGLE_PI_2) an = -INT32_ANGLE_PI - an;		
	    if (an >= 0) _s = pprz_trig_int[an];				
	    else _s = -pprz_trig_int[-an];					
	  }
	public static void PPRZ_ITRIG_COS(int _c,int _a) {					
	    PPRZ_ITRIG_SIN( _c, _a + INT32_ANGLE_PI_2);				
	  }

}
