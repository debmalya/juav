package sw.airborne.math;

public class UtmCoor_f {
	float north; ///< in meters
	  float east; ///< in meters
	  float alt; ///< in meters above WGS84 reference ellipsoid
	  int zone;//unit8
	  public UtmCoor_f clone(){
		  UtmCoor_f temp= new UtmCoor_f();
		  temp.north = this.north;
		  temp.east = this.east;
		  temp.alt = this.alt;
		  temp.zone = this.zone;
		  return temp;
	  }
}
