package ge.mgl.utils;

/**
 * Created by Mikheil on 11/12/17.
 */
public class NumeratorUtil {

    private static final String FLIGHTS_PREFIX = "GDH";
    private static final String USER_PREFIX = "GDX";
    private static final String JUR_USER_PREFIX = "JDX";


    public static String generateFlightString(Long last){
        last++;
        return FLIGHTS_PREFIX + String.format("%04d", last);
    }

    public static String generateUserString(Long last){
        last++;
        return USER_PREFIX + String.format("%04d", last);
    }

    public static String generateJurUserString(Long last){
        last++;
        return JUR_USER_PREFIX + String.format("%04d", last);
    }

    public static void main(String[] args) {
        System.out.println(generateJurUserString(1L));
    }
}



