package com.thinking.machines.utilities.source;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.exceptions.*;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSARandomNumberGenerator implements RSARandomNumberGeneratorInterface
{
//declared two final variable to store size of number to be generated and the certainty value
private final int bitLength;
private final int certainty;

//default constructor of the class, this sets the value of 2 final variables
public RSARandomNumberGenerator(int bitLength, int certainty)
{
this.bitLength=bitLength;
this.certainty=certainty;
}

//methord to generate random number
public BigInteger generate() throws UtilitiesException
{
BigInteger primeNumber=null;

try
{
//validating the values of bitLength and certainty
if(bitLength<=0) throw new UtilitiesException("Bit Length cannot be Zero(0), Invalid RSARandomNumberGenerator, please make a new object of RSARandomNumberGenerator with valid values");
if(certainty<0) throw new UtilitiesException("Certainty cannot be -ve, Invalid RSARandomNumberGenerator, please make a new object of RSARandomNumberGenerator with valid values");

SecureRandom secureRandom= new SecureRandom();
primeNumber=new BigInteger(bitLength, certainty, secureRandom);
while (!primeNumber.isProbablePrime(certainty))
{
primeNumber = new BigInteger(bitLength, certainty, secureRandom);
}

}catch(Exception exception)
{
throw new UtilitiesException("In RSARandomNumberGenerator: "+exception);
}

return primeNumber;
}

}
