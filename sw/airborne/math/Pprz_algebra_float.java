package sw.airborne.math;

public class Pprz_algebra_float {
	
	public static void VECT3_COPY(FloatVect3 _a,FloatVect3 _b){
		(_a).x = (_b).x;				
	    (_a).y = (_b).y;				
	    (_a).z = (_b).z;
	}
	public static void VECT2_COPY(FloatVect2 _a,FloatVect2 _b){
		(_a).x = (_b).x;				
	    (_a).y = (_b).y;				
	    
	}
	
	public static void  FLOAT_VECT3_CROSS_PRODUCT(DoubleVect3 _vo, DoubleVect3 _v1,DoubleVect3 _v2) {          
	    (_vo).x = (_v1).y*(_v2).z - (_v1).z*(_v2).y;            
	    (_vo).y = (_v1).z*(_v2).x - (_v1).x*(_v2).z;            
	    (_vo).z = (_v1).x*(_v2).y - (_v1).y*(_v2).x;            
	  }
	public static float FLOAT_VECT2_NORM2(FloatVect2 _v){ 
		return ((_v).x*(_v).x + (_v).y*(_v).y);}

}
