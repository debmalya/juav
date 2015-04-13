package sw.airborne.subsystems.ins;
import sw.airborne.math.*;
public class InsInt {
	public static LtpDef_i  ltp_def;
	public static boolean           ltp_initialized;

	  /** request to realign horizontal filter.
	   * Sets to current position (local origin unchanged).
	   */
	  public static boolean hf_realign;

	  /** request to reset vertical filter.
	   * Sets the z-position to zero and resets the the z-reference to current altitude.
	   */
	  public static boolean vf_reset;

	  /* output LTP NED */
	  public static NedCoor_i ltp_pos;
	   public static NedCoor_i ltp_speed;
	   public static NedCoor_i ltp_accel;

	  /* baro */
	   public static float baro_z;  ///< z-position calculated from baro in meters (z-down)
	  public static float qfe;
	  public static boolean baro_initialized;

	//#if USE_SONAR
	  public static boolean update_on_agl; ///< use sonar to update agl if available
	//#endif


}
