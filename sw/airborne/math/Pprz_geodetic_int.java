package sw.airborne.math;

public class Pprz_geodetic_int {
	public static void ecef_of_ned_pos_i( EcefCoor_i ecef, LtpDef_i def, NedCoor_i ned) {
		  EnuCoor_i enu;
		  //ENU_OF_TO_NED(enu, ned);
		  (enu).x =  (ned).y;				
		    (enu).y =  (ned).x;				
		    (enu).z = -(ned).z;	
		  ecef_of_enu_pos_i(ecef, def, enu);
		}
	public static void enu_of_lla_point_i (EnuCoor_i enu,LtpDef_i def,LlaCoor_i lla) {
		   EcefCoor_i ecef;
		  ecef_of_lla_i(ecef,lla);
		  enu_of_ecef_point_i(enu,def,&ecef);
		}

	public static void ned_of_lla_point_i(struct NedCoor_i* ned, struct LtpDef_i* def, struct LlaCoor_i* lla) {
		  struct EcefCoor_i ecef;
		  ecef_of_lla_i(&ecef,lla);
		  ned_of_ecef_point_i(ned,def,&ecef);
		}

		void enu_of_lla_vect_i(struct EnuCoor_i* enu, struct LtpDef_i* def, struct LlaCoor_i* lla) {
		  struct EcefCoor_i ecef;
		  ecef_of_lla_i(&ecef,lla);
		  enu_of_ecef_vect_i(enu,def,&ecef);
		}

		void ned_of_lla_vect_i(struct NedCoor_i* ned, struct LtpDef_i* def, struct LlaCoor_i* lla) {
		  struct EcefCoor_i ecef;
		  ecef_of_lla_i(&ecef,lla);
		  ned_of_ecef_vect_i(ned,def,&ecef);
		}
		void lla_of_ecef_i(struct LlaCoor_i* out, struct EcefCoor_i* in) {

			  /* convert our input to floating point */
			  struct EcefCoor_d in_d;
			  in_d.x = M_OF_CM((double)in->x);
			  in_d.y = M_OF_CM((double)in->y);
			  in_d.z = M_OF_CM((double)in->z);
			  /* calls the floating point transformation */
			  struct LlaCoor_d out_d;
			  lla_of_ecef_d(&out_d, &in_d);
			  /* convert the output to fixed point       */
			  out->lon = (int32_t)rint(EM7RAD_OF_RAD(out_d.lon));
			  out->lat = (int32_t)rint(EM7RAD_OF_RAD(out_d.lat));
			  out->alt = (int32_t)MM_OF_M(out_d.alt);

			}

			void ecef_of_lla_i(struct EcefCoor_i* out, struct LlaCoor_i* in) {

			  /* convert our input to floating point */
			  struct LlaCoor_d in_d;
			  in_d.lon = RAD_OF_EM7RAD((double)in->lon);
			  in_d.lat = RAD_OF_EM7RAD((double)in->lat);
			  in_d.alt = M_OF_MM((double)in->alt);
			  /* calls the floating point transformation */
			  struct EcefCoor_d out_d;
			  ecef_of_lla_d(&out_d, &in_d);
			  /* convert the output to fixed point       */
			  out->x = (int32_t)CM_OF_M(out_d.x);
			  out->y = (int32_t)CM_OF_M(out_d.y);
			  out->z = (int32_t)CM_OF_M(out_d.z);

			}


}
