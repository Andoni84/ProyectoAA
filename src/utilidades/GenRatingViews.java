package utilidades;

public class GenRatingViews {
	
	public static int generadorViews(){
		return (int) (Math.random()*10000);
	}
	
	public static double generadorRating(){
		return (double) (((int)(Math.random()*500))/100d);
	}

}
