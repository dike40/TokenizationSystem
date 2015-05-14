package com.TokenizationSys.Utils;

public class hexAndString {
	
	public static byte[] hexToBytes(String hexString) {
		char[] hex = hexString.toCharArray();
		// תrawData���ȼ���
		int length = hex.length / 2;
		byte[] rawData = new byte[length];
		for (int i = 0; i < length; i++) {
			// �Ƚ�hexת10��λ��ֵ
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			// ����һ��ֵ�Ķ��Mλֵ��ƽ��4λ,ex: 00001000 => 10000000 (8=>128)
			// Ȼ����ڶ���ֵ�Ķ���λֵ������ex: 10000000 | 00001100 => 10001100 (137)
			int value = (high << 4) | low;
			// ��FFFFFFFF������
			if (value > 127) {
				value -= 256;
			}
			// ���ת��byte��OK
			rawData[i] = (byte) value;
		}
		return rawData;
	}
	public static final String bytesToHexString(byte[] buf) {
		StringBuilder sb = new StringBuilder(buf.length * 2);
		String tmp = "";
		// ���ֽ�������ÿ���ֽڲ���2λ16��������
		for (int i = 0; i < buf.length; i++) {
			// 1.
			// sb.append(Integer.toHexString((buf[i] & 0xf0) >> 4));
			// sb.append(Integer.toHexString((buf[i] & 0x0f) >> 0));
			// //////////////////////////////////////////////////////////////////
			// 2.sodino��ϲ���ķ�ʽ���ٺ�...
			tmp = Integer.toHexString(0xff & buf[i]);
			tmp = tmp.length() == 1 ? "0" + tmp : tmp;
			sb.append(tmp);
		}
		return sb.toString();
	}

}
