package com.trioprinter.keylines.services;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfBoolean;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.layer.PdfLayer;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.kernel.pdf.xobject.PdfTransparencyGroup;
import com.itextpdf.kernel.pdf.xobject.PdfXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.trioprinter.keylines.model.Lines;
import com.trioprinter.keylines.pojos.CurveValues;
import com.trioprinter.keylines.pojos.LineValues;

@Service
@Component
public class DrawLine {

	public static ByteArrayInputStream generateKeyLinePdf()
			throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// String path = "./Graphics/drawing_lines.pdf";
		PdfWriter pdfwriter = new PdfWriter(out);

		PdfDocument pdfdocument = new PdfDocument(pdfwriter);

		Document document = new Document(pdfdocument);
		// Creating a new page

		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream generateLinesinPdf(
			List<LineValues> lineslist) throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// String path = "./Graphics/drawing_lines.pdf";
		PdfWriter pdfwriter = new PdfWriter(out);

		PdfDocument pdfdocument = new PdfDocument(pdfwriter);

		Document document = new Document(pdfdocument);
		// Creating a new page
		PageSize pageSize = new PageSize(20000, 20000);
		// PdfPage pdfPage = pdfdocument.addNewPage();
		PdfPage pdfPage = pdfdocument.addNewPage(pageSize);
		// instantiating a PdfCanvas object
		PdfCanvas canvas = new PdfCanvas(pdfPage);
		for (LineValues lv : lineslist) {
			canvas.moveTo(lv.getStartX() * 50, lv.getStartY() * 50);
			canvas.lineTo(lv.getEndX() * 50, lv.getEndY() * 50);
			canvas.closePathStroke();
		}
		document.close();
		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream generateLinesAndCurvesinPdf(
			List<LineValues> lineslist, List<CurveValues> curveValueList,
			HashMap<String, Double> mParamValue) throws FileNotFoundException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// String path = "./Graphics/drawing_lines.pdf";
			PdfWriter pdfwriter = new PdfWriter(out);
			// PdfWriter pdfwriter2 = new PdfWriter(path);

			PdfDocument pdfdocument = new PdfDocument(pdfwriter);
			// PdfDocument pdfdocument2 = new PdfDocument(pdfwriter2);
			Document document = new Document(pdfdocument);
			// Document document2 = new Document(pdfdocument2);
			// Creating a new page
			PageSize pageSize = new PageSize(5000, 5000);

			// PdfPage pdfPage = pdfdocument.addNewPage();
			PdfPage pdfPage = pdfdocument.addNewPage(pageSize);

			// instantiating a PdfCanvas object
			PdfCanvas canvas = new PdfCanvas(pdfPage);

			// PdfExtGState gstate = new PdfExtGState();
			// gstate.setFillOpacity(0.5f);
			// PdfDictionary group = new PdfDictionary();
			// group.put(PdfName.Type, PdfName.Group);
			// group.put(PdfName.S, PdfName.Transparency);
			// group.put(PdfName.CS, PdfName.DeviceGray);
			// group.put(PdfName.I, new PdfNumber(0.5f));
			// group.put(PdfName.K, PdfBoolean.TRUE);
			// group.put(PdfName.ca, new PdfNumber(0.5f));
			// group.put(PdfName.CA, new PdfNumber(0.5f));

			// Begin the transparency group

			PdfLayer printLayer = new PdfLayer("printlines", pdfdocument);
			// PdfLayer printLayer2 = new PdfLayer("printlines",pdfdocument2 );
			printLayer.setOn(true);
			// printLayer2.setOn(true);

			canvas.beginLayer(printLayer);

			for (LineValues lv : lineslist) {
				// canvas.beginMarkedContent(new PdfName("Transparency"),
				// group);
				canvas.moveTo(lv.getStartX() * 10, lv.getStartY() * 10);
				canvas.lineTo(lv.getEndX() * 10, lv.getEndY() * 10);

			}

			canvas.endLayer();

			canvas.closePathStroke();

			PdfLayer printLayer01 = new PdfLayer("printcurves", pdfdocument);
			// PdfLayer printLayer02 = new PdfLayer("printcurves",pdfdocument2
			// );
			printLayer01.setOn(true);
			// printLayer02.setOn(true);
			canvas.beginLayer(printLayer01);

			Color magentaColor = new DeviceCmyk(0.f, 1.f, 0.f, 0.f);
			canvas.setStrokeColor(magentaColor);

			for (CurveValues cv : curveValueList) {
				canvas.moveTo(cv.getStartX() * 10, cv.getStartY() * 10);
				canvas.curveTo(cv.getStartX() * 10, cv.getStartY() * 10,
						cv.getCenterX() * 10, cv.getCenterY() * 10,
						cv.getEndX() * 10, cv.getEndY() * 10);

				canvas.moveTo(cv.getEndX() * 10, cv.getEndY() * 10);

			}
			canvas.endLayer();

			canvas.closePathStroke();

			// D+W+TUCKIN
			double y = mParamValue.get("YHeight");
			//System.out.println("height is " + y);

