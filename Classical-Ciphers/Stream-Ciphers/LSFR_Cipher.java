/* 
   LFSR (Linear Feedback Shift Register) Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

public class LSFR_Cipher {
	/* keystream K generated using z and c */
	private int[] K;
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, int[] z, int[] c)
	{
		// Ensure z and c only contain an equal amount of integers 
		if(z.length != c.length)
		{
			System.err.println("LFSR cipher key z and coefficients c must be equal length!");
			return false;
		}
		
		// Ensure z and c contain integers [0,1]
		for(int i = 0; i < z.length; i++)
		{
			if(z[i] < 0 || z[i] > 1 || c[i] < 0 || c[i] > 1)
			{
				System.err.println("LFSR cipher key z and coefficients c must contain integers [0,1]!");
				return false;
			}
		}
		
		// Ensure plaintext/ciphertext only contains 0 and 1
		for(int i = 0; i < t.length(); i++)
		{
			if(t.charAt(i) != '1' && t.charAt(i) != '0')
			{
				System.err.println("LFSR cipher plaintext/ciphertext must only contain 0 and 1!");
				return false;
			}
		}
			
		return true;
	}
	
	/* e_k(x) = x + K */
	public String encrypt(String x)
	{
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x.length(); i++)
		{
			int y_i = Character.getNumericValue(x.charAt(i)) + K[i];
			y += y_i % 2;
		}
		
		return y;
	}
	
	/* d_k(y) = y + K */
	public String decrypt(String y)
	{
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y.length(); i++)
		{
			int x_i = Character.valueOf(y.charAt(i)) + K[i];
			x += x_i % 2;
		}
		
		return x;
	}
	
	/* LSFR_Cipher constructor */
	public LSFR_Cipher(String t, int[] z, int[] c) 
	{ 
		K = new int[t.length()];
		
		for(int i = 0; i < z.length; i++)
		{
			K[i] = z[i];
		}
		
		for(int i = z.length; i < t.length(); i++)
		{
			int K_i = 0;
			for(int j = 0, idx = i-z.length; j < c.length; j++, idx++)
			{
				K_i += K[idx] * c[j];
			}
			K[i] = K_i % 2;
		}
	}
	
	/* main method for execution */
	public static void main(String[] args) 
	{
		if(args.length != 1)
		{
			System.err.println("Please provide input argument:");
			System.err.println("m - plaintext/ciphertext binary string");
			throw new IllegalArgumentException();
		}
		
		int[] z = {1, 0, 0, 0};
		int[] c = {1, 1, 0, 0};
		
		// Verify input is suitable
		if(!verify_input(args[0], z, c))
			throw new IllegalArgumentException();
		
		String m = args[0];
		
		System.out.println("Input message: " + m);
		System.out.print("Input key: [");
		for(int i = 0; i < z.length-1; i++)
		{
			System.out.print(z[i] + ",");
		}
		System.out.println(z[z.length-1] + "]");
		
		System.out.print("Input coefficient: [");
		for(int i = 0; i < z.length-1; i++)
		{
			System.out.print(c[i] + ",");
		}
		System.out.println(c[c.length-1] + "]");
		
		LSFR_Cipher lc = new LSFR_Cipher(m, z, c);
		String y = lc.encrypt(m);
		System.out.println("Encrypted message: " + y);
		
		String x = lc.decrypt(y);
		System.out.println("Decrypted message: " + x);
	}
}
