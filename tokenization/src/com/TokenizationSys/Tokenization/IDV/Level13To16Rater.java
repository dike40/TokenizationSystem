package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * ��������Token����������13��16������������
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



