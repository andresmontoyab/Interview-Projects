package utils;

public class HitMapper {

    public static Integer getValueFromFrame(String frame) {
        if(frame.equals("")) {
            return 0;
        }

        if(frame.equals("X")) {
            return 10;
        }

        if(frame.equals("XX")) {
            return 20;
        }

        if (frame.contains("/")) {
            return 10;
        }

        if (frame.matches("[0-9]+")) {
            if (frame.length() > 1) {
                return Integer.valueOf(frame.substring(0, 1)) + Integer.valueOf(frame.substring(1, 2));
            } else {
                return Integer.valueOf(frame.substring(0,1));
            }
        }

        if(!frame.contains("-")) {
            return Integer.valueOf(frame.substring(0,1)) + Integer.valueOf(frame.substring(1,2));
        }

        return 0;
    }
}
