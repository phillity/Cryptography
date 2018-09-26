/* 
   Scytale Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

public class Scytale_Cipher {
	/* Char rod used for encryption/decrytpion */
	private char[][] rod;
	
	private void use_rod(String m, int K)
	{
		char[] t = m.toCharArray();
		
		int r = (int)Math.ceil((double)t.length / K);
		rod = new char[r][K];
		
		int idx = 0;
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < K; j++)
			{
				rod[i][j] = t[idx];
				idx++;
			}
		}
	}
	
	/* Helper method to verify input is suitable */
	private static boolean verify_input(String t, String K)
	{
		// Ensure key is an integer
		try {
			 Integer.parseInt(K);    
		}catch(NumberFormatException e){
			System.err.println("Scytale cipher key must be an integer greater than 0!");
			return false;
		}
		 
		// Ensure key is an integer > 0
		if(Integer.parseInt(K) < 0)
		{
			System.err.println("Scytale cipher key must be an integer greater than 0!");
			return false;
		}
		
		// Ensure plaintext/ciphertext only contains characters
		if(!t.matches("[a-zA-Z]+"))
		{
			System.err.println("Scytale cipher plaintext/ciphertext must only contain characters!");
			return false;
		}
		
		return true;
	}
	
	/* e_k(x) = x_ij */
	/* i = [0,ceil(x.length/K)] */
	/* j = [0,K] */
	public String encrypt(String x, int K)
	{
		// Build encryption rod
		use_rod(x, K);
		
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int j = 0; j < rod[0].length; j++)
		{
			for(int i = 0; i < rod.length; i++)
			{
				y += rod[i][j];
			}
		}
		
		return y;
	}
	
	/* d_k(y) = y_ij */
	/* i = [0,K] */
	/* j = [0,ceil(x.length/K)] */
	public String decrypt(String y, int K)
	{
		// Build decryption rod
		use_rod(y, K);
		
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int j = 0; j < rod[0].length; j++)
		{
			for(int i = 0; i < rod.length; i++)
			{	
				x += rod[i][j];
			}
		}
		
		return x;
	}
	
	/* Scytale_Cipher constructor */
	public Scytale_Cipher() { }
	
	/* main method for execution */
	public static void main(String[] args) {
		if(args.length != 2)
		{
			System.err.println("Please provide two input arguments:");
			System.err.println("m - plaintext/ciphertext string of characters");
			System.err.println("K - matrix column size");
			throw new IllegalArgumentException();
		}
		
		// Verify input is suitable
		if(!verify_input(args[0], args[1]))
			throw new IllegalArgumentException();
		
		String m = args[0];
		int K = Integer.parseInt(args[1]);
		
		System.out.println("Input message: " + m);
		System.out.println("Input key: " + K);
		
		while(m.length() % K != 0)
			m += '~';
		
		Scytale_Cipher sc = new Scytale_Cipher();
		String y = sc.encrypt(m, K);
		System.out.println("Encrypted message: " + y);
		
		K = y.length() / K;
		String x = sc.decrypt(y, K);
		System.out.println("Decrypted message: " + x);
	}
}
