package com.trioprinter.keylines.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Component;

@Component
public class ResolveParamHelper {

	public double paramResolver(String formula, HashMap<String, Double> mParamValue) {
	
		 String formularesolved="";
		 formula=addspaces(formula);

		List<String> forParamList = Arrays.asList(formula.split(" "));
		
		for (int index = 0; index < forParamList.size(); index++) {
			
			// get the value of variable if its present in ParamValues Map
			if ((mParamValue.get(forParamList.get(index))!= null) ){
				double value = mParamValue.get(forParamList.get(index));

				// replace the param name with param value
				formularesolved= formularesolved +String.valueOf(value);

			}else{
				formularesolved=formularesolved+" "+forParamList.get(index)+" ";
			}
		}
		// replace the value of pi in formula
		if (formularesolved.contains("PI")) {
			formularesolved = formularesolved.replace("PI", String.valueOf(3.1416));

		}
		// resolve the formula
		
		Expression e = new Expression(formularesolved);
		double calculated=e.calculate();
		
		if(String.valueOf(calculated).equals("NaN")){
			System.out.println("cant be resolved formula  " + formularesolved);
		}
		return calculated;
	}

	private String addspaces(String formula){
		formula = formula.replace("(","( ");
		formula = formula.replace(")"," )");
		
		formula = formula.replace("-"," - ");
		formula = formula.replace("+"," + ");
		formula = formula.replace("*"," * ");
		formula = formula.replace("/"," / ");
		formula = formula.replace("%"," % ");
		formula= formula.replace("Temp","TEMP");
		formula= formula.replace("Tan","tan");
		formula= formula.replace("Sin","sin");
		formula= formula.replace("Cos","cos");
//		if (formula.contains("PI")){
//			formula=formula.replace("PI",String.valueOf(3.1416));
//		}
		return formula;
	}
	
	public double convertToPixel(double mm){
		return
		mm*(3.7795275591) ;
	}
}
