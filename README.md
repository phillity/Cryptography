# Classical-Ciphers

## Monoalphabetic Substitution Ciphers 

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


### Affine Cipher (Affine_Cipher.java)
Arguments: 
```
m - plaintext/ciphertext string of characters
a - integer key [0,25] and is coprime with 26
b - integer key [0,25]
```

Sample usage:
```
javac Affine_Cipher.java
java Affine_Cipher hot 
```

Sample Output:
```
Input message: hot
Input key a: 7
Input key b: 3
Encrypted message: axg
Decrypted message: hot
```

## Polyalphabetic Substitution Ciphers

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

## Tranposition Ciphers

### Permutation Cipher (Permutation_Cipher.java)
Argument: 
```
m - plaintext/ciphertext string of characters
```

Sample usage:
```
javac Permutation_Cipher.java
java Permutation_Cipher shesellsseashellsbythesea 
```

Sample Output:
```
Input message: shesellsseashellsbythesea
Input key: [3,5,1,6,4,2]
Encrypted message: eeslshsalseslshblehsyeet~~a~~~
Decrypted message: shesellsseashellsbythesea~~~~~
```

### Scytale Cipher (Scytale_Cipher.java)
Arguments: 
```
m - plaintext/ciphertext string of characters
K - matrix column size
```

Sample usage:
```
javac Scytale_Cipher.java
java Scytale_Cipher thisisatest 
```

Sample Output:
```
Input message: thisisatest
Input key: 5
Encrypted message: tstha~it~se~is~
Decrypted message: thisisatest~~~~
```

## Stream Ciphers

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
```

## Cryptanalysis Tools

### Frequency Analysis (Frequency_Analysis.java)

### Kasiski Test (Kasiski_Test.java)

### Index of Coincidence (Index_of_Coincidence.java)
