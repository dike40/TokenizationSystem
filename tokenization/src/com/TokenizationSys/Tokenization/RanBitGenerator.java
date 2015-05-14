package com.TokenizationSys.Tokenization;
/**
 * 随机数产生器，产生指定位数的随机数
 */
public class RanBitGenerator {
	/*产生的随机数的位数*/
	int digit = -1; 
    /*RanBitGenerator的构造函数*/
	public RanBitGenerator(int digit) {
		this.digit = digit;
	}
	
	/*通过该方法获得随机数*/
	public String getRanBits() {
		long ranBits; //long型的随机数
		String sranBits; //Sting型的随机数
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < digit; i++) {
			sb = sb.append(0);
		}
		sb = sb.insert(0, 1);
		//System.out.println("sb:"+ sb);
		/*生成long型的digit位随机数，但有可能前几位为0*/
		ranBits = (long) (Math.random()*Long.parseLong(sb.toString())); 
		sranBits = Long.toString(ranBits);//转化成String类型
		if(sranBits.length() != digit) { //检测，如果此随机数位数不为digit，则在前面补0，使其够digit位
			StringBuilder padding = new StringBuilder();//需补的0的位数
			for(int i = 0; i < (digit - sranBits.length()); i++) {
				padding = padding.append(0);
			}
			sranBits = padding + sranBits;
			System.out.println("Entering padding mode/sranBits:"+sranBits);
		}	
		return sranBits;
	}
}
