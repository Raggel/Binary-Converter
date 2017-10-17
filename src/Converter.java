	import java.util.Scanner;

	public class Converter {
		public static void main( String[] args) {
			
			while (true) {
				System.out.println( "Enter a number: (Enter 'exit' to exit)");
				
				Scanner scanner = new Scanner( System.in);
				String str = scanner.next();
			
				if (str.equals("exit"))
					System.exit(0);
				
				System.out.println( "Enter a desired type:");
				String desiredType = scanner.next();
				if (!desiredType.equals("byte")  && !desiredType.equals("short")  
						&& !desiredType.equals("int") && !desiredType.equals("long") 
						&& !desiredType.equals("float")  && !desiredType.equals("double") )
				{
					desiredType = "";
					System.out.println(	"This is not a valid type");
				}
				
				Boolean negative = false;
				if (str.indexOf("-") == 0)
				{
					negative = true;
					str = str.substring(1);
				}
				
				String divMark;
				if (str.indexOf(".") != -1)
					divMark = ".";
				else if (str.indexOf(",") != -1)
					divMark = ",";
				else divMark = "";
				
				long intPart;
				if (divMark != "")
					intPart = Long.parseLong( str.substring( 0, str.indexOf( divMark)));
				else
					intPart = Long.parseLong( str);
				
				//System.out.println( intPart);
			
				long decPart;
				if (divMark != "")
					decPart = Long.parseLong( str.substring( str.indexOf(divMark) + 1));
				else
					decPart = 0;
				
				//System.out.println( decPart);
				
				String tempStr = "";
				int count = 0;
				if (intPart2Binary( intPart).length() % 8 > 0)
					count = 8 - intPart2Binary( intPart).length() % 8;
				for (int i = 0; i < count; i++)
					tempStr += "0";
				String binaryIntPart = tempStr + intPart2Binary( intPart);
				//System.out.println( binaryIntPart);
				
				String binaryDecPart = decPart2Binary( decPart);
				
				if (!negative) {
					if (decPart == 0) {
					System.out.print( intPart + " = ");
					
					String type = "";
					
					if (intPart >= -128 && intPart <= 127)
						type = "byte";
					else if (intPart >= -32768 && intPart <= 32767)
						type = "short";
					else if (intPart >= -2147483648 && intPart <= 2147483647)
						type = "int";
					else
						type = "long";
					
					switch (desiredType) {
					case "byte":
						if (!(intPart >= -128 && intPart <= 127))
							desiredType = type;
						break;
					case "short":
						if (!(intPart >= -32768 && intPart <= 32767))
							desiredType = type;
						break;
					case "int":
						if (!(intPart >= -2147483648 && intPart <= 2147483647))
							desiredType = type;
						break;
						
					case "float":
							desiredType = type;
						break;
						
					case "double":
							desiredType = type;
						break;
					}
					
					if (desiredType.equals(""))
						desiredType = type;
					
					System.out.print( "(" + desiredType + ")");
					
					String tempStr2 = "";
					switch (desiredType) {
					case "byte":
						for (int i = 0; i < 8 - binaryIntPart.length(); i++)
							tempStr2 += "0";
						break;
					case "short":
						for (int i = 0; i < 16 - binaryIntPart.length(); i++)
							tempStr2 += "0";
						break;
					case "int":
						for (int i = 0; i < 32 - binaryIntPart.length(); i++)
							tempStr2 += "0";
						break;
					case "long":
						for (int i = 0; i < 64 - binaryIntPart.length(); i++)
							tempStr2 += "0";
						break;
					}
					
					tempStr2 += binaryIntPart;
					binaryIntPart = tempStr2;
					
					for (int i = 0; i < binaryIntPart.length(); i++)
						if (i % 8 != 0)
							System.out.print( binaryIntPart.charAt(i));
						else
						{
							System.out.print(" ");
							System.out.print( binaryIntPart.charAt(i));
						}
					
					System.out.print(", 0x" + binary2Base16( binaryIntPart));
					
					
				}
					if (decPart != 0) {
						System.out.print( intPart + "." + decPart + " = ");
						
						String type = "";
						
							if (intPart >= Float.MIN_VALUE && intPart <= Float.MAX_VALUE)
								type = "float";
							else
								type = "double";
							
							
							if (!(intPart >= Float.MIN_VALUE && intPart <= Float.MAX_VALUE))
								desiredType = type;
						
						if (desiredType.equals(""))
							desiredType = type;
						
						if (!desiredType.equals("float") && !desiredType.equals("double"))
							desiredType = type;
						
						System.out.print( "(" + desiredType + ")");
						
						String tempStr2 = "";
						switch (desiredType) {
						case "float":
							for (int i = 0; i < 32 - binaryIntPart.length(); i++)
								tempStr2 += "0";
							break;
						case "double":
							for (int i = 0; i < 64 - binaryIntPart.length(); i++)
								tempStr2 += "0";
							break;
						}
						
						tempStr2 += binaryIntPart;
						binaryIntPart = tempStr2;
						
						for (int i = 0; i < binaryIntPart.length(); i++)
							if (i % 8 != 0)
								System.out.print( binaryIntPart.charAt(i));
							else
							{
								System.out.print(" ");
								System.out.print( binaryIntPart.charAt(i));
							}
						
						System.out.print( ".");
					
						for (int i = 0; i < decPart2Binary( decPart).length(); i++)
							if (i % 8 != 0)
								System.out.print( decPart2Binary( decPart).charAt(i));
							else
							{
								System.out.print(" ");
								System.out.print( decPart2Binary( decPart).charAt(i));
							}
					}
					
				} else {
					
					if (decPart == 0)
						System.out.print( "-" + intPart + " =" + negativeNum2Binary( intPart, decPart) + ", -0x" + binary2Base16( binaryIntPart));
					else
						System.out.print( "-" + intPart + "." + decPart + " =" + negativeNum2Binary( intPart, decPart));
				}
				
				System.out.println();
			
			}
		}
		
		public static String intPart2Binary(long intPart) {
			String reversedStr = "";
			
			while (intPart > 0)
			{
				if (intPart % 2 == 1) 
					reversedStr += "1";
				else
					reversedStr += "0";
				intPart /= 2;
			}
			
			String str = "";
			for (int i = 0; i < reversedStr.length(); i++)
				str += reversedStr.charAt( reversedStr.length() - 1 - i); 
			
			return str;
		}
		
		public static String decPart2Binary(long decPart) {
			String str = "";
			int length = String.valueOf(decPart).length();
			Boolean ended = false;
			
			for (int i = 0; i < 16; i++) {
				decPart *= 2;
				if ( decPart > Math.pow( 10, length)) {
					decPart -= Math.pow( 10, length);
					str += 1;
				} else {
					str += 0;
				}
				
				if (decPart == 0)
				{
					ended = true;
					break;
				}
			}
			
			if (!ended)
				str += "...";
			
			return str;
		}
		
		public static String negativeNum2Binary(long intPart, long decPart) {
			
			String tempStr = "";
			int count = 0;
			
			intPart--;
			if (intPart2Binary( intPart).length() % 8 > 0)
				count = 8 - intPart2Binary( intPart).length() % 8;
			for (int i = 0; i < count; i++)
				tempStr += "0";
			String strIntPart = tempStr + intPart2Binary( intPart);
			
			String strDecPart = decPart2Binary(decPart);
			
			String invertedStrIntPart = "";
			for (int i = 0; i < strIntPart.length(); i++)
				if (strIntPart.charAt(i) == '0')
					invertedStrIntPart += "1";
				else
					invertedStrIntPart += "0";
			
			String invertedStrDecPart = "";
			for (int i = 0; i < strDecPart.length(); i++)
				if (strDecPart.charAt(i) == '0')
					invertedStrDecPart += "1";
				else
					invertedStrDecPart += "0";
			
			String fullInt = "";
			String fullDec = "";
			
			for (int i = 0; i < invertedStrIntPart.length(); i++)
				if (i % 8 != 0)
					fullInt += invertedStrIntPart.charAt(i);
				else
				{
					fullInt += " ";
					fullInt += invertedStrIntPart.charAt(i);
				}
			
			for (int i = 0; i < invertedStrDecPart.length(); i++)
				if (i % 8 != 0)
					fullDec += invertedStrDecPart.charAt(i);
				else
				{
					fullDec += " ";
					fullDec += invertedStrDecPart.charAt(i);
				}
			
			if (decPart != 0)
				return fullInt + "." + fullDec + "...";
			else
				return fullInt;
		}
		
		public static String binary2Base16(String binary) {
			
			String str = "";
			String tempStr = "";
			
			for (int i = 0; i < binary.length() / 4; i++) {
				if (!binary.substring(i*4, i*4 + 4).equals("0000"))
					tempStr += binary.substring(i*4, i*4 + 4);
			}
			binary = tempStr;
			
			for (int i = 0; i < binary.length() / 4; i++) {
				switch (binary.substring(i*4, i*4 + 4)) {
				case "0000" :
					str += "0";
					break;
				case "0001" :
					str += "1";
					break;
				case "0010" :
					str += "2";
					break;
				case "0011" :
					str += "3";
					break;
				case "0100" :
					str += "4";
					break;
				case "0101" :
					str += "5";
					break;
				case "0110" :
					str += "6";
					break;
				case "0111" :
					str += "7";
					break;
				case "1000" :
					str += "8";
					break;
				case "1001" :
					str += "9";
					break;
				case "1010" :
					str += "A";
					break;
				case "1011" :
					str += "B";
					break;
				case "1100" :
					str += "C";
					break;
				case "1101" :
					str += "D";
					break;
				case "1110" :
					str += "E";
					break;
				case "1111" :
					str += "F";
					break;
				}
			}
			
			return str;
		}
		
		
	}
