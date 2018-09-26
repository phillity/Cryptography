/* 
   Hill Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.HashMap;
import java.util.Map;


public class Hill_Cipher {
	/* Character to integer map */
	private Map<Character, Integer> char_to_int = new HashMap<Character, Integer>();
	
	/* Integer to character map */
	private Map<Integer, Character> int_to_char = new HashMap<Integer, Character>();
	
	/* key matrix */
	private int K[];
	
	/* inverse key matrix */
	private int K_inv[];
	
	/* Greatest common divisor (gcd) helper method */
	private static int gcd(int a, int b) 
	{
		// Use Euclidean algorithm to find gcd
		if(b == 0) 
			return a;
		
		return gcd(b, a % b);
	}
	
	/* Multiplicative inverse helper function */
	private int inv(int a)
	{
		// Ensure a is coprime with 26 and multiplicative inverse exists
		if(gcd(a, 26) != 1)
		{
			System.err.println("Input is not coprime with 26! Therefore, input has no multiplicative inverse!");
			throw new IllegalArgumentException();
		}
		
		// Use extended Euclidean algorithm to find multiplicative inverse
		// ax + by = gcd(a,b)
		// ax + by = 1 mod m
		// ax = 1 mod m
		int m = 26, y = 0, x = 1;
		while(a > 1)
		{
			int q = a / m;
			int t = m;
			
			m = a % m;
			a = t;
			t = y;
			
			y = x - q * y;
			x = t;
		}
		x = (x + 26) % 26;
		
		return x;
	}
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, int[] K)
	{
		// Ensure K only contains four integers > 0 and < 26
		for(int i = 0; i < K.length; i++)
		{
			if(K[i] < 0 || K[i] > 26 || K.length != 4)
			{
				System.err.println("Hill cipher key must contain four integers [0,25]!");
				return false;
			}
		}
		
		// Ensure K is invertible
		// K is invertible if gcd(K_11*K_22-K_12*K_21, 26) == 1
		if(gcd(K[0]*K[3]-K[1]*K[2], 26) != 1)
		{
			System.err.println("Hill cipher key must be invertible!");
			return false;
		}
		
		// Ensure plaintext/ciphertext only contains characters
		if(!t.matches("[a-zA-Z]+"))
		{
			System.err.println("Hill cipher plaintext/ciphertext must only contain characters!");
			return false;
		}
		
		return true;
	}
	
	/* e_k(x) = x * K */
	public String encrypt(String x)
	{
		// Split x into substrings
		int cnt = (int)Math.ceil((double)x.length() / 2);
		String[] x_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < x.length(); i+=2)
		{
			x_substrings[idx++] = x.substring(i, Math.min(i + 2, x.length()));
		}
		
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x_substrings.length; i++)
		{
			int x_i = char_to_int.get(x_substrings[i].charAt(0));
			int x_j = char_to_int.get(x_substrings[i].charAt(1));
			
			int y_i = x_i * K[0] + x_j * K[2];
			y_i = (y_i + 26) % 26;
			int y_j = x_i * K[1] + x_j * K[3];
			y_j = (y_j + 26) % 26;
			
			y += int_to_char.get(y_i);
			y += int_to_char.get(y_j);
		}
		
		return y;
	}
	
	/* d_k(y) = y * K_inv */
	public String decrypt(String y)
	{
		// Split y into substrings
		int cnt = (int)Math.ceil((double)y.length() / 2);
		String[] y_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < y.length(); i+=2)
		{
			y_substrings[idx++] = y.substring(i, Math.min(i + 2, y.length()));
		}
		
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y_substrings.length; i++)
		{
			int y_i = char_to_int.get(y_substrings[i].charAt(0));
			int y_j = char_to_int.get(y_substrings[i].charAt(1));
			
			int x_i = y_i * K_inv[0] + y_j * K_inv[2];
			x_i = (x_i + 26) % 26;
			int x_j = y_i * K_inv[1] + y_j * K_inv[3];
			x_j = (x_j + 26) % 26;
			
			x += int_to_char.get(x_i);
			x += int_to_char.get(x_j);
		}
		
		return x;
	}
	
	/* Hill_Cipher constructor */
	public Hill_Cipher(int[] input_K) 
	{ 
		// Populate char_to_int and int_to_char maps
		for(int i = 0; i < 26; i ++)
		{
			char_to_int.put((char) ('a' + i), 0 + i);
			int_to_char.put(0 + i, (char) ('a' + i));
		}
		
		K = input_K;
		int K_det = inv((K[0]*K[3]-K[1]*K[2]) % 26);
		K_inv = new int[]{K[3], -K[1], -K[2], K[0]};
		for(int i = 0; i < K_inv.length; i++)
		{
			K_inv[i] = K_det * K_inv[i];
			K_inv[i] = (K_inv[i] + 26) % 26;
		}
	}
	
	/* main method for execution */
	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.err.println("Please provide input argument:");
			System.err.println("m - plaintext/ciphertext string of characters");
			throw new IllegalArgumentException();
		}
		
		//	  K = {K_11, K_12, K_21, K22}
		int[] K = {11, 8, 3, 7};
		String m = args[0];
		
		// Verify input is suitable
		if(!verify_input(m, K))
			throw new IllegalArgumentException();
		
		System.out.println("Input message: " + m);
		System.out.print("Input key: [");
		for(int i = 0; i < K.length-1; i++)
		{
			System.out.print(K[i] + ",");
		}
		System.out.println(K[K.length-1] + "]");
		
		while(m.length() % K.length != 0)
			m += 'a';
		
		Hill_Cipher hc = new Hill_Cipher(K);
		String y = hc.encrypt(m);
		System.out.println("Encrypted message: " + y);
		
		String x = hc.decrypt(y);
		System.out.println("Decrypted message: " + x);
	}
	
}
