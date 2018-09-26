/* 
   Substitution Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Substition_Cipher {
	/* Character to integer map */
	private Map<Character, Integer> char_to_int = new HashMap<Character, Integer>();
	
	/* Integer to character map */
	private Map<Integer, Character> int_to_char = new HashMap<Integer, Character>();
	
	/* pi permutation map */
	private int pi[];
	
	/* pi_inv permutation map */
	private int pi_inv[];
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, int[] pi)
	{
		// Ensure pi only contains integers > 0 and < 26
		for(int i = 0; i < pi.length; i++)
		{
			if(pi[i] < 0 || pi[i] > 26 || pi.length != 26)
			{
				System.err.println("Permutation cipher pi must contain integers [0,25]!");
				return false;
			}
		}
		
		// Ensure pi does not skip values
		int[] pi_cpy = pi.clone();
		Arrays.sort(pi_cpy);
		for(int i = 0; i < 26; i++)
		{
			if(pi_cpy[i] != i)
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
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x.length(); i++)
		{
			int x_i = char_to_int.get(x.toLowerCase().charAt(i));
		    int y_i = pi[x_i];
		    
		    y += int_to_char.get(y_i);
		}
		
		return y;
	}
	
	/* d_k(y) = pi_inv(y_i) */
	public String decrypt(String y)
	{
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y.length(); i++)
		{
			int y_i = char_to_int.get(y.toLowerCase().charAt(i));
		    int x_i = pi_inv[y_i];
		    
		    x += int_to_char.get(x_i);
		}

		return x;
	}
	
	/* Substition_Cipher constructor */
	public Substition_Cipher(int[] input_pi) 
	{ 
		// Populate char_to_int and int_to_char maps
		for(int i = 0; i < 26; i ++)
		{
			char_to_int.put((char) ('a' + i), 0 + i);
			int_to_char.put(0 + i, (char) ('a' + i));
		}
		
		pi = input_pi;
		pi_inv = new int[pi.length];
		
		for(int i = 0; i < pi.length; i++)
			pi_inv[pi[i]] = i;
	}
	
	/* main method for execution */
	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.err.println("Please provide input argument:");
			System.err.println("m - plaintext/ciphertext string of characters");
			throw new IllegalArgumentException();
		}
		
		int[] pi = {23, 13, 24, 0, 7, 15, 14, 6, 25, 16, 22, 1, 19, 18, 5, 11, 17, 2, 21, 12, 20, 4, 10, 9, 3, 8};
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
		
		Substition_Cipher sc = new Substition_Cipher(pi);
		String y = sc.encrypt(m);
		System.out.println("Encrypted message: " + y);
		
		String x = sc.decrypt(y);
		System.out.println("Decrypted message: " + x);
	}
}
