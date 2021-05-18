package NumberService;

/**
 * Class to process numbers
 */
public class NumberHandler {
    /**
     * Do primary numbers` processing
     * @param number number`s string
     * @return number written with letters
     */
    public static String numberToString(String number) {
        String res = "";
        if(number.startsWith("-")) {
            res += "минус ";
            number = String.valueOf(-Long.parseLong(number));
        }
        boolean flag = false;
        int count = 0;
        if (number.startsWith("0")) {
            for(int i = 0; i < number.length() - 1; i++) {
                if (number.charAt(i) == '0') {
                    res += "ноль ";
                    count++;
                }
                else {
                    flag = true;
                    break;
                }
            }
        }
        if(count > 0) {
            long chislo;
            if(count > 1) {
                chislo = (long) (Long.parseLong(number) % Math.pow(10, count - 1));
            }
            else chislo = (long) (Long.parseLong(number) / Math.pow(10, count - 1));
            return res + numberToSymbol(chislo, chislo);
        }
        else return res + numberToSymbol(Long.parseLong(number), Long.parseLong(number));
    }

    /**
     * Translate number to string format
     * @param number number to process
     * @param number_copy copy of number to process
     * @return
     */
    public static String numberToSymbol(long number, long number_copy) {
        String res = "";
        long copyNumber;


        if (number_copy == 0) {
            return "ноль";
        }
        while (number != 0) {


            if (number <= 10) {
                res += WorkWithNumbers.edinitsi[(int) ((number - 1) % 11)] + " ";
                number = 0;
            }
            if (number > 10 && number < 20) {
                // +
                res += WorkWithNumbers.nadsat[(int) (number % 11)] + "надцать ";
                number = 0;
            }
            if (number >= 20 && number < 40) {
                // +
                res += WorkWithNumbers.dsat[(int) ((number / 10) % 2)] + "дцать ";
                number = number % 10;
                continue;
            }
            // +
            if (number >= 40 && number < 50) {
                res += "сорок ";
                number %= 10;
                continue;
            }
            // +
            if (number >= 50 && number < 90) {
                res += WorkWithNumbers.desyat[(int) ((number / 10) % 5)] + "десят ";
                number %= 10;
                continue;
            }
            // +
            if (number >= 90 && number < 100) {
                res += "девяносто ";

                number %= 10;
                continue;

            }
            // 191...
            if (number >= 100 && number < 200) {
                res += "сто ";
                number %= 100;
                continue;
            }
            //+
            if (number >= 200 && number < 300) {
                res += "двести ";
                number %= 100;
                continue;
            }
            //+
            if (number >= 300 && number < 500) {
                res += WorkWithNumbers.sta[(int) (((number / 100) - 1) % 2)] + "ста ";
                number %= 100;
                continue;
            }
            //+
            if (number >= 500 && number < 1000) {
                res += WorkWithNumbers.sot[(int) ((number / 100) % 5)] + "сот ";
                number %= 100;
                continue;
            }

            if (number >= 1000 && number < 2000) {
                res += "тысяча ";
                number %= 1000;
                continue;
            }
            if (number >= 2000 && number < 5000) {
                res += WorkWithNumbers.tasyachi[(int) (((number - 2000) / 1000) % 3)] + " тысячи ";
                number %= 1000;
                continue;
            }

            if (number >= 5000 && number < 1e6) {

                return numberToSymbol(number / 1000, number_copy) + "тысяч " + numberToSymbol(number % 1000, number_copy);

            }
            if (number >= 1e6 && number < 2e6) {
                return "миллион " + numberToSymbol((long) (number % 1e6), number_copy);
            }
            if (number >= 2e6 && number < 1e9) {
                copyNumber = (long) (number / 1e6);
                return getString(res, copyNumber, number_copy, "миллион") + numberToSymbol((long) (number % 1e6), number_copy);

            }
            if(number >= 1e9) {
                return getStringByOneNumber("", number, number);
            }
          /* if (number >= 1e9 && number < 2e9) {
               return "миллиард " + numberToSymbol((long) (number % 1e7), number_copy);
           }
           if (number >= 2e9 && number < 1e12) {
               copyNumber = (long) (number / 1e9);
               return getString(res, copyNumber, number_copy, "миллиард") + numberToSymbol((long) (number % 1e9), number_copy);

           }
           if (number >= 1e12 && number < 2e12) {
               return "триллион " + numberToSymbol((long) (number % 1e12), number_copy);
           }
           if (number >= 2e12 && number < 1e15) {
               copyNumber = (long) (number / 1e12);
               return getString(res, copyNumber, number_copy, "триллион") + numberToSymbol((long) (number % 1e12), number_copy);
           }
           if (number >= 1e15 && number < 2e15) {

               return "квадриллион " + numberToSymbol((long) (number % 1e15), number_copy);

           }
           if (number >= 2e15 && number < 1e18) {
               copyNumber = (long) (number / 1e15);
               return getString(res, copyNumber, number_copy, "квадриллион") + numberToSymbol((long) (number % 1e15), number_copy);
           }*/
        }

        return res;
    }

    /**
     * Change number`s string to correct record according to grammar
     * @param res number`s string
     * @param number number to process
     * @param copy_number copy of number to process
     * @param ends postfix of number. For example, "миллион".
     * @return result of change
     */
    private static String getString(String res, long number, long copy_number, String ends) {
        String t = numberToSymbol(number,  copy_number).trim();
        if (t.endsWith("надцать") || t.endsWith("дцать") ||
                t.endsWith("десят") || t.endsWith("сто") ||
                t.endsWith("тысяч") || t.endsWith("тысячи") || t.endsWith("ть")) {
            res += t + " " + ends + "ов ";
        }
        else if(t.endsWith("один")) {
            res+= t + " " + ends;
        }
        else res+= t + " " + ends + "а ";
        return res;
    }

    /**
     * Make number to letter format by one number. For example, "123" - один два три
     * @param res number`s string
     * @param number number to process
     * @param copy_number copy of number
     * @return number in letter format by one number
     */
    private static String getStringByOneNumber(String res, long number, long copy_number) {
        while (number != 0) {
            res = copy_number == number ? "" : " "  + numberToSymbol(number % 10, number % 10)  + res;
            number /= 10;
        }
        res += numberToSymbol(copy_number % 10, copy_number % 10);
        return res;
    }
}