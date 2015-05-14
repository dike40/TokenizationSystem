package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * ��������Token����������9��12������������
 *
 */
class Level9To12Rater extends Rater {

	@Override
	public void Rating(int reqTokAssuLevel, int accVerLevel, int riskScore) {
		if((9 <= reqTokAssuLevel) && (reqTokAssuLevel <= 12)) {
			if((30 <= riskScore) && (riskScore <= 149)) {
				if((reqTokAssuLevel - 8) <= accVerLevel){
					assigTokAssuLevel = reqTokAssuLevel;
				}
				else if((reqTokAssuLevel - 8) > accVerLevel) {
					assigTokAssuLevel = (accVerLevel + 8);
				}
			}
			else {
				successRater.Rating(8, accVerLevel, riskScore);
			}
		}
		else {
			successRater.Rating(reqTokAssuLevel, accVerLevel, riskScore);
		}
	}
}
