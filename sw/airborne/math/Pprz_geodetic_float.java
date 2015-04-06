package sw.airborne.math;
import static sw.airborne.math.Pprz_geodetic.*;
import static sw.airborne.math.Pprz_algebra.*;

public class Pprz_geodetic_float {
	public static void ecef_of_ned_vect_f( EcefCoor_f ecef,  LtpDef_f def,  NedCoor_f ned) {
		   EnuCoor_f enu=new EnuCoor_f();
		  ENU_OF_TO_NED(enu, ned);
		  ecef_of_enu_vect_f(ecef, def, enu);
		}
	
	public static void ecef_of_enu_vect_f( EcefCoor_f ecef,  LtpDef_f def,  EnuCoor_f enu) {
		  /* convert used floats to double */
		   DoubleMat33 ltp_of_ecef_d = new DoubleMat33();
		  ltp_of_ecef_d.m[0] = (double) def.ltp_of_ecef.m[0];
		  ltp_of_ecef_d.m[1] = (double) def.ltp_of_ecef.m[1];
		  ltp_of_ecef_d.m[2] = (double) def.ltp_of_ecef.m[2];
		  ltp_of_ecef_d.m[3] = (double) def.ltp_of_ecef.m[3];
		  ltp_of_ecef_d.m[4] = (double) def.ltp_of_ecef.m[4];
		  ltp_of_ecef_d.m[5] = (double) def.ltp_of_ecef.m[5];
		  ltp_of_ecef_d.m[6] = (double) def.ltp_of_ecef.m[6];
		  ltp_of_ecef_d.m[7] = (double) def.ltp_of_ecef.m[7];
		  ltp_of_ecef_d.m[8] = (double) def.ltp_of_ecef.m[8];
		   EnuCoor_f enu_d = new EnuCoor_f();
		  enu_d.x = (float) enu.x;
		  enu_d.y = (float) enu.y;
		  enu_d.z = (float) enu.z;

		  /* compute in double */
		   EcefCoor_d ecef_d = new EcefCoor_d();
		  MAT33_VECT3_TRANSP_MUL(ecef_d, ltp_of_ecef_d, enu_d);

		  /* convert result back to float*/
		  ecef.x = (float) ecef_d.x;
		  ecef.y = (float) ecef_d.y;
		  ecef.z = (float) ecef_d.z;
		}
	
	public static void ned_of_ecef_vect_f( NedCoor_f ned,  LtpDef_f def,  EcefCoor_f ecef) {
		   EnuCoor_f enu = new EnuCoor_f();
		  enu_of_ecef_vect_f(enu, def, ecef);
		  ENU_OF_TO_NED(ned, enu);
		}
	
	public static void enu_of_ecef_vect_f( EnuCoor_f enu,  LtpDef_f def,  EcefCoor_f ecef) {
		  MAT33_VECT3_MUL(enu, def.ltp_of_ecef, ecef);
		}

}
