/**
 * 
 */
package zn.until;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @author hq
 *
 */
public class EncodeUtils {
	 private static final String DEFAULT_URL_ENCODING = "UTF-8";
	 /**
	  * Hex����.
	  */
	 public static String hexEncode(byte[] input) {
	  return Hex.encodeHexString(input);
	 }
	 /**
	  * Hex����.
	  */
	 public static byte[] hexDecode(String input) {
	  try {
	   return Hex.decodeHex(input.toCharArray());
	  } catch (DecoderException e) {
	   throw new IllegalStateException("Hex Decoder exception", e);
	  }
	 }
	 
	 
		/**
		 * ��byteת��Ϊ��λ�������ַ���
		 * @Title: byte2bits 
		 * @Description: TODO
		 * @param b
		 * @return   
		 * @throws
		 */
		public  static   String byte2bits(byte b) {
			  int z = b; z |= 256;		
			  String str = Integer.toBinaryString(z);
			  int len = str.length(); 			
			  return str.substring(len-8, len);
			}

	 /** 
	  * ����ת��Ϊ�ֽ� 
	  *  
	  * @param f 
	  * @return 
	  */  
	 public static byte[] float2byte(float f) {  
	       
	     // ��floatת��Ϊbyte[]  
	     int fbit = Float.floatToIntBits(f);  
	       
	     byte[] b = new byte[4];    
	     for (int i = 0; i < 4; i++) {    
	         b[i] = (byte) (fbit >> (24 - i * 8));    
	     }   
	       
	     // ��ת����  
	     int len = b.length;  
	     // ����һ����Դ����Ԫ��������ͬ������  
	     byte[] dest = new byte[len];  
	     // Ϊ�˷�ֹ�޸�Դ���飬��Դ���鿽��һ�ݸ���  
	     System.arraycopy(b, 0, dest, 0, len);  
	     byte temp;  
	     // ��˳λ��i���뵹����i������  
	     for (int i = 0; i < len / 2; ++i) {  
	         temp = dest[i];  
	         dest[i] = dest[len - i - 1];  
	         dest[len - i - 1] = temp;  
	     }  	       
	     return dest;         
	 }  
	   
	 /** 
	  * �ֽ�ת��Ϊ���� 
	  *  
	  * @param b �ֽڣ�����4���ֽڣ� 
	  * @param index ��ʼλ�� 
	  * @return 
	  */  
	 public static float byte2float(byte[] b, int index) {    
	     int l;                                             
	     l = b[index + 0];                                  
	     l &= 0xff;                                         
	     l |= ((long) b[index + 1] << 8);                   
	     l &= 0xffff;                                       
	     l |= ((long) b[index + 2] << 16);                  
	     l &= 0xffffff;                                     
	     l |= ((long) b[index + 3] << 24);                  
	     return Float.intBitsToFloat(l);                    
	 }  
	 
	
	 /**
	  * ��C/C++���޷��� DWORD����ת��Ϊjava��long��
	  * @Title: getLong 
	  * @Description: TODO
	  * @param buf
	  * @param index
	  * @return   
	  * @throws
	  */
	 public  static long getLong(byte buf[], int index) {

		 int firstByte = (0x000000FF & ((int) buf[index]));
		 
		 int secondByte = (0x000000FF & ((int) buf[index + 1]));
		
		 int thirdByte = (0x000000FF & ((int) buf[index + 2]));
		 System.out.println();
		 int fourthByte = (0x000000FF & ((int) buf[index + 3]));

		 long unsignedLong = ((long) (firstByte | secondByte << 8 | thirdByte << 16 | fourthByte << 24)) & 0xFFFFFFFFL;

		 return unsignedLong;
		 }
		 

}
