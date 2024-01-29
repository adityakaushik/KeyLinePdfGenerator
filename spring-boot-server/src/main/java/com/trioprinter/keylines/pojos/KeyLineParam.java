package com.trioprinter.keylines.pojos;
public class KeyLineParam {
    int templateId;
	double L; 
	double W; 
	double D; 
	double CTBK;
	double TUCKIN;
	double PASTING;
	int numX;
	int numY;
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public double getL() {
		return L;
	}
	public void setL(double l) {
		L = l;
	}
	public double getW() {
		return W;
	}
	public void setW(double w) {
		W = w;
	}
	public double getD() {
		return D;
	}
	public void setD(double d) {
		D = d;
	}
	public double getCTBK() {
		return CTBK;
	}
	public void setCTBK(double cTBK) {
		CTBK = cTBK;
	}
	public double getTUCKIN() {
		return TUCKIN;
	}
	public void setTUCKIN(double tUCKIN) {
		TUCKIN = tUCKIN;
	}
	public double getPASTING() {
		return PASTING;
	}
	public void setPASTING(double pASTING) {
		PASTING = pASTING;
	}
	public int getNumX() {
		return numX;
	}
	public void setNumX(int numX) {
		this.numX = numX;
	}
	public int getNumY() {
		return numY;
	}
	public void setNumY(int numY) {
		this.numY = numY;
	}
	public KeyLineParam(int templateId, double l, double w, double d,
			double cTBK, double tUCKIN, double pASTING, int numX, int numY) {
		super();
		this.templateId = templateId;
		L = l;
		W = w;
		D = d;
		CTBK = cTBK;
		TUCKIN = tUCKIN;
		PASTING = pASTING;
		this.numX = numX;
		this.numY = numY;
	}
	public KeyLineParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
