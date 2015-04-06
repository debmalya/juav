package sw.airborne.math;

import static sw.airborne.math.Pprz_algebra.*;
import static sw.airborne.math.Pprz_algebra_int.*;
import static sw.airborne.math.Pprz_algebra_double.*;
import static sw.airborne.math.Pprz_geodetic.*;

public class Pprz_geodetic_int {
	
	public static final int HIGH_RES_TRIG_FRAC  = 20;
	
	public static double M_OF_CM(double cm){
		return cm/1e2;
	}

	public static double CM_OF_M(double cm){
		return cm*1e2;
	}
	
	public static double RAD_OF_EM7RAD(double r){
		return r/1e7;
	}
	
	public static double M_OF_MM(double mm){
		return mm/1e3;
	}
	
	public static void ecef_of_ned_pos_i( EcefCoor_i ecef, LtpDef_i def, NedCoor_i ned) {
		EnuCoor_i enu = new EnuCoor_i();
		//ENU_OF_TO_NED(enu, ned);
		enu.x =  ned.y;				
		enu.y =  ned.x;				
		enu.z = -ned.z;	
		ecef_of_enu_pos_i(ecef, def, enu);
	}
	public static void enu_of_lla_point_i (EnuCoor_i enu,LtpDef_i def,LlaCoor_i lla) {
		EcefCoor_i ecef = new EcefCoor_i();
		ecef_of_lla_i(ecef,lla);
		enu_of_ecef_point_i(enu,def,ecef);
	}

	public static void ned_of_lla_point_i( NedCoor_i ned,  LtpDef_i def,  LlaCoor_i lla) {
		 EcefCoor_i ecef = new EcefCoor_i();
		ecef_of_lla_i(ecef,lla);
		ned_of_ecef_point_i(ned,def,ecef);
	}

	public static void enu_of_lla_vect_i( EnuCoor_i enu,  LtpDef_i def,  LlaCoor_i lla) {
		 EcefCoor_i ecef = new EcefCoor_i();
		ecef_of_lla_i(ecef,lla);
		enu_of_ecef_vect_i(enu,def,ecef);
	}

	public static void ned_of_lla_vect_i( NedCoor_i ned,  LtpDef_i def,  LlaCoor_i lla) {
		 EcefCoor_i ecef = new EcefCoor_i();
		ecef_of_lla_i(ecef,lla);
		ned_of_ecef_vect_i(ned,def,ecef);
	}
	public static void lla_of_ecef_i( LlaCoor_i out,  EcefCoor_i in) {

		/* convert our input to floating point */
		 EcefCoor_d in_d = new EcefCoor_d();
		in_d.x = M_OF_CM((double)in.x);
		in_d.y = M_OF_CM((double)in.y);
		in_d.z = M_OF_CM((double)in.z);
		/* calls the floating point transformation */
		 LlaCoor_d out_d= new LlaCoor_d();
		lla_of_ecef_d(out_d, in_d);
		/* convert the output to fixed point       */
		out.lon =(int) (EM7RAD_OF_RAD(out_d.lon));
		out.lat =(int) (EM7RAD_OF_RAD(out_d.lat));
		out.alt = (int)MM_OF_M(out_d.alt);

	}

	public static void ecef_of_lla_i( EcefCoor_i out,  LlaCoor_i in) {

		/* convert our input to floating point */
		 LlaCoor_d in_d = new LlaCoor_d();
		in_d.lon = RAD_OF_EM7RAD((double)in.lon);
		in_d.lat = RAD_OF_EM7RAD((double)in.lat);
		in_d.alt = M_OF_MM((double)in.alt);
		/* calls the floating point transformation */
		 EcefCoor_d out_d = new EcefCoor_d();
		ecef_of_lla_d(out_d, in_d);
		/* convert the output to fixed point       */
		out.x = (int)CM_OF_M(out_d.x);
		out.y = (int)CM_OF_M(out_d.y);
		out.z = (int)CM_OF_M(out_d.z);

	}

	public static void ecef_of_enu_pos_i(EcefCoor_i ecef, LtpDef_i def, EnuCoor_i enu){
		EnuCoor_i enu_cm = new EnuCoor_i();
		VECT3_SMUL(enu_cm, enu, 25);
		INT32_VECT3_RSHIFT(enu_cm, enu_cm, INT32_POS_FRAC-2);
		ecef_of_enu_vect_i(ecef, def, enu_cm);
		INT32_VECT3_ADD(ecef, def.ecef);
	}

