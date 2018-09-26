/* 
   Permutation Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.Arrays;

public class Permutation_Cipher {
	/* pi permutation map */
	private int pi[];
	
	/* pi_inv permutation map */
	private int pi_inv[];
	
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, int[] pi)
	{
		// Ensure pi only contains integers > 0 and < t.length()
		for(int i = 0; i < pi.length; i++)
		{
			if(pi[i] < 0 || pi[i] > t.length())
			{
				System.err.println("Permutation cipher pi must contain integers greater than 0!");
				return false;
			}
		}
		
		// Ensure pi does not skip values
		int[] pi_cpy = pi.clone();
		Arrays.sort(pi_cpy);
		for(int i = 1; i < pi_cpy.length+1; i++)
		{
			if(pi_cpy[i-1] != i)
			{
				System.err.println("Permutation cipher pi must not skip values!");
				return false;
			}
		}
		
		// Ensure plaintext/ciphertext only contains characters
		if(!t.matches("[a-zA-Z]+"))
		{
			System.err.println("Permutation cipher plaintext/ciphertext must only contain characters!");
			return false;
		}
		
		return true;
	}
	
	/* e_k(x) = pi(x_i) */
	public String encrypt(String x)
	{
		// Split x into substrings
		int cnt = (int)Math.ceil((double)x.length() / pi.length);
		String[] x_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < x.length(); i+=pi.length)
		{
			x_substrings[idx++] = x.substring(i, Math.min(i + pi.length, x.length()));
		}
		
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x_substrings.length; i++)
		{
			for(int j = 0; j < x_substrings[0].length(); j++)
			{
				y += x_substrings[i].charAt(pi[j]-1);
			}
		}
		
		return y;
	}
	
	/* d_k(y) = pi_inv(y_i) */
	public String decrypt(String y)
	{
		// Split y into substrings
		int cnt = (int)Math.ceil((double)y.length() / pi_inv.length);
		String[] y_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < y.length(); i+=pi_inv.length)
		{
			y_substrings[idx++] = y.substring(i, Math.min(i + pi_inv.length, y.length()));
		}
		
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y_substrings.length; i++)
		{
			for(int j = 0; j < y_substrings[0].length(); j++)
			{
				x += y_substrings[i].charAt(pi_inv[j]-1);
			}
		}

		return x;
	}
	
	/* Permutation_Cipher constructor */
	public Permutation_Cipher(int[] input_pi) 
	{ 
		pi = input_pi;
		pi_inv = new int[pi.length];
		
		for(int i = 0; i < pi.length; i++)
			pi_inv[pi[i]-1] = i+1;
	}
	
	/* main method for execution */
	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.err.println("Please provide input argument:");
			System.err.println("m - plaintext/ciphertext string of characters");
			throw new IllegalArgumentException();
		}
		
		int[] pi = {3, 5, 1, 6, 4, 2};
		String m = args[0];
		
		// Verify input is suitable
		if(!verify_input(m, pi))
			throw new IllegalArgumentException();
		
		System.out.println("Input message: " + m);
		System.out.print("Input key: [");
		for(int i = 0; i < pi.length-1; i++)
		{
			System.out.print(pi[i] + ",");
		}
		System.out.println(pi[pi.length-1] + "]");
		
		while(m.length() % pi.length != 0)
			m += '~';
		
		Permutation_Cipher pc = new Permutation_Cipher(pi);
		String y = pc.encrypt(m);
		System.out.println("Encrypted message: " + y);
		
		String x = pc.decrypt(y);
		System.out.println("Decrypted message: " + x);
	}
}
