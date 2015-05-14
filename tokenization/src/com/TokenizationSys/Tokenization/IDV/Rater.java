package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * 评级者的父类
 *
 */
abstract class Rater {
	/*静态的，所有Rater的子类共享*/
	static int assigTokAssuLevel = -1; 
	/*后继的评级者*/
	protected Rater successRater = null;
	/*设置后继的评级者的方法*/
	public void setSuccessRater(Rater successRater) {
		this.successRater = successRater;
	}
	/*评级的方法*/
	public abstract void Rating(int reqTokAssuLevel, int accVerLevel, int riskScore); 
	/*获取指定Token担保级别的方法*/
    public int getAssigTokAssuLevel() {
		return assigTokAssuLevel;
	}
}
