package ge.mgl.utils;

import ge.mgl.entities.GUser;
import ge.mgl.security.SpringSecurityUser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class GeneralUtil {

    public static String generateString(int length) {
        if (length < 5) length = 5;
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = chars.charAt(rng.nextInt(chars.length()));
        }
        return String.valueOf(text);
    }

    public static boolean isCellEmpty(final HSSFCell cell) {
        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return true;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().isEmpty()) {
            return true;
        }

        return false;
    }

    public static String encodeMD5(String text) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(text.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {

        }
        return null;
    }

    public static Date str2Date(String str) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static int getDaysDifference(long t1, long t2) {
        if (t1 == t2) {
            return 0;
        }
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }

    public static void reloadUserData(GUser user){
        if(user != null){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            grantedAuthorities.addAll(user.getRole().getPermissions().stream().map(
                    permission -> new SimpleGrantedAuthority(permission.getName())
            ).collect(Collectors.toList()));

            // Load GUser Role
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

            SpringSecurityUser securityUser = new SpringSecurityUser(user, grantedAuthorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser, securityUser.getPassword(), grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }


    public static String mapToJsonString(Map<Integer, Double> hashMap)
    {
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<?, ?> entry : hashMap.entrySet()) {
            try {
                JSONObject json_obj = new JSONObject();
                Double val = (Double) entry.getValue();
                json_obj.put(String.valueOf(entry.getKey()), val);
                jsonArray.put(json_obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public static int localeBetween(LocalDate dateFrom, LocalDate dateTo){
        if ((dateFrom != null) && dateTo != null) {
            return Period.between(dateFrom, dateTo).getDays();
        } else {
            return 0;
        }
    }

    public static RequestResponse RequestSuccess(){
        return new RequestResponse(true);
    }

    public static RequestResponse RequestSuccess(String message){
        return new RequestResponse(true, message);
    }

    public static RequestResponse RequestSuccess(String message, Integer code){
        return new RequestResponse(true, message, code);
    }

    public static RequestResponse RequestError(){
        return new RequestResponse(false);
    }

    public static RequestResponse RequestError(String message){
        return new RequestResponse(false, message);
    }

    public static RequestResponse RequestError(String message, Integer code){
        return new RequestResponse(false, message, code);
    }
}
