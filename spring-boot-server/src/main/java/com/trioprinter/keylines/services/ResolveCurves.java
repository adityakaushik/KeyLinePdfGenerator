package com.trioprinter.keylines.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.trioprinter.keylines.model.Curves;
import com.trioprinter.keylines.model.KeyParam;
import com.trioprinter.keylines.pojos.CurveValues;
import com.trioprinter.keylines.repository.CurvesRepository;
import com.trioprinter.keylines.repository.KeyParamRepository;
import com.trioprinter.keylines.utility.ResolveParamHelper;

@Service
@Component
public class ResolveCurves {

	@Autowired
	ResolveParamValues resolveParamValues;

	@Autowired
	KeyParamRepository keyParamRepository;

	@Autowired
	CurvesRepository CurvesRepository;

	@Autowired
	ResolveParamHelper resolveParamHelper;

	public ArrayList<CurveValues> resolveParams(HashMap<String, Double> mParamValue,
			int templateId, double L, double W, double D, double CTBK,
			double TUCKIN, double PASTING) {
		try {

			ArrayList<CurveValues> listCurveValues = new ArrayList<>();

//			HashMap<String, Double> mParamValue = resolveParamValues
//					.resolveParams(mParamValue2, L, W, D, CTBK, TUCKIN, PASTING);

			//System.out.println("TemaplateId" + templateId);

			List<Curves> CurvesVales = CurvesRepository
					.findByShapeTemplateId((byte) templateId);
			if (CurvesVales == null || CurvesVales.size() == 0) {
				//System.out.print("Curves Empty");
			}
			for (Curves curve : CurvesVales) {
				CurveValues lv = new CurveValues();
				// //System.out.println(curve.getShapeTemplateId()+","+curve.getShapecurveId());
				lv.setShapeTemplateId(curve.getShapeTemplateId());
				lv.setShapeLineId(curve.getShapeCurveId());
				lv.setDesignName(curve.getDesignName());
				lv.setRadius1(Double.valueOf(mParamValue.get(curve.getRadius1())));
				lv.setRadius2(Double.valueOf(mParamValue.get(curve.getRadius2())));
				lv.setShapeLineId(curve.getShapeCurveId());
				lv.setDesignName(curve.getDesignName());
				String cx = curve.getCenterX();
				String cy = curve.getCenterY();
				String r = String.valueOf(mParamValue.get(curve.getRadius2()));
				double angle = Double.valueOf(curve.getEndAngle());
				String P = "A";
				if (curve.getPie() != null) {
					P = curve.getPie();
				}
				if (P.equals("DU")) {
					//System.out.println("DU");
					lv.setStartX(resolveParamHelper.paramResolver(cx,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy + "+" + r,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "+" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "+" + r, mParamValue));

					lv.setEndX(resolveParamHelper.paramResolver(cx + "+" + r,
							mParamValue));
					lv.setEndY(resolveParamHelper
							.paramResolver(cy, mParamValue));
				}
				if (P.equals("TF")) {

					lv.setStartX(resolveParamHelper.paramResolver(cx + "-" + r,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "-" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "+" + r, mParamValue));

					lv.setEndX(resolveParamHelper
							.paramResolver(cx, mParamValue));
					lv.setEndY(resolveParamHelper.paramResolver(cy + "+" + r,
							mParamValue));

				}
				if (P.equals("TD")) {
					lv.setStartX(resolveParamHelper.paramResolver(cx,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy + "+" + r,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "+" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "+" + r, mParamValue));

					lv.setEndX(resolveParamHelper.paramResolver(cx + "+" + r,
							mParamValue));
					lv.setEndY(resolveParamHelper
							.paramResolver(cy, mParamValue));

				}
				if (P.equals("AU")) {

					//System.out.println("AU" + " : " + curve.getShapeCurveId());

					lv.setStartX(resolveParamHelper.paramResolver(cx + "-" + r,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "-" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "+" + r, mParamValue));

					lv.setEndX(resolveParamHelper
							.paramResolver(cx, mParamValue));
					lv.setEndY(resolveParamHelper.paramResolver(cy + "+" + r,
							mParamValue));
				}
				if (P.equals("A")) {

					//System.out.println("AU" + " : " + curve.getShapeCurveId());

					lv.setStartX(resolveParamHelper.paramResolver(cx,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "+" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "+" + r, mParamValue));

					lv.setEndX(resolveParamHelper.paramResolver(cx + "+" + r,
							mParamValue));
					lv.setEndY(resolveParamHelper
							.paramResolver(cy, mParamValue));
				}

				if (P.equals("AD")) {

					//System.out.println("AD" + " : " + curve.getShapeCurveId());
					lv.setStartX(resolveParamHelper.paramResolver(cx,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy + "-" + r,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "+" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "-" + r, mParamValue));

					lv.setEndX(resolveParamHelper.paramResolver(cx + "+" + r,
							mParamValue));
					lv.setEndY(resolveParamHelper
							.paramResolver(cy, mParamValue));
				}
				if (P.equals("DD")) {

					//System.out.println("DD" + " : " + curve.getShapeCurveId());
					lv.setStartX(resolveParamHelper.paramResolver(cx + "-" + r,
							mParamValue));

					lv.setStartY(resolveParamHelper.paramResolver(cy,
							mParamValue));

					lv.setCenterX(resolveParamHelper.paramResolver(
							cx + "-" + r, mParamValue));
					lv.setCenterY(resolveParamHelper.paramResolver(
							cy + "-" + r, mParamValue));

					lv.setEndX(resolveParamHelper
							.paramResolver(cx, mParamValue));
					lv.setEndY(resolveParamHelper.paramResolver(cy + "-" + r,
							mParamValue));
				}

				listCurveValues.add(lv);
			}
			// return mCurveValues;
			return listCurveValues;
		} catch (PersistenceException e) {
			//System.out.println("Exception Occured: " + e);
			return null;
		} catch (Exception ex) {
			//System.out.println("Exception Occured: " + ex);
			return null;
		}
	}

}
