package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * ��������Token����������1��4������������
 *
 */
class Level1To4Rater extends Rater {

	@Override
	public void Rating(int reqTokAssuLevel, int accVerLevel, int riskScore) {
		if((1 <= reqTokAssuLevel) && (reqTokAssuLevel <= 4)) {
			if(reqTokAssuLevel <= accVerLevel){
				assigTokAssuLevel = reqTokAssuLevel;
			}
			else if(reqTokAssuLevel > accVerLevel) {
				assigTokAssuLevel = accVerLevel;
			}
		}
		else {
			successRater.Rating(reqTokAssuLevel, accVerLevel, riskScore);//���Բ�д��
		}
	}
}
