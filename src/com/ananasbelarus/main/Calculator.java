package com.ananasbelarus.main;
import com.ananasbelarus.main.Operations.enumOperations;
import static com.ananasbelarus.main.Operations.isOperator;

public  class Calculator {
    public enum enumState {
        Start,
        A1,
        A2,
        A3,
        A4,
        A5,
        B1,
        B2,
        C1,
        C2,
        D1,
        D2,
        D3,
        G,
        H,
        Error,
        Final
    }
    public static class Errors {
        enum enumErrors {
            ErrorDec,
            ErrorFormat,
            ErrorRoman,
            NoError
        }
        public class ErrorFirstDec extends Exception
        {
            public ErrorFirstDec(String errMassage)
            {
                super(errMassage);
            }
        }
        public class ErrorRoman extends Exception
        {
            public ErrorRoman(String errMassage)
            {
                super(errMassage);
            }
        }
        public class ErrorFormat extends Exception
        {
            public ErrorFormat(String errMassage)
            {
                super(errMassage);
            }
        }


        public void Allarm(enumErrors err, int pos) throws ErrorFirstDec  {
            switch (err)
            {
                case ErrorDec:
                    throw new ErrorFirstDec("Неверный формат десятичного оператора. Позиция ошибки: "+pos);
                case ErrorFormat:
                    throw new ErrorFirstDec("Неверный формат строки. Позиция ошибки: "+pos);
                case ErrorRoman:
                    throw new ErrorFirstDec("Неверный формат римского числа. Позиция ошибки: "+pos);

            }
        }
    }
    private static String doOperation(int a, int b, enumOperations operation,boolean roman) throws RomanNumb.WrongRomanNumb
    {
        RomanNumb rn = new RomanNumb();
        switch (operation)
        {
            case Multiplication: return roman?rn.IntToRoman(a*b):Integer.toString(a*b);
            case Addition: return roman?rn.IntToRoman(a+b):Integer.toString(a+b);
            case Division: return roman?rn.IntToRoman(a/b):Integer.toString(a/b);
            case Subtraction: return roman?rn.IntToRoman(a-b):Integer.toString(a-b);
            default: {/*Error*/
                return null;
            }
        }
    }
    public static String doSimpleAction(String s) throws Errors.ErrorFirstDec
    {
        Errors.enumErrors error = Errors.enumErrors.NoError;
        int a = 0,b = 0;
        String roman="";
        boolean isRoman = false;
        RomanNumb rn= new RomanNumb();
        int res = -1,point = 0;
        enumOperations operation = enumOperations.Empty;
        enumState State = enumState.Start;
        char[] str = s.toCharArray();
        for (char c: str) {
            switch (State) {
                case Start:
                    //System.out.println("Start");
                    if ( (int)c >=50 &&
                            (int)(c) <= 57) {
                        State = enumState.A1;
                        a = Integer.parseInt(String.valueOf(c));
                        //System.out.println("a =  " + a+" ("+(int)c+")\n Start \'D\'");
                    } else if (c == '1') {
                        State = enumState.B1;
                        //System.out.println("Start \'1\'");
                    } else if (RomanNumb.isRoman(c)) {
                        State = enumState.D1;
                        roman = Character.toString(c);
                        isRoman = true;
                        //System.out.println("Start \'R\'");

                    } else if (c == ' ') {
                        State = enumState.Start;
                        //System.out.println("Start \' \'");

                    } else {
                        State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;
                    }
                    break;
                case A1:
                    if (c == ' ') {
                        State = enumState.A2;
                        //System.out.println("A1 \' \'");
                    } else if (isOperator(c)) {
                        State = enumState.A3;
                        operation = Operations.DetermineOperation(c);
                        //System.out.println("A1 \'+\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorDec;}
                    break;
                case A2:
                    if (c == ' ') {/*State = enumState.A2;*/
                        System.out.println("A2 \' \'");}
                    else if (isOperator(c)) {
                        State = enumState.A3;
                        operation = Operations.DetermineOperation(c);
                        //System.out.println("A2 \'+\'");
                    }
                    else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;}
                    break;
                case B1:
                    if (c == '0') {
                        State = enumState.B2;
                        a = 10;
                        //System.out.println("B1 \'0\'");
                    } else if (c == ' ') {
                        a = 1;
                        State = enumState.A2;
                        //System.out.println("B1 \' \'");
                    } else if (isOperator(c)) {
                        State = enumState.A3;
                        operation = Operations.DetermineOperation(c);
                        a=1;
                        //System.out.println("B1 \'+\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorDec;}
                    break;
                case B2:
                    if (c == ' ') {
                        State = enumState.A2;
                        //System.out.println("B2 \' \'");
                    } else if (isOperator(c)) {
                        State = enumState.A3;
                        operation = Operations.DetermineOperation(c);
                        //System.out.println("B2 \'+\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorDec;}
                    break;
                case A3:
                    if ((int)c >=50 && (int)(c) <= 57) {
                        State = enumState.A4;
                        b = Integer.parseInt(String.valueOf(c));
                        //System.out.println("b =  " + b+" A3 \'D\'");
                    } else if (c == '1') {
                        State = enumState.C1;
                        //System.out.println("A3 \'1\'");
                    } else if (c == ' ') {
                        State = enumState.A3;
                        //System.out.println("A3 \' \'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;}
                    break;
                case A4:
                    if (c == ' ') {
                        State = enumState.A5;
                        //System.out.println("A4 \' \'");
                    } else if (c == '˧') {
                        State = enumState.Final;
                        //System.out.println("A4 \'F\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorDec;}
                    break;
                case A5:
                    if (c == ' ') {System.out.println("A5 \' \'");/*State = enumState.A5;*/}
                    else if (c == '˧') {
                        State = enumState.Final;
                        //System.out.println("A5 \'F\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;}
                    break;
                case C1:
                    if (c == '0') {
                        State = enumState.C2;
                        b = 10;
                        //System.out.println("C1 \'0\'");
                    } else if (c == ' ') {
                        b = 1;
                        State = enumState.A5;
                        //System.out.println("C1 \' \'");
                    } else if (c == '˧') {
                        b=1;
                        State = enumState.Final;
                        //System.out.println("C1 \'F\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorDec;}
                    break;
                case C2:
                    if (c == ' ') {
                        State = enumState.A2;
                        //System.out.println("C2 \' \'");
                    } else if (c == '˧') {
                        State = enumState.Final;
                        //System.out.println("C2 \'F\'");
                    } else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorDec;}
                    break;
                case D1:
                    //System.out.println("D1");
                    if (RomanNumb.isRoman(c)) {
                        roman = roman + Character.toString(c);
                    }else if(c==' ')
                    {
                        try {
                            a = rn.convertToInt(roman);
                            State = enumState.G;}
                        catch (RomanNumb.WrongRomanNumb e){State = enumState.Error;
                            error = Errors.enumErrors.ErrorRoman;
                        }
                    }
                    else if (isOperator(c))
                    {
                        try {
                        a = rn.convertToInt(roman);
                        State = enumState.D2;
                        operation = Operations.DetermineOperation(c);
                        }
                        catch (RomanNumb.WrongRomanNumb e){State = enumState.Error;
                        error = Errors.enumErrors.ErrorRoman;
                        }
                    }
                    else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorRoman;}
                    break;
                case G:
                    //System.out.println("G");
                    if(c==' ')
                    {
                        State = enumState.G;}
                    else if (isOperator(c))
                    {
                        operation = Operations.DetermineOperation(c);
                        State = enumState.D2;
                    }
                    else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;}
                    break;
                case D2:
                    //System.out.println("D2 + "+c);
                    if(c==' ')
                    {

                    }else if(RomanNumb.isRoman(c))
                    {
                        roman = Character.toString(c);
                        State = enumState.D3;
                    }
                    else {State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;}
                    break;
                case D3:
                    //System.out.println("D3 + "+c);
                    if (RomanNumb.isRoman(c)) {
                        roman = roman + c;
                    }else if(c==' ' )
                    {
                        try {
                            b = rn.convertToInt(roman);
                            State = enumState.H;
                        }
                        catch (RomanNumb.WrongRomanNumb e){State = enumState.Error;
                            error = Errors.enumErrors.ErrorRoman;
                        }
                    }
                    else if (c == '˧') {
                        try {
                        b = rn.convertToInt(roman);
                        State = enumState.Final;
                    }
                        catch (RomanNumb.WrongRomanNumb e){State = enumState.Error;
                            error = Errors.enumErrors.ErrorRoman;
                        }
                    }else { State = enumState.Error;
                        error = Errors.enumErrors.ErrorRoman;}
                    break;
                case H:
                    //System.out.println("H");
                    if(c==' ')
                    {
                        State = enumState.H;}
                    else if (c == '˧') {
                        State = enumState.Final;
                    }
                    else {
                        State = enumState.Error;
                        error = Errors.enumErrors.ErrorFormat;
                    }
                    break;
                case Final:
                    //System.out.println("Final a="+a+" b="+b);
                    try {
                    return doOperation(a, b, operation,isRoman);}
                    catch (RomanNumb.WrongRomanNumb e)
                    {
                        State = enumState.Error;
                        error = Errors.enumErrors.ErrorRoman;
                    }
                case Error:
                    Errors errors = new Errors();
                    errors.Allarm(error,point);
                    break;
                default:
                    return null;
            }
            //System.out.println(c);
            point++;
        }
        return null;
    }
}
