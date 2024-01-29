package com.trioprinter.keylines.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.trioprinter.keylines.model.KeyParam;
import com.trioprinter.keylines.utility.BODMAS;
import com.trioprinter.keylines.utility.ResolveParamHelper;

@Service
@Component
public class ResolveParamValues {

	@Autowired 
	ResolveParamHelper resolveParamHelper;
	
	public HashMap<String, Double> resolveParams(List<KeyParam> paramsList, double L, double W,
			double D, double CTBK, double TUCKIN, double PASTING) {

		HashMap<String, String> mParamFormula = new HashMap<>();
		HashMap<String, Double> mParamValue = new HashMap<>();
		for (KeyParam param : paramsList) {
			
			
			if (param.getParamName().equals("L")) {
				if (L != 0) {
					param.setValue(L);
				}
			}
			if (param.getParamName().equals("W")) {
				if (W != 0) {
					param.setValue(W);
				}
			}
			if (param.getParamName().equals("D")) {
				if (D != 0) {
					param.setValue(D);
				}
			}
			if (param.getParamName().equals("CTBK")) {
				if (CTBK != 0) {
					param.setValue(CTBK);
				}
			}
			if (param.getParamName().equals("TUCKIN")) {
				if (TUCKIN != 0) {
					param.setValue(TUCKIN);
				}
			}
			if (param.getParamName().equals("PASTING")) {
				if (PASTING != 0) {
					param.setValue(PASTING);
				}
			}
			mParamFormula.put(param.getParamName(), param.getFormula());
			mParamValue.put(param.getParamName(), (double) param.getValue());
		}
		mParamFormula.put("YHeight", " D + W + C ");
		mParamValue.put("YHeight", (double) 0);
		mParamFormula.put("XWidth", " 2*W - CTBK + 2* L  + C ");
		mParamValue.put("XWidth", (double) 0);
		
		for ( Entry<String, String> entry: mParamFormula.entrySet()) {
			String formula=entry.getValue();
			if (formula != null) {				
				double v=resolveParamHelper.paramResolver(formula,mParamValue);
				mParamValue.put(entry.getKey(), v);	
			}
		}
		return mParamValue;
	}
}
