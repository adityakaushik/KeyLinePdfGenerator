package com.trioprinter.keylines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name = "curves")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Curves {
	@Id
	@Column(name = "ShapeCurveId")
	int shapeCurveId;
	
	@Column(name = "ShapeTemplateId")
    byte shapeTemplateId;
	
	@Column(name = "DesignName")
    String designName;
	
	@Column(name = "CenterX")
    String centerX;
	
	@Column(name = "CenterY")
    String CenterY;
	
	@Column(name = "Radius1")
    String radius1;
	
	@Column(name = "Radius2")
    String radius2;
	
	@Column(name = "StartAngle")
    String startAngle;
	
	@Column(name = "EndAngle")
    String endAngle;
	
	@Column(name = "Pie")
    String pie;
	
	@Column(name = "LineType")
    String lineType;

	public int getShapeCurveId() {
		return shapeCurveId;
	}

	public void setShapeCurveId(int shapeCurveId) {
		this.shapeCurveId = shapeCurveId;
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

	public String getCenterX() {
		return centerX;
	}

	public void setCenterX(String centerX) {
		this.centerX = centerX;
	}

	public String getCenterY() {
		return CenterY;
	}

	public void setCenterY(String centerY) {
		CenterY = centerY;
	}

	public String getRadius1() {
		return radius1;
	}

	public void setRadius1(String radius1) {
		this.radius1 = radius1;
	}

	public String getRadius2() {
		return radius2;
	}

	public void setRadius2(String radius2) {
		this.radius2 = radius2;
	}

	public String getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(String startAngle) {
		this.startAngle = startAngle;
	}

	public String getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(String endAngle) {
		this.endAngle = endAngle;
	}

	public String getPie() {
		return pie;
	}

	public void setPie(String pie) {
		this.pie = pie;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public Curves(int shapeCurveId, byte shapeTemplateId, String designName,
			String centerX, String centerY, String radius1, String radius2,
			String startAngle, String endAngle, String pie, String lineType) {
		super();
		this.shapeCurveId = shapeCurveId;
		this.shapeTemplateId = shapeTemplateId;
		this.designName = designName;
		this.centerX = centerX;
		CenterY = centerY;
		this.radius1 = radius1;
		this.radius2 = radius2;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.pie = pie;
		this.lineType = lineType;
	}

	public Curves(){
		
	}
}
