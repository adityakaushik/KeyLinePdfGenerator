package com.trioprinter.keylines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "lines")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lines {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShapeLineId")
	int shapeLineId;
	
	@Column(name = "ShapeTemplateId")
	byte shapeTemplateId;
	
	@Column(name = "DesignName")
	String designName;
	
	@Column(name = "StartX")
	String startX;
	
	@Column(name = "StartY")
	String startY;
	
	@Column(name = "EndX")
	String endX;
	
	@Column(name = "EndY")
	String endY;	
	
	@Column(name = "Description")
	String description;
	
	@Column(name = "LineType")
	String lineType;

	public Lines(int shapeLineId, byte shapeTemplateId, String designName,
			String startX, String startY, String endX, String endY,
			String description, String lineType) {
		super();
		shapeLineId = shapeLineId;
		this.shapeTemplateId = shapeTemplateId;
		this.designName = designName;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.description = description;
		this.lineType = lineType;
	}

	public int getShapeLineId() {
		return shapeLineId;
	}

	public void setShapeLineId(int shapeLineId) {
		shapeLineId = shapeLineId;
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

	public String getStartX() {
		return startX;
	}

	public void setStartX(String startX) {
		this.startX = startX;
	}

	public String getStartY() {
		return startY;
	}

	public void setStartY(String startY) {
		this.startY = startY;
	}

	public String getEndX() {
		return endX;
	}

	public void setEndX(String endX) {
		this.endX = endX;
	}

	public String getEndY() {
		return endY;
	}

	public void setEndY(String endY) {
		this.endY = endY;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	@Override
	public String toString() {
		return "Lines [shapeLineId=" + shapeLineId + ", shapeTemplateId="
				+ shapeTemplateId + ", designName=" + designName + ", startX="
				+ startX + ", startY=" + startY + ", endX=" + endX + ", endY="
				+ endY + ", description=" + description + ", lineType="
				+ lineType + "]";
	}

	public Lines() {

	}

	
	
}
