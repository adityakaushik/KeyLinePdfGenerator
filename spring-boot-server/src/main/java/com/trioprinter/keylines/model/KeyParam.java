package com.trioprinter.keylines.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name = "keyparam")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyParam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int ParamId;
	
	@Column(name = "ShapeTemplateId")
	byte shapeTemplateId;
	
	@Column(name = "ShapeTemplateName")
	String shapeTemplateName;
	@Column(name = "ParameterName")
	String paramName;
	@Column(name = "DisplayName")
	String displayName;
	@Column(name = "Value")
	double value;
	@Column(name = "Formula")
	String formula;
	@Column(name = "ParameterType")
	String parameterType;
	public int getShapeTemplateId() {
		return shapeTemplateId;
	}
	public void setShapeTemplateId(byte  shapeTemplateId) {
		this.shapeTemplateId = shapeTemplateId;
	}
	public String getShapeTemplateName() {
		return shapeTemplateName;
	}
	public void setShapeTemplateName(String shapeTemplateName) {
		this.shapeTemplateName = shapeTemplateName;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		if (formula!=null){
		this.formula = formula;
		}else {
			formula="null";
		}
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		if (parameterType!=null){
			this.parameterType = parameterType;
			}else {
				parameterType="null";
			}
		
	}
	public KeyParam() {

	}
	public KeyParam(int paramId, byte shapeTemplateId,
			String shapeTemplateName, String paramName, String displayName,
			float value, String formula, String parameterType) {
		super();
		ParamId = paramId;
		this.shapeTemplateId = shapeTemplateId;
		this.shapeTemplateName = shapeTemplateName;
		this.paramName = paramName;
		this.displayName = displayName;
		this.value = value;
		this.formula = formula;
		this.parameterType = parameterType;
	}
	@Override
	public String toString() {
		return "KeyParam [ParamId=" + ParamId + ", shapeTemplateId="
				+ shapeTemplateId + ", shapeTemplateName=" + shapeTemplateName
				+ ", paramName=" + paramName + ", displayName=" + displayName
				+ ", value=" + value + ", formula=" + formula
				+ ", parameterType=" + parameterType + "]";
	}

}
