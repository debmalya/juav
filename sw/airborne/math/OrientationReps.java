package sw.airborne.math;

public class OrientationReps {
	 /**
	   * Holds the status bits for all orientation representations.
	   * When the corresponding bit is set, the representation
	   * is already computed.
	   */
	  public int status;

	  /**
	   * Orientation quaternion.
	   * Units: #INT32_QUAT_FRAC
	   */
	 public  Int32Quat quat_i = new Int32Quat();

	  /**
	   * Orientation in zyx euler angles.
	   * Units: rad in BFP with #INT32_ANGLE_FRAC
	   */
	  public Int32Eulers eulers_i = new Int32Eulers();

	  /**
	   * Orientation rotation matrix.
	   * Units: rad in BFP with #INT32_TRIG_FRAC
	   */
	  public Int32RMat rmat_i = new Int32RMat();

	  /**
	   * Orientation as quaternion.
	   * Units: unit length quaternion
	   */
	  public FloatQuat quat_f = new FloatQuat();

	  /**
	   * Orienation in zyx euler angles.
	   * Units: rad
	   */
	  public FloatEulers eulers_f = new FloatEulers();

	  /**
	   * Orientation rotation matrix.
	   * Units: rad
	   */
	  public FloatRMat   rmat_f = new FloatRMat();
}
