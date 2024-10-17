package com.thinking.machines.RSA.keyGenerator.source;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyGenerator implements RSAKeyGeneratorInterface
{
private final int keySize;
private RSAKeyInterface keyPair[]=null;
public RSAKeyGenerator(int keySize)
{
this.keySize=keySize;
}

public RSAKeyInterface[] generateKeyPair()
{
try
{
//p and q are 2 prime number which will multipy to generate n(modulus value)
RSARandomNumberGeneratorInterface randomNumberGenerator=new RSARandomNumberGenerator(keySize/2,100);
BigInteger p=randomNumberGenerator.generate();
BigInteger q=randomNumberGenerator.generate();
BigInteger n=p.multiply(q);

//Now we will calculate the value of PHI

BigInteger phin=(p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));


// we will calculate the value of the first exponent e
SecureRandom random = new SecureRandom();
BigInteger e=new BigInteger(phin.bitLength(),random);

while(e.compareTo(phin)>=0 || e.compareTo(new BigInteger("1"))<=0 || !e.gcd(phin).equals(new BigInteger("1")))
{
e=new BigInteger(phin.bitLength(),random);
}

// after this loop we will have the first exponent e
//Now calculating other exponent 

BigInteger d=e.modInverse(phin);


// so now we have all the components to do the encryption/decryption p, q, n, PHI(n), e, d

//Now generating Keys 
keyPair=new RSAKeyInterface[2];
keyPair[0]=new RSAPublicKey(e,n);
keyPair[1]=new RSAPrivateKey(d,n);
}catch(Exception exception)
{
System.out.println("Exception in class RSAKeyGenerator --> generateKeyPair : "+exception);
}
return keyPair; 
}

}
