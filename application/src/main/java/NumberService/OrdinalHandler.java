package OrdinalNumbers;

public class OrdinalHandler {
    public static String ordinalToString(String number, String ended) {
        String res = number;
        if(number.endsWith("один ")) {
            int first = number.lastIndexOf("один ");
            int second = first + 5;
            if(ended.equals("й")) ended = "ый";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "перв" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("два ")) {
            int first = number.lastIndexOf("два ");
            int second = first + 4;
            if(ended.equals("й")) ended = "ой";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "втор" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("три ")) {
            int first = number.lastIndexOf("три ");
            int second = first + 4;
            if(ended.equals("й")) ended = "ий";
            if(ended.equals("го")) ended = "его";
            if(ended.equals("ий")) {
                res = number.substring(0, first) + "трет" + ended + " " + number.substring(second);
            }
            else res = number.substring(0, first) + "треть" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("четыре ")) {
            int first = number.lastIndexOf("четыре ");
            int second = first + 7;
            if(ended.equals("й")) ended = "ый";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "четверт" + ended + " " + number.substring(second);

        }
        else if(number.endsWith("пять ")) {
            int first = number.lastIndexOf("пять ");
            int second = first + 5;
            if(ended.equals("й")) ended = "ый";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "пят" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("шесть ")) {
            int first = number.lastIndexOf("шесть ");
            int second = first + 6;
            if(ended.equals("й")) ended = "ой";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "шест" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("семь ")) {
            int first = number.lastIndexOf("семь ");
            int second = first + 5;
            if(ended.equals("й")) ended = "ой";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "седьм" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("восемь ")) {
            int first = number.lastIndexOf("восемь ");
            int second = first + 7;
            if(ended.equals("й")) ended = "ой";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "восьм" + ended + " " + number.substring(second);
        }
        else if(number.endsWith("девять ")) {
            int first = number.lastIndexOf("девять ");
            int second = first + 7;
            if(ended.equals("й")) ended = "ый";
            if(ended.equals("го")) ended = "ого";
            res = number.substring(0, first) + "девят" + ended + " " + number.substring(second);
        }
        return res;
    }
}