	public static void ecef_of_enu_vect_i( EcefCoor_i ecef,  LtpDef_i def,  EnuCoor_i enu) {

		int tmpx = (int)def.ltp_of_ecef.m[0] * enu.x +
				(int)def.ltp_of_ecef.m[3] * enu.y +
				(int)def.ltp_of_ecef.m[6] * enu.z;
		ecef.x = (int)(tmpx>>HIGH_RES_TRIG_FRAC);

		int tmpy = (int)def.ltp_of_ecef.m[1] * enu.x +
				(int)def.ltp_of_ecef.m[4] * enu.y +
				(int)def.ltp_of_ecef.m[7] * enu.z;
		ecef.y = (int)(tmpy>>HIGH_RES_TRIG_FRAC);

		/* first element is always zero http://en.wikipedia.org/wiki/Geodetic_system#From_ENU_to_ECEF */
		int tmpz = (int)def.ltp_of_ecef.m[5] * enu.y +
				(int)def.ltp_of_ecef.m[8] * enu.z;
		ecef.z = (int)(tmpz>>HIGH_RES_TRIG_FRAC);

	}
	
	public static double EM7RAD_OF_RAD(double r){
		return r*1e7;
	}
	
	public static void ned_of_ecef_vect_i(NedCoor_i ned, LtpDef_i def, EcefCoor_i ecef) {
		  EnuCoor_i enu = new EnuCoor_i();
		  enu_of_ecef_vect_i(enu, def, ecef);
		  ENU_OF_TO_NED(ned, enu);
		}

	
	public static double MM_OF_M(double m){
		return m*1e3;
	}
	
	public static void enu_of_ecef_vect_i(EnuCoor_i enu, LtpDef_i def,EcefCoor_i ecef) {

		  int tmpx = (int)def.ltp_of_ecef.m[0]*ecef.x +
		                       (int)def.ltp_of_ecef.m[1]*ecef.y +
		                       0; /* this element is always zero http://en.wikipedia.org/wiki/Geodetic_system#From_ECEF_to_ENU */
		  enu.x = (int)(tmpx>>HIGH_RES_TRIG_FRAC);
		   int tmpy = (int)def.ltp_of_ecef.m[3]*ecef.x +
		                       (int)def.ltp_of_ecef.m[4]*ecef.y +
		                       (int)def.ltp_of_ecef.m[5]*ecef.z;
		  enu.y = (int)(tmpy>>HIGH_RES_TRIG_FRAC);
		   int tmpz = (int)def.ltp_of_ecef.m[6]*ecef.x +
		                       (int)def.ltp_of_ecef.m[7]*ecef.y +
		                       (int)def.ltp_of_ecef.m[8]*ecef.z;
		  enu.z = (int)(tmpz>>HIGH_RES_TRIG_FRAC);

		}
	
	/** Convert a point from ECEF to local NED.
	 * @param[out] ned  NED point in cm
	 * @param[in]  def  local coordinate system definition
	 * @param[in]  ecef ECEF point in cm
	 */
	public static void ned_of_ecef_point_i( NedCoor_i ned,  LtpDef_i def,  EcefCoor_i ecef) {
	   EnuCoor_i enu = new EnuCoor_i();
	  enu_of_ecef_point_i(enu, def, ecef);
	  ENU_OF_TO_NED(ned, enu);
	}

	/** Convert a point from ECEF to local ENU.
	 * @param[out] enu  ENU point in cm
	 * @param[in]  def  local coordinate system definition
	 * @param[in]  ecef ECEF point in cm
	 */
	public static void enu_of_ecef_point_i( EnuCoor_i enu,  LtpDef_i def,  EcefCoor_i ecef) {

	   EcefCoor_i delta = new EcefCoor_i();
	  VECT3_DIFF(delta, ecef, def.ecef);
	   int tmpx = (int)def.ltp_of_ecef.m[0]*delta.x +
	                       (int)def.ltp_of_ecef.m[1]*delta.y +
	                       0; /* this element is always zero http://en.wikipedia.org/wiki/Geodetic_system#From_ECEF_to_ENU */
	  enu.x = (int)(tmpx>>HIGH_RES_TRIG_FRAC);
	   int tmpy = (int)def.ltp_of_ecef.m[3]*delta.x +
	                       (int)def.ltp_of_ecef.m[4]*delta.y +
	                       (int)def.ltp_of_ecef.m[5]*delta.z;
	  enu.y = (int)(tmpy>>HIGH_RES_TRIG_FRAC);
	   int tmpz = (int)def.ltp_of_ecef.m[6]*delta.x +
	                       (int)def.ltp_of_ecef.m[7]*delta.y +
	                       (int)def.ltp_of_ecef.m[8]*delta.z;
	  enu.z = (int)(tmpz>>HIGH_RES_TRIG_FRAC);

	}

}
