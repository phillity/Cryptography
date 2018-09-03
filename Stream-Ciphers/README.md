# Stream Ciphers

### Autokey Cipher (Autokey_Cipher.java)
Arguments: 
```
m - plaintext/ciphertext string of characters
K - integer key [0,25]
```

Sample usage:
```
javac Autokey_Cipher.java
java Autokey_Cipher rendezvous 
```

Sample Output:
```
Input message: rendezvous
Input key: 8
Encrypted message: zvrqhdujim
Decrypted message: rendezvous
```

### Linear Feedback Shift Register (LFSR) Cipher (LFSR_Cipher.java)
Argument: 
```
m - plaintext/ciphertext binary string
```

Sample usage:
```
javac LFSR_Cipher.java
java LFSR_Cipher 1111111111111111111 
```

Sample Output:
```
Input message: 1111111111111111111
Input key: [1,0,0,0]
Input coefficient: [1,1,0,0]
Encrypted message: 0111011001010000111
Decrypted message: 1111111111111111111
