package sw.airborne.math;

public class UtmCoor_f {
	float north; ///< in meters
	  float east; ///< in meters
	  float alt; ///< in meters above WGS84 reference ellipsoid
	  int zone;//unit8
	  public UtmCoor_f clone(){
		  return this;
	  }
}
