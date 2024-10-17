package com.thinking.machines.RSA.keyGenerator.source;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;

import java.math.BigInteger;

public class RSAPublicKey implements RSAKeyInterface
{
public final BigInteger publicExponent;
public final BigInteger modulo;
public RSAPublicKey(BigInteger publicExponent, BigInteger modulo)
{
this.publicExponent=new BigInteger(publicExponent.toString());
this.modulo=new BigInteger(modulo.toString());
}
public BigInteger getKeyExponent()
{
return new BigInteger(this.publicExponent.toString());
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
return this.publicExponent.bitLength();
}
public int getHash()
{
return 0;
}
public String toString()
{
return "{ "+publicExponent+", "+modulo+" }";
}
}
