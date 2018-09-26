/* 
   Affine Cipher  
   Tyler Phillips 
   phillity@iu.edu 
*/ 

import java.util.HashMap;
import java.util.Map;

public class Affine_Cipher {
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
	
	/* Character to integer map */
	private Map<Character, Integer> char_to_int = new HashMap<Character, Integer>();
	
	/* Integer to character map */
	private Map<Integer, Character> int_to_char = new HashMap<Integer, Character>();
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, String a, String b)
	{
		// Ensure a is an integer
		try {
			 Integer.parseInt(a);
		}catch(NumberFormatException e){
			System.err.println("Affine cipher key a must be an integer [0,25] that is coprime with 26!");
			return false;
		}
		// Ensure b is an integer
		try {
			 Integer.parseInt(b);
		}catch(NumberFormatException e){
			System.err.println("Affine cipher key b must be an integer [0,25]!");
			return false;
		}
		
		// Ensure a is an integer [0,25] and is coprime with 26
		if(Integer.parseInt(a) < 0 || Integer.parseInt(a) > 25 || gcd(Integer.parseInt(a), 26) != 1)
		{
			System.err.println("Affine cipher key a must be an integer [0,25] that is coprime with 26!");
			return false;
		}
		
		// Ensure b is an integer [0,25]
		if(Integer.parseInt(b) < 0 || Integer.parseInt(b) > 25)
		{
			System.err.println("Affine cipher key b must be an integer [0,25]!");
			return false;
		}
		
		// Ensure plaintext/ciphertext only contains characters
		if(!t.matches("[a-zA-Z]+"))
		{
			System.err.println("Affine cipher plaintext/ciphertext must only contain characters!");
			return false;
		}
		
		return true;
	}
	
	/* e_k(x) = (a * x_i + b) mod 26 */
	public String encrypt(String x, int a, int b)
	{
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x.length(); i++)
		{
		    int x_i = char_to_int.get(x.toLowerCase().charAt(i));
		    int y_i = (a * x_i + b) % 26;
		    y_i = (y_i + 26) % 26;
		    
		    y += int_to_char.get(y_i);
		}
		
		return y;
	}
	
	/* d_k(y) = (a_inv * (y_i - b)) mod 26 */
	public String decrypt(String y, int a, int b)
	{
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y.length(); i++)
		{
		    int y_i = char_to_int.get(y.toLowerCase().charAt(i));
		    int x_i = (inv(a) * (y_i - b)) % 26;
		    x_i = (x_i + 26) % 26;
		    
		    x += int_to_char.get(x_i);
		}
		
		return x;
	}
	
	/* Affine_Cipher constructor */
	public Affine_Cipher()
	{
		// Populate char_to_int and int_to_char maps
		for(int i = 0; i < 26; i ++)
		{
			char_to_int.put((char) ('a' + i), 0 + i);
			int_to_char.put(0 + i, (char) ('a' + i));
		}
	}
	
	/* main method for execution */
	public static void main(String[] args) 
	{
		if(args.length != 3)
		{
			System.err.println("Please provide three input arguments:");
			System.err.println("m - plaintext/ciphertext string of characters");
			System.err.println("a - integer key [0,25] and is coprime with 26");
			System.err.println("b - integer key [0,25]");
			throw new IllegalArgumentException();
		}
		
		// Verify input is suitable
		if(!verify_input(args[0], args[1], args[2]))
			throw new IllegalArgumentException();
		
		String m = args[0];
		int a = Integer.parseInt(args[1]);
		int b = Integer.parseInt(args[2]);
		
		System.out.println("Input message: " + m);
		System.out.println("Input key a: " + a);
		System.out.println("Input key b: " + b);
		
		Affine_Cipher ac = new Affine_Cipher();
		String y = ac.encrypt(m, a, b);
		System.out.println("Encrypted message: " + y);
		
		String x = ac.decrypt(y, a, b);
		System.out.println("Decrypted message: " + x);
	}
}
