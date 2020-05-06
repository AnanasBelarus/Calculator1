package com.ananasbelarus.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import com.ananasbelarus.main.Calculator.Errors;

public class Main {
    public static void main(String[] args) throws Errors.ErrorFirstDec , Errors.ErrorFormat, Errors.ErrorRoman {
        System.out.println("Введите пример");
        Scanner scanner = new Scanner(System.in);
        while (true){String name = scanner.nextLine()+"˧ ";
        System.out.println(Calculator.doSimpleAction(name));}
    }
}
