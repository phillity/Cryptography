# Transposition Ciphers

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
