package com.ananasbelarus.main;

public class RomanNumb {
    public class WrongRomanNumb extends Exception
    {
        public WrongRomanNumb (String errorMess)
        {
            super(errorMess);
        }
    }
    private static String array[] = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    public static boolean isRoman(char ch)
    {
        switch (ch)
        {
            case 'I':return true;
            case 'V':return true;
            case 'X':return true;
            default:return false;
        }
    }
    public int convertToInt(String s) throws WrongRomanNumb {
        char[] str = s.toCharArray();
        int result=1;
        for (String rom : array) {
            if (rom.length() == s.length()) {
                boolean o = true;
                char[] roman = rom.toCharArray();
                for (int i = 0; i < s.length() && o; i++) {
                    if (str[i] != roman[i]) {
                        o = false;
                    }
                }
                if(o){return result;}
            }
            result++;
        }
        if(str.length==4){
            boolean o = true;
            char[] rom4 = "IIII".toCharArray();
            for (int i = 0; i < s.length() && o; i++) {
                if (str[i] != rom4[i]) {
                    o = false;
                }
            }
            if(o){return 4;}
        }
        if(str.length==5){
            boolean o = true;
            char[] rom4 = "VIIII".toCharArray();
            for (int i = 0; i < s.length() && o; i++) {
                if (str[i] != rom4[i]) {
                    o = false;
                }
            }
            if(o){return 9;}
        }
        throw new WrongRomanNumb("Неверный формат римской цифры");
    }
    public String IntToRoman(int dec) throws WrongRomanNumb
    {
        String res = "";
        /*if(dec<=0){throw new WrongRomanNumb("Не существует эквивалентного данному");}
        while (dec-100>=0){res+="С";dec-=100;}
        while (dec-50>=0){res+="L";dec-=50;}
        while (dec-10>=0){res+="X";dec-=10;}
        while (dec-5>=0){res+="V";dec-=5;}
        while (dec-1>=0){res+="I";dec--;}
         */
        while (dec-100>=0){res+="С";dec-=100;}
        if (dec>90){res+="XC"; res+=IntToRoman(dec-90);dec=0;}
        while (dec-50>=0){res+="L";dec-=50;}
        if (dec>40){res+="XL"; res+=IntToRoman(dec-40);dec=0;}
        while (dec-10>=0){res+="X";dec-=10;}
        if (dec==9){res+="IX";dec=0;}
        while (dec-5>=0){res+="V";dec-=5;}
        if (dec==9){res+="IV";dec=0;}
        while (dec-1>=0){res+="I";dec--;}
        return res;
    }
}
