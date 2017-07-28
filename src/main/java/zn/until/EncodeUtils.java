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
	  * Hex编码.
	  */
	 public static String hexEncode(byte[] input) {
	  return Hex.encodeHexString(input);
	 }
	 /**
	  * Hex解码.
	  */
	 public static byte[] hexDecode(String input) {
	  try {
	   return Hex.decodeHex(input.toCharArray());
	  } catch (DecoderException e) {
	   throw new IllegalStateException("Hex Decoder exception", e);
	  }
	 }
	 
	 
		/**
		 * 将byte转换为八位二进制字符串
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
	  * 浮点转换为字节 
	  *  
	  * @param f 
	  * @return 
	  */  
	 public static byte[] float2byte(float f) {  
	       
	     // 把float转换为byte[]  
	     int fbit = Float.floatToIntBits(f);  
	       
	     byte[] b = new byte[4];    
	     for (int i = 0; i < 4; i++) {    
	         b[i] = (byte) (fbit >> (24 - i * 8));    
	     }   
	       
	     // 翻转数组  
	     int len = b.length;  
	     // 建立一个与源数组元素类型相同的数组  
	     byte[] dest = new byte[len];  
	     // 为了防止修改源数组，将源数组拷贝一份副本  
	     System.arraycopy(b, 0, dest, 0, len);  
	     byte temp;  
	     // 将顺位第i个与倒数第i个交换  
	     for (int i = 0; i < len / 2; ++i) {  
	         temp = dest[i];  
	         dest[i] = dest[len - i - 1];  
	         dest[len - i - 1] = temp;  
	     }  	       
	     return dest;         
	 }  
	   
	 /** 
	  * 字节转换为浮点 
	  *  
	  * @param b 字节（至少4个字节） 
	  * @param index 开始位置 
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
	  * 将C/C++的无符号 DWORD类型转换为java的long型
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
		 
	 
	 
	 /** 
	     * 字符串转换成十六进制字符串 
	     *  
	     * @param String 
	     *            str 待转换的ASCII字符串 
	     * @return String 每个Byte之间空格分隔，如: [61 6C 6B] 
	     */  
	    public static String str2HexStr(String str) {  
	  
	        char[] chars = "0123456789ABCDEF".toCharArray();  
	        StringBuilder sb = new StringBuilder("");  
	        byte[] bs = str.getBytes();  
	        int bit;  
	  
	        for (int i = 0; i < bs.length; i++) {  
	            bit = (bs[i] & 0x0f0) >> 4;  
	            sb.append(chars[bit]);  
	            bit = bs[i] & 0x0f;  
	            sb.append(chars[bit]);  
	            sb.append(' ');  
	        }  
	        return sb.toString().trim();  
	    }  
	  
	    /** 
	     * 十六进制转换字符串 
	     *  
	     * @param String 
	     *            str Byte字符串(Byte之间无分隔符 如:[616C6B]) 
	     * @return String 对应的字符串 
	     */  
	    public static String hexStr2Str(String hexStr) {  
	        String str = "0123456789ABCDEF";  
	        char[] hexs = hexStr.toCharArray();  
	        byte[] bytes = new byte[hexStr.length() / 2];  
	        int n;  
	  
	        for (int i = 0; i < bytes.length; i++) {  
	            n = str.indexOf(hexs[2 * i]) * 16;  
	            n += str.indexOf(hexs[2 * i + 1]);  
	            bytes[i] = (byte) (n & 0xff);  
	        }  
	        return new String(bytes);  
	    }  
	  
	
	 
	 
	 public static void main(String[] args) {

		System.out.println(hexStr2Str("30313035423030323844325242").replaceAll("//s", ""));
	}

}
