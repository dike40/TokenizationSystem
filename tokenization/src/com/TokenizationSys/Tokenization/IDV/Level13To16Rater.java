package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * 处理请求Token担保级别在13到16级的评级者类
 *
 */
class Level13To16Rater extends Rater {

	@Override
	public void Rating(int reqTokAssuLevel, int accVerLevel, int riskScore) {
		if((13 <= reqTokAssuLevel) && (reqTokAssuLevel <= 16)) {
			if((30 <= riskScore) && (riskScore <= 89)) {
				if((reqTokAssuLevel - 12) <= accVerLevel){
					assigTokAssuLevel = reqTokAssuLevel;
				}
				else if((reqTokAssuLevel - 12) > accVerLevel) {
					assigTokAssuLevel = (accVerLevel + 12);
				}
			}
			else {
				successRater.Rating(12, accVerLevel, riskScore);
			}
		}
		else {
			successRater.Rating(reqTokAssuLevel, accVerLevel, riskScore);
		}
	}
}



