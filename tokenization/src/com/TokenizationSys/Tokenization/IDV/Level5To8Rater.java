package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * ��������Token����������5��8������������
 *
 */
class Level5To8Rater extends Rater {

	@Override
	public void Rating(int reqTokAssuLevel, int accVerLevel, int riskScore) {
		if((5 <= reqTokAssuLevel) && (reqTokAssuLevel <= 8)) {
			if((30 <= riskScore) && (riskScore <= 210)) {
				if((reqTokAssuLevel - 4) <= accVerLevel){
					assigTokAssuLevel = reqTokAssuLevel;
				}
				else if((reqTokAssuLevel - 4) > accVerLevel) {
					assigTokAssuLevel = (accVerLevel + 4);
				}
			}
			else {
				successRater.Rating(4, accVerLevel, riskScore);
			}
		}
		else {
			successRater.Rating(reqTokAssuLevel, accVerLevel, riskScore);
		}
	}
}


