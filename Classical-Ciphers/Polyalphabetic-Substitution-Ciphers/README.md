# Polyalphabetic Substitution Ciphers

### Vigenere Cipher (Vigenere_Cipher.java)
Arguments: 
```
m - plaintext/ciphertext string of characters
K - key string of characters
```

Sample usage:
```
javac Vigenere_Cipher.java
java Vigenere_Cipher thiscryptosystemisnotsecure 
```

Sample Output:
```
Input message: thiscryptosystemisnotsecure
Input key: cipher
Encrypted message: vpxzgiaxivwpubttmjpwizitwzt
Decrypted message: thiscryptosystemisnotsecure
```

### Hill Cipher (Hill_Cipher.java)
Argument: 
```
m - plaintext/ciphertext string of characters
```

Sample usage:
```
javac Hill_Cipher.java
java Hill_Cipher july 
```

Sample Output:
```
Input message: july
Input key: [11,8,3,7]
Encrypted message: delw
Decrypted message: july
```
