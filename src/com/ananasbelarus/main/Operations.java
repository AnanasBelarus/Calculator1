package com.ananasbelarus.main;

public class Operations {
    public static boolean isOperator(char ch)
    {
        switch (ch)
        {
            case '/':return true;
            case '*':return true;
            case '-':return true;
            case '+':return true;
            default:return false;
        }
    }
    public enum enumOperations {
        Addition, Subtraction, Multiplication, Division, Empty
    }
    public static enumOperations DetermineOperation(char ch)
    {
        switch (ch)
        {
            case '/':return enumOperations.Division;
            case '*':return enumOperations.Multiplication;
            case '+':return enumOperations.Addition;
            case '-':return enumOperations.Subtraction;
            default:return null;
        }
    }
}
