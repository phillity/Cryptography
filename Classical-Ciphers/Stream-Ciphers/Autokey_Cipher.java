/* 
   Autokey Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.HashMap;
import java.util.Map;

public class Autokey_Cipher {
	/* Character to integer map */
	private Map<Character, Integer> char_to_int = new HashMap<Character, Integer>();
	
	/* Integer to character map */
	private Map<Integer, Character> int_to_char = new HashMap<Integer, Character>();
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, String K)
	{
		// Ensure key is an integer
		try {
			 Integer.parseInt(K);    
		}catch(NumberFormatException e){
			System.err.println("Autokey cipher key must be an integer from 0 to 25!");
			return false;
		}
		 
		// Ensure key is an integer [0,25]
		if(Integer.parseInt(K) < 0 || Integer.parseInt(K) > 25)
		{
			System.err.println("Autokey cipher key must be an integer from 0 to 25!");
			return false;
		}
		
		// Ensure plaintext/ciphertext only contains characters
		if(!t.matches("[a-zA-Z]+"))
		{
			System.err.println("Autokey cipher plaintext/ciphertext must only contain characters!");
			return false;
		}
		
		return true;
	}
	
	/* e_k(x) = x_i + K_j mod 26 */
	/* K_j = K for j = 0 */
	/* K_j = x_(i-1) for j >= 1 */
	public String encrypt(String x, int K)
	{
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		int x_i_prev = -1;
		for(int i = 0; i < x.length(); i++)
		{
			int K_j = -1;
		    if(i == 0)
		    	K_j = K;
		    else
		    	K_j = x_i_prev;
			
		    int x_i = char_to_int.get(x.toLowerCase().charAt(i));
		    int y_i = (x_i + K_j) % 26;
		    y_i = (y_i + 26) % 26;
		    
		    y += int_to_char.get(y_i);
		    
		    x_i_prev = x_i;
		}
		
		return y;
	}
	
	/* d_k(y) = y_i - K_j mod 26 */
	/* K_j = K for j = 0 */
	/* K_j = y_(i-1) for j >= 1 */
	public String decrypt(String y, int K)
	{
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		int x_i_prev = -1;
		for(int i = 0; i < y.length(); i++)
		{
			int K_j = -1;
		    if(i == 0)
		    	K_j = K;
		    else
		    	K_j = x_i_prev;
			
		    int y_i = char_to_int.get(y.toLowerCase().charAt(i));
		    int x_i = (y_i - K_j) % 26;
		    x_i = (x_i + 26) % 26;
		    
		    x += int_to_char.get(x_i);
		    
		    x_i_prev = x_i;
		}
		
		return x;
	}
	
	/* Autokey_Cipher constructor */
	public Autokey_Cipher()
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
		if(args.length != 2)
		{
			System.err.println("Please provide two input arguments:");
			System.err.println("m - plaintext/ciphertext string of characters");
			System.err.println("K - integer key [0,25]");
			throw new IllegalArgumentException();
		}
		
		// Verify input is suitable
		if(!verify_input(args[0], args[1]))
			throw new IllegalArgumentException();
		
		String m = args[0];
		int K = Integer.parseInt(args[1]);
		
		System.out.println("Input message: " + m);
		System.out.println("Input key: " + K);
		
		Autokey_Cipher akc = new Autokey_Cipher();
		String y = akc.encrypt(m, K);
		System.out.println("Encrypted message: " + y);
		
		String x = akc.decrypt(y, K);
		System.out.println("Decrypted message: " + x);
	}
}