			double x = mParamValue.get("XWidth");
			//System.out.println("width is " + x);
			for (LineValues lv : lineslist) {
				// canvas.beginMarkedContent(new PdfName("Transparency"),
				// group);
				canvas.moveTo((lv.getStartX() + x) * 10,
						(lv.getStartY() + y) * 10);
				canvas.lineTo((lv.getEndX() + x) * 10, (lv.getEndY() + y) * 10);

			}
			for (CurveValues cv : curveValueList) {
				canvas.moveTo((cv.getStartX() + x) * 10,
						(cv.getStartY() + y) * 10);
				canvas.curveTo((cv.getStartX() + x) * 10,
						(cv.getStartY() + y) * 10, (cv.getCenterX() + x) * 10,
						(cv.getCenterY() + y) * 10, (cv.getEndX() + x) * 10,
						(cv.getEndY() + y) * 10);

				canvas.moveTo((cv.getEndX() + x) * 10, (cv.getEndY() + y) * 10);

			}

			canvas.closePathStroke();

			document.close();

			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			//System.out.println(e);
			return null;
		}
	}

	public ByteArrayInputStream generateLinesAndCurvesinPdf(
			List<LineValues> lineslist, List<CurveValues> curveValueList,
			HashMap<String, Double> mParamValue, int numX, int numY) {
		// TODO Auto-generated method stub
		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// String path = "./Graphics/drawing_lines.pdf";
			PdfWriter pdfwriter = new PdfWriter(out);
			// PdfWriter pdfwriter2 = new PdfWriter(path);

			PdfDocument pdfdocument = new PdfDocument(pdfwriter);
			// PdfDocument pdfdocument2 = new PdfDocument(pdfwriter2);
			Document document = new Document(pdfdocument);
			// Document document2 = new Document(pdfdocument2);
			// Creating a new page
			PageSize pageSize = new PageSize(5000, 5000);

			// PdfPage pdfPage = pdfdocument.addNewPage();
			PdfPage pdfPage = pdfdocument.addNewPage(pageSize);

			// instantiating a PdfCanvas object
			PdfCanvas canvas = new PdfCanvas(pdfPage);

			// Begin the transparency group
			double x = 0;
			
			double width = mParamValue.get("XWidth");
			double height = mParamValue.get("YHeight");
			for (int i = 0; i < numX; i++) {
				double y = 0;
				for (int k = 0; k < numY; k++) {
					//System.out.println("width is " + x);
					//System.out.println("height is " + y);
					PdfLayer printLayer = new PdfLayer("printlines"+i+"."+k,
							pdfdocument);
					printLayer.setOn(true);

					canvas.beginLayer(printLayer);

					for (LineValues lv : lineslist) {
						canvas.moveTo((lv.getStartX() + x) * 10,
								(lv.getStartY() + y) * 10);
						canvas.lineTo((lv.getEndX() + x) * 10,
								(lv.getEndY() + y) * 10);

					}

					canvas.endLayer();

//					canvas.closePathStroke();

					PdfLayer printLayer01 = new PdfLayer("printcurves"+i+"."+k,
							pdfdocument);
					// PdfLayer printLayer02 = new
					// PdfLayer("printcurves",pdfdocument2 );
					printLayer01.setOn(true);
					// printLayer02.setOn(true);
					canvas.beginLayer(printLayer01);

					Color magentaColor = new DeviceCmyk(0.f, 1.f, 0.f, 0.f);
					canvas.setStrokeColor(magentaColor);

					for (CurveValues cv : curveValueList) {
						canvas.moveTo((cv.getStartX() + x) * 10,
								(cv.getStartY() + y) * 10);
						canvas.curveTo((cv.getStartX() + x) * 10,
								(cv.getStartY() + y) * 10,
								(cv.getCenterX() + x) * 10,
								(cv.getCenterY() + y) * 10,
								(cv.getEndX() + x) * 10,
								(cv.getEndY() + y) * 10);

						canvas.moveTo((cv.getEndX() + x) * 10,
								(cv.getEndY() + y) * 10);

					}
					canvas.endLayer();
					y = y + height;
				}
				x = x + width;
			}
			canvas.closePathStroke();
			document.close();
			

			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			//System.out.println(e);
			return null;
		}
	}
	
	
	
	public static PdfDocument generateLinesAndCurvesinObject(
			List<LineValues> lineslist, List<CurveValues> curveValueList,
			HashMap<String, Double> mParamValue) throws FileNotFoundException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// String path = "./Graphics/drawing_lines.pdf";
			PdfWriter pdfwriter = new PdfWriter(out);
			// PdfWriter pdfwriter2 = new PdfWriter(path);

			PdfDocument pdfdocument = new PdfDocument(pdfwriter);
			// PdfDocument pdfdocument2 = new PdfDocument(pdfwriter2);
			//Document document = new Document(pdfdocument);
			// Document document2 = new Document(pdfdocument2);
			// Creating a new page
			PageSize pageSize = new PageSize(1200, 1200);

			// PdfPage pdfPage = pdfdocument.addNewPage();
			PdfPage pdfPage = pdfdocument.addNewPage(pageSize);

			// instantiating a PdfCanvas object
			PdfCanvas canvas = new PdfCanvas(pdfPage);

			

			PdfLayer printLayer = new PdfLayer("printlines", pdfdocument);
			// PdfLayer printLayer2 = new PdfLayer("printlines",pdfdocument2 );
			printLayer.setOn(true);
			// printLayer2.setOn(true);

			canvas.beginLayer(printLayer);

			for (LineValues lv : lineslist) {
				// canvas.beginMarkedContent(new PdfName("Transparency"),
				// group);
				canvas.moveTo(lv.getStartX() * 10, lv.getStartY() * 10);
				canvas.lineTo(lv.getEndX() * 10, lv.getEndY() * 10);

			}

			canvas.endLayer();

			canvas.closePathStroke();

			PdfLayer printLayer01 = new PdfLayer("printcurves", pdfdocument);
			// PdfLayer printLayer02 = new PdfLayer("printcurves",pdfdocument2
			// );
			printLayer01.setOn(true);
			// printLayer02.setOn(true);
			canvas.beginLayer(printLayer01);

			Color magentaColor = new DeviceCmyk(0.f, 1.f, 0.f, 0.f);
			canvas.setStrokeColor(magentaColor);

			for (CurveValues cv : curveValueList) {
				canvas.moveTo(cv.getStartX() * 10, cv.getStartY() * 10);
				canvas.curveTo(cv.getStartX() * 10, cv.getStartY() * 10,
						cv.getCenterX() * 10, cv.getCenterY() * 10,
						cv.getEndX() * 10, cv.getEndY() * 10);

				canvas.moveTo(cv.getEndX() * 10, cv.getEndY() * 10);

			}
			canvas.endLayer();
			canvas.closePathStroke();
			//document.close();
		//	PdfObject pdfObject = pdfdocument.getPdfObject(1);
			return pdfdocument;
		} catch (Exception e) {
			//System.out.println(e);
			return null;
		}
	}
	
	
	public ByteArrayInputStream generateLinesAndCurvesinPdfPrint(
			List<LineValues> lineslist, List<CurveValues> curveValueList,
			HashMap<String, Double> mParamValue, int numX, int numY) {
		try {
			

//			if (pdfObj.isStream()) {
//			    PdfStream pdfStream = (PdfStream) pdfObj;
//			    PdfDictionary pdfDict = pdfStream.getAsDictionary(PdfName.Resources);
//			     xObject = new PdfFormXObject(pdfStream);
//			}
			//System.out.println("xObject created");
			//PdfXObject xObject = new PdfFormXObject(Object);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// String path = "./Graphics/drawing_lines.pdf";
			PdfWriter pdfwriter = new PdfWriter(out);
			// PdfWriter pdfwriter2 = new PdfWriter(path);

			PdfDocument pdfdocument = new PdfDocument(pdfwriter);
			PdfDocument srcPdfDoc = generateLinesAndCurvesinObject(lineslist,curveValueList,mParamValue);
			// Get the PdfObject you want to use from the source PDF document
			srcPdfDoc.close();
	        PdfObject obj = srcPdfDoc.getPdfObject(1);
	        PdfObject objCopy = obj.copyTo(srcPdfDoc, true);
	        
	     // Create a new PdfFormXObject using the copied PdfObject
	        PdfFormXObject formXObject = new PdfFormXObject((PdfStream)objCopy);
			// PdfDocument pdfdocument2 = new PdfDocument(pdfwriter2);
			Document document = new Document(pdfdocument);
			// Document document2 = new Document(pdfdocument2);
			// Creating a new page
			PageSize pageSize = new PageSize(5000, 5000);

			// PdfPage pdfPage = pdfdocument.addNewPage();
			PdfPage pdfPage = pdfdocument.addNewPage(pageSize);

			// instantiating a PdfCanvas object
			PdfCanvas canvas = new PdfCanvas(pdfPage);

			// Begin the transparency group
			double x = 0;
			
			double width = mParamValue.get("XWidth");
			double height = mParamValue.get("YHeight");
			for (int i = 0; i < numX; i++) {
				double y = 0;
				for (int k = 0; k < numY; k++) {
					//System.out.println("width is " + x);
					//System.out.println("height is " + y);
					PdfLayer printLayer = new PdfLayer("printlines"+i+"."+k,
							pdfdocument);
					printLayer.setOn(true);

					canvas.beginLayer(printLayer);
					//canvas.addXObject(formXObject);
					canvas.addXObject(formXObject);
					canvas.endLayer();

//					canvas.closePathStroke();

					PdfLayer printLayer01 = new PdfLayer("printcurves"+i+"."+k,
							pdfdocument);
					// PdfLayer printLayer02 = new
					// PdfLayer("printcurves",pdfdocument2 );
					printLayer01.setOn(true);
					// printLayer02.setOn(true);
					canvas.beginLayer(printLayer01);

					

					canvas.endLayer();
					y = y + height;
				}
				x = x + width;
			}
			canvas.closePathStroke();
			document.close();
			

			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			//System.out.println(e);
			return null;
		}
	}
}
