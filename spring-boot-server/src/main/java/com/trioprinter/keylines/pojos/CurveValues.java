package com.trioprinter.keylines.pojos;

import javax.persistence.Column;


public class CurveValues {

	int shapeLineId;
	
	byte shapeTemplateId;
	
	String designName;

	double startX;
	
	double startY;
	
	double centerX;
	
	double centerY;
	
	double endX;
	
	double endY;

	double startAngle;
	
	double endAngle;
	
	double radius1;
	
	double radius2;
	
	public int getShapeLineId() {
		return shapeLineId;
	}

	public void setShapeLineId(int shapeLineId) {
		this.shapeLineId = shapeLineId;
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

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
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

	
	
	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}

	public double getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(double endAngle) {
		this.endAngle = endAngle;
	}

	public double getRadius1() {
		return radius1;
	}

	public void setRadius1(double radius1) {
		this.radius1 = radius1;
	}

	public double getRadius2() {
		return radius2;
	}

	public void setRadius2(double radius2) {
		this.radius2 = radius2;
	}

	
	
	public CurveValues(){
		
	}
	
}
