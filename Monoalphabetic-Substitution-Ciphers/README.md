# Monoalphabetic Substitution Ciphers 

### Shift (Caesar) Cipher (Shift_Cipher.java)
Arguments: 
```
m - plaintext/ciphertext string of characters
K - integer key [0,25]
```

Sample usage:
```
javac Shift_Cipher.java
java Shift_Cipher astitchintimesavesnine 9
```

Sample Output:
```
Input message: astitchintimesavesnine
Input key: 9
Encrypted message: jbcrclqrwcrvnbjenbwrwn
Decrypted message: astitchintimesavesnine
```

### Substitution Cipher (Substitution_Cipher.java)
Argument: 
```
m - plaintext/ciphertext string of characters
```

Sample usage:
```
javac Substitution_Cipher.java
java Substitution_Cipher cryptography 
```

Sample Output:
```
Input message: cryptography
Input key: [23,13,24,0,7,15,14,6,25,16,22,1,19,18,5,11,17,2,21,12,20,4,10,9,3,8]
Encrypted message: ycdlmfocxlgd
Decrypted message: cryptography
```
