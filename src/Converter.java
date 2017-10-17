	import java.util.Scanner;

	public class Converter {
		public static void main( String[] args) {
			
			while (true) {
				Scanner scanner = new Scanner( System.in);
				String str = scanner.next();
			
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
				
				if (!negative) {
					System.out.print( "0b");
					
					for (int i = 0; i < binaryIntPart.length(); i++)
						if (i % 4 != 0)
							System.out.print( binaryIntPart.charAt(i));
						else
						{
							System.out.print(" ");
							System.out.print( binaryIntPart.charAt(i));
						}
					
					if (decPart != 0) {
						System.out.print( ".");
					
						for (int i = 0; i < decPart2Binary( decPart).length(); i++)
							if (i % 4 != 0)
								System.out.print( decPart2Binary( decPart).charAt(i));
							else
							{
								System.out.print(" ");
								System.out.print( decPart2Binary( decPart).charAt(i));
							}
					}
					
				} else {
					
					System.out.print( "-0b" + negativeNum2Binary( intPart, decPart));
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
				if (i % 4 != 0)
					fullInt += invertedStrIntPart.charAt(i);
				else
				{
					fullInt += " ";
					fullInt += invertedStrIntPart.charAt(i);
				}
			
			for (int i = 0; i < invertedStrDecPart.length(); i++)
				if (i % 4 != 0)
					fullDec += invertedStrDecPart.charAt(i);
				else
				{
					fullDec += " ";
					fullDec += invertedStrDecPart.charAt(i);
				}
			
			if (decPart != 0)
				return fullInt + "." + fullDec;
			else
				return fullInt;
		}
		
		
	}
