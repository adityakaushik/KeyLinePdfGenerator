package com.trioprinter.keylines.utility;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

@Component
public class BODMASModified {
//    public static void main(String args[]) 
//    {
//        System.out.println("******Expression Outputs******");
//        System.out.println(BODMAS.solveExpression("30 + 3 * (60/60)")); 
//        System.out.println(BODMAS.solveExpression("90 + 7 * 50/50")); 
//        System.out.println(BODMAS.solveExpression("100 + 3 * 50")); 
//        System.out.println(BODMAS.solveExpression("1000 * ( 1 + 70 ) "));
//        System.out.println(BODMAS.solveExpression("1000 / 0"));
//    }
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
    public static int solveExpression(String expression)
    {
    	List<String> tokens = Arrays.asList(expression.split(" "));
       // char[] tokens = expression.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<String> operators = new Stack<>();
        for(int i=0; i < tokens.size(); i++)
        {
//            if(tokens.get(i) == " ")
//                continue;
        	if(isNumeric(tokens.get(i))){
            if (Double.parseDouble(tokens.get(i)) >= 0 && Double.parseDouble(tokens.get(i)) <= 9) 
            { 
//                StringBuilder str = new StringBuilder();
//                while (i < tokens.length && tokens.get(i) >= "0" && tokens.get(i) <= "9")
//                {
//                    str.append(tokens[i++]);
//                }
                values.push(Integer.parseInt(tokens.get(i))); 
            }
        	}
            else if (tokens.get(i) == "(") 
                operators.push(tokens.get(i));
            else if (tokens.get(i) == ")") { 
                while (operators.peek() != "(") 
                {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop())); 
                }
                operators.pop();
            }
            else if (tokens.get(i) == "+" || tokens.get(i) == "-" || tokens.get(i) == "*" 
            		|| tokens.get(i) == "/" )
            {
                while (!operators.empty() && hasPrecedence(tokens.get(i), operators.peek())) 
                {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens.get(i));    
            }
            else if ( tokens.get(i)=="Sin" || tokens.get(i)=="Cos"  || tokens.get(i)=="Tan" ){
            	applyTrigno(tokens.get(i),values.pop());
            }
        }
        while (!operators.empty()) 
        {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop())); 
        }
        return values.pop();
    } 

    public static boolean hasPrecedence(String oper1, String oper2)
    { 
        if (oper2 == "(" || oper2 == ")") 
            return false; 
        if ((oper1 == "/" || oper1 == "*") && (oper2 == "+" || oper2 == "-")) 
            return false; 
        else
            return true;
    }
    
    public static int applyOperation(String oper, int var2, int var1) 
    { 
        switch (oper)
        { 
            case "+": 
                return var1 + var2; 
            case "-": 
                return var1 - var2; 
            case "*": 
                return var1 * var2; 
            case "/": 
                if (var2 == 0) 
                    throw new ArithmeticException("division by zero not possible!"); 
                return var1 / var2; 
           
            	
        } 
        return 0;
    }
    
    public static double applyTrigno(String oper,  int var1) 
    { 
        switch (oper)
        { 
            case "Sin": 
                return Math.sin(var1); 
            case "Cos": 
            	return Math.cos(var1); 
            case "tan": 
            	return Math.tan(var1); 	
        } 
        return 0;
    }
  }
    