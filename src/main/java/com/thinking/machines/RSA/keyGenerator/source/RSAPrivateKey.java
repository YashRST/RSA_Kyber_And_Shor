package com.thinking.machines.RSA.keyGenerator.source;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;

import java.math.BigInteger;

public class RSAPrivateKey implements RSAKeyInterface
{
private final BigInteger privateExponent;
private final BigInteger modulo;

//Constructor
protected RSAPrivateKey(BigInteger privateExponent, BigInteger modulo)
{
this.privateExponent=new BigInteger(privateExponent.toString());
this.modulo=new BigInteger(modulo.toString());
}

public BigInteger getKeyExponent()
{
return new BigInteger(this.privateExponent.toString());
}
public BigInteger getKeyModulo()
{
return new BigInteger(this.modulo.toString());
}

public int getKeyModuloSize()
{
return this.modulo.bitLength();
}
public int getKeyExponentSize()
{
return this.privateExponent.bitLength();
}
public int getHash()
{
return 0;
}
public String toString()
{
return "{ "+privateExponent+", "+modulo+" }";
}

}
