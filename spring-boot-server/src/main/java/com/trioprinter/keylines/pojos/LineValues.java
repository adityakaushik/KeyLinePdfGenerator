package com.trioprinter.keylines.pojos;

import javax.persistence.Column;

public class LineValues {

	int ShapeLineId;
	
	byte shapeTemplateId;
	
	String designName;

	double startX;
	
	double startY;

	double endX;
	
	double endY;

	public int getShapeLineId() {
		return ShapeLineId;
	}

	public void setShapeLineId(int shapeLineId) {
		ShapeLineId = shapeLineId;
	}

	public byte getShapeTemplateId() {
		return shapeTemplateId;
	}

	public void setShapeTemplateId(byte shapeTemplateId) {
		this.shapeTemplateId = shapeTemplateId;
	}

	public String getDesignName() {
		return designName;
	}

	public void setDesignName(String designName) {
		this.designName = designName;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public LineValues(int shapeLineId, byte shapeTemplateId, String designName,
			double startX, double startY, double endX, double endY) {
		super();
		ShapeLineId = shapeLineId;
		this.shapeTemplateId = shapeTemplateId;
		this.designName = designName;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	public LineValues(){
		
	}
	@Override
	public String toString() {
		return "LineValues [ShapeLineId=" + ShapeLineId + ", shapeTemplateId="
				+ shapeTemplateId + ", designName=" + designName + ", startX="
				+ startX + ", startY=" + startY + ", endX=" + endX + ", endY="
				+ endY + "]";
	}	
	
	
}
