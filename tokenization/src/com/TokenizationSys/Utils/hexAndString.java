package com.TokenizationSys.Utils;

public class hexAndString {
	
	public static byte[] hexToBytes(String hexString) {
		char[] hex = hexString.toCharArray();
		// 转rawData长度减半
		int length = hex.length / 2;
		byte[] rawData = new byte[length];
		for (int i = 0; i < length; i++) {
			// 先将hex转10进位数值
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			// 將第一個值的二進位值左平移4位,ex: 00001000 => 10000000 (8=>128)
			// 然后与第二个值的二进位值作联集ex: 10000000 | 00001100 => 10001100 (137)
			int value = (high << 4) | low;
			// 与FFFFFFFF作补集
			if (value > 127) {
				value -= 256;
			}
			// 最后转回byte就OK
			rawData[i] = (byte) value;
		}
		return rawData;
	}
	public static final String bytesToHexString(byte[] buf) {
		StringBuilder sb = new StringBuilder(buf.length * 2);
		String tmp = "";
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < buf.length; i++) {
			// 1.
			// sb.append(Integer.toHexString((buf[i] & 0xf0) >> 4));
			// sb.append(Integer.toHexString((buf[i] & 0x0f) >> 0));
			// //////////////////////////////////////////////////////////////////
			// 2.sodino更喜欢的方式，嘿嘿...
			tmp = Integer.toHexString(0xff & buf[i]);
			tmp = tmp.length() == 1 ? "0" + tmp : tmp;
			sb.append(tmp);
		}
		return sb.toString();
	}

}
