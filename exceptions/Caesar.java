public class Caesar
{
	public static void main(String[] args)
	{
		String mode, message;
		try
		{
			mode = args[0];
			message = args[1];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Usage: Caesar {encode|decode} message");
			return;
		}

		String result;
		switch(mode)
		{
			case "encode":
				result = encode(message);
				break;
			case "decode":
				result = decode(message);
				break;
			default:
				throw new UnsupportedOperationException("Unknown mode: " + mode);
		}

		System.out.println(result);
	}

	private static String encode(String message)
	{
		char[] charArray = message.toCharArray();
		for(int i = 0; i < charArray.length; i++)
		{
			charArray[i]++;
		}
		return new String(charArray);
	}

	private static String decode(String message)
	{
		char[] charArray = message.toCharArray();
		for(int i = 0; i < charArray.length; i++)
		{
			charArray[i]--;
		}
		return new String(charArray);
	}
}
