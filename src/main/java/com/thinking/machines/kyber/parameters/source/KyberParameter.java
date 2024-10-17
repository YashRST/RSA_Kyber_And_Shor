package com.thinking.machines.kyber.parameters.source;

import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.exceptions.*;

public class KyberParameter implements KyberParameterInterface
{
private static final int n=4;
private static final int q=7;
private int k=0;
private int polynomialSize=0;
private static final int symBytes=32; //symBytes==number of bytes in symmetric key operations like hashing, and is used in generating seed.
private static final int errorDistribution=2;

public KyberParameter()
{}
public KyberParameter(int k) throws KyberException
{
if(k<=0 || k>4) throw new KyberException("Invalid instantiation of KyberParameters: Need a valid value of 0<k<5.");
this.k=k;
this.polynomialSize=n*Integer.BYTES;
}

public int getN()
{
return this.n;
}
public int getQ()
{
return this.q;
}
public int getK()
{
return this.k;
}
public int getPolynomialSize()
{
return this.polynomialSize;
}
public int getNumberOfSymmetricBytes()
{
return this.symBytes;
}
public int getErrorDistribution()
{
return this.errorDistribution;
}

public KyberParameterInterface getKyberParametersCopy() throws KyberException
{
KyberParameterInterface copyParameters;
try
{
copyParameters=new KyberParameter(this.k);
}catch(Exception exception)
{
throw new KyberException("Problem in KyberParametes -->  getKyberParametersCopy(): "+exception.getMessage());
}
return copyParameters;
}

}