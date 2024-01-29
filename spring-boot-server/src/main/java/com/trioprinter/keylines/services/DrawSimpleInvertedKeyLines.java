package com.trioprinter.keylines.services;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.layer.PdfLayer;
import com.itextpdf.layout.Document;
import com.trioprinter.keylines.pojos.CurveValues;
import com.trioprinter.keylines.pojos.LineValues;

@Service
@Component
public class DrawSimpleInvertedKeyLines {

	public ByteArrayInputStream generateSimpleKeyLine(
			List<LineValues> lineslist, List<CurveValues> curveValueList,
			HashMap<String, Double> mParamValue, int numX, int numY) {
		// TODO Auto-generated method stub
		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// String path = "./Graphics/drawing_lines.pdf";
			PdfWriter pdfwriter = new PdfWriter(out);
			// PdfWriter pdfwriter2 = new PdfWriter(path);
			double width = mParamValue.get("XWidth");
			double height = mParamValue.get("YHeight");
			//System.out.println("width is :- " + width);
			//System.out.println("height is :- " + height);
			//System.out.println("numX is :- " + numX);
			//System.out.println("numY is :- " + numY);
			float totalwidth = (float) (width *(3.7795275591) * numX);
			float totalheight = (float) ((height *(3.7795275591) * numY) + 4 * height);
			PdfDocument pdfdocument = new PdfDocument(pdfwriter);
			// PdfDocument pdfdocument2 = new PdfDocument(pdfwriter2);
			Document document = new Document(pdfdocument);
			// Document document2 = new Document(pdfdocument2);
			// Creating a new page
			PageSize pageSize = new PageSize(totalwidth, totalheight);

			// PdfPage pdfPage = pdfdocument.addNewPage();
			PdfPage pdfPage = pdfdocument.addNewPage(pageSize);

			// instantiating a PdfCanvas object
			PdfCanvas canvas = new PdfCanvas(pdfPage);

			// Begin the transparency group
			double x = 0;

			for (int i = 0; i < numX; i++) {
				double y = 0;
				for (int k = 0; k < numY; k++) {

					if (i % 2 == 0 || i == 0) {
						//System.out.println("in if i=" + i + "k=" + k);
						PdfLayer printLayer = new PdfLayer("printlines" + i
								+ "." + k, pdfdocument);
						printLayer.setOn(true);

						canvas.beginLayer(printLayer);
						printLines(canvas, lineslist, x, y);
						canvas.endLayer();

						PdfLayer printLayer01 = new PdfLayer("printcurves" + i
								+ "." + k, pdfdocument);
						printLayer01.setOn(true);
						canvas.beginLayer(printLayer01);
						Color magentaColor = new DeviceCmyk(0.f, 1.f, 0.f, 0.f);

						canvas.setStrokeColor(magentaColor);
						printCurves(canvas, curveValueList, x, y);
						canvas.endLayer();
						canvas.closePathStroke();
					}
					y = y + height;
				}
				x = x + width;

			}

			for (int j = 0; j < numX; j++) {
				height=164;
				for (int l = 0; l < numY; l++) {
					if (j % 2 != 0) {
						
						PdfLayer printLayer1 = new PdfLayer("printInverted" + j + "."
								+ l, pdfdocument);
						printLayer1.setOn(true);

						
						//System.out.println("in if j=" + j);
						canvas.saveState();
						canvas.beginLayer(printLayer1);
						printLines(canvas, lineslist, 0, 0);
						printCurves(canvas, curveValueList, 0, 0);
						PdfArray rotationMatrix = new PdfArray();
						rotationMatrix.add(new PdfNumber(-1)); // a = -1
						rotationMatrix.add(new PdfNumber(0)); // b = 0
						rotationMatrix.add(new PdfNumber(0)); // c = 0
						rotationMatrix.add(new PdfNumber(-1)); // d = -1
						rotationMatrix.add(new PdfNumber((j+1) * width *(3.7795275591))); // e
																			// =
																			// page
																			// width
						rotationMatrix.add(new PdfNumber(height *(3.7795275591))); // f
																		// =page
																		// height
						canvas.concatMatrix(rotationMatrix);
						canvas.endLayer();
						canvas.closePathStroke();
						height = height + 122;
						canvas.restoreState();
					}

				}
			}
			document.close();

			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			//System.out.println(e);
			return null;
		}
	}

	void printLines(PdfCanvas canvas, List<LineValues> lineslist, double x,
			double y) {
		for (LineValues lv : lineslist) {
			// //System.out.println("canvas.moveTo("+(lv.getStartX() +
			// x)+" , "+(lv.getStartY() + y)+");");
			canvas.moveTo((lv.getStartX() + x) *(3.7795275591), (lv.getStartY() + y) *(3.7795275591));

			// //System.out.println("canvas.lineTo("+(lv.getEndX() +
			// x)+" , "+(lv.getEndY() + y)+");");
			canvas.lineTo((lv.getEndX() + x) *(3.7795275591), (lv.getEndY() + y) *(3.7795275591));

		}
	}

	void printCurves(PdfCanvas canvas, List<CurveValues> curveValueList,
			double x, double y) {
		for (CurveValues cv : curveValueList) {
			canvas.moveTo((cv.getStartX() + x) *(3.7795275591), (cv.getStartY() + y) *(3.7795275591));
			canvas.curveTo((cv.getStartX() + x) *(3.7795275591),
					(cv.getStartY() + y) *(3.7795275591),

					(cv.getCenterX() + x) *(3.7795275591), (cv.getCenterY() + y) *(3.7795275591),

					(cv.getEndX() + x) *(3.7795275591), (cv.getEndY() + y) *(3.7795275591));

			canvas.moveTo((cv.getEndX() + x) *(3.7795275591), (cv.getEndY() + y) *(3.7795275591));
		}
	}

}
