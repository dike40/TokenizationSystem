package com.TokenizationSys.Tokenization;
/**
 * �����������������ָ��λ���������
 */
public class RanBitGenerator {
	/*�������������λ��*/
	int digit = -1; 
    /*RanBitGenerator�Ĺ��캯��*/
	public RanBitGenerator(int digit) {
		this.digit = digit;
	}
	
	/*ͨ���÷�����������*/
	public String getRanBits() {
		long ranBits; //long�͵������
		String sranBits; //Sting�͵������
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < digit; i++) {
			sb = sb.append(0);
		}
		sb = sb.insert(0, 1);
		//System.out.println("sb:"+ sb);
		/*����long�͵�digitλ����������п���ǰ��λΪ0*/
		ranBits = (long) (Math.random()*Long.parseLong(sb.toString())); 
		sranBits = Long.toString(ranBits);//ת����String����
		if(sranBits.length() != digit) { //��⣬����������λ����Ϊdigit������ǰ�油0��ʹ�乻digitλ
			StringBuilder padding = new StringBuilder();//�貹��0��λ��
			for(int i = 0; i < (digit - sranBits.length()); i++) {
				padding = padding.append(0);
			}
			sranBits = padding + sranBits;
			System.out.println("Entering padding mode/sranBits:"+sranBits);
		}	
		return sranBits;
	}
}
