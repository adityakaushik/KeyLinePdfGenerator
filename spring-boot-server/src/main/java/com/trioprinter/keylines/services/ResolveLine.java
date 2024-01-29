package com.trioprinter.keylines.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.trioprinter.keylines.model.KeyParam;
import com.trioprinter.keylines.model.Lines;
import com.trioprinter.keylines.pojos.LineValues;
import com.trioprinter.keylines.repository.KeyParamRepository;
import com.trioprinter.keylines.repository.LinesRepository;
import com.trioprinter.keylines.utility.ResolveParamHelper;

@Service
@Component
public class ResolveLine {

	@Autowired
	ResolveParamValues resolveParamValues;

	@Autowired
	KeyParamRepository keyParamRepository;

	@Autowired
	LinesRepository linesRepository;

	@Autowired
	ResolveParamHelper resolveParamHelper;

	public ArrayList<LineValues> resolveParams(HashMap<String, Double> mParamValue,
			int templateId, double L, double W, double D, double CTBK,
			double TUCKIN, double PASTING) {
		try {

			ArrayList<LineValues> listLineValues = new ArrayList<>();

//			HashMap<String, Double> mParamValue = resolveParamValues
//					.resolveParams(mParamValue2, L, W, D, CTBK, TUCKIN, PASTING);

			//System.out.println("TemaplateId" + templateId);

			List<Lines> linesVales = linesRepository
					.findByShapeTemplateId((byte) templateId);
			if (linesVales == null || linesVales.size() == 0) {
				//System.out.print("Lines Empty");
			}
			for (Lines line : linesVales) {
				LineValues lv = new LineValues();
				// //System.out.println(line.getShapeTemplateId()+","+line.getShapeLineId());
				lv.setShapeTemplateId(line.getShapeTemplateId());
				lv.setShapeLineId(line.getShapeLineId());
				lv.setDesignName(line.getDesignName());

				lv.setEndX(resolveParamHelper.paramResolver(line.getEndX(),
						mParamValue));

				lv.setEndY(resolveParamHelper.paramResolver(line.getEndY(),
						mParamValue));

				lv.setStartX(resolveParamHelper.paramResolver(line.getStartX(),
						mParamValue));
				;
				lv.setStartY(resolveParamHelper.paramResolver(line.getStartY(),
						mParamValue));
				// mLineValues.put(line.get), (double) param.getValue());
				listLineValues.add(lv);
			}
			// return mLineValues;
			return listLineValues;
		} catch (PersistenceException e) {
			//System.out.println("Exception Occured: " + e);
			return null;
		} catch (Exception ex) {
			//System.out.println("Exception Occured: " + ex);
			return null;
		}
	}

}
