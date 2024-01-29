package com.trioprinter.keylines.utility;
import java.util.Stack;

import org.springframework.stereotype.Component;

@Component
public class BODMAS {
//    public static void main(String args[]) 
//    {
//        System.out.println("******Expression Outputs******");
//        System.out.println(BODMAS.solveExpression("30 + 3 * (60/60)")); 
//        System.out.println(BODMAS.solveExpression("90 + 7 * 50/50")); 
//        System.out.println(BODMAS.solveExpression("100 + 3 * 50")); 
//        System.out.println(BODMAS.solveExpression("1000 * ( 1 + 70 ) "));
//        System.out.println(BODMAS.solveExpression("1000 / 0"));
//    }
    public static int solveExpression(String expression)
    {
        char[] tokens = expression.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for(int i=0; i < tokens.length; i++)
        {
            if(tokens[i] == ' ')
                continue;
            if (tokens[i] >= '0' && tokens[i] <= '9') 
            { 
                StringBuilder str = new StringBuilder();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                {
                    str.append(tokens[i++]);
                }
                values.push(Integer.parseInt(str.toString())); 
            }
            else if (tokens[i] == '(') 
                operators.push(tokens[i]);
            else if (tokens[i] == ')') { 
                while (operators.peek() != '(') 
                {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop())); 
                }
                operators.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' )
            {
                while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) 
                {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);    
            }
        }
        while (!operators.empty()) 
        {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop())); 
        }
        return values.pop();
    } 

    public static boolean hasPrecedence(char oper1, char oper2)
    { 
        if (oper2 == '(' || oper2 == ')') 
            return false; 
        if ((oper1 == '/' || oper1 == '*') && (oper2 == '+' || oper2 == '-')) 
            return false; 
        else
            return true;
    }
    
    public static int applyOperation(char oper, int var2, int var1) 
    { 
        switch (oper)
        { 
            case '+': 
                return var1 + var2; 
            case '-': 
                return var1 - var2; 
            case '*': 
                return var1 * var2; 
            case '/': 
                if (var2 == 0) 
                    throw new ArithmeticException("division by zero not possible!"); 
                return var1 / var2; 
        } 
        return 0;
    }
  }
    