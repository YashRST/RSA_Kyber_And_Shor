package com.thinking.machines.kyber.keyGeneration.source;
import com.thinking.machines.kyber.keyGeneration.interfaces.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;
import com.thinking.machines.kyber.exceptions.*;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;
import com.thinking.machines.utilities.exceptions.*;

import org.bouncycastle.crypto.digests.*;
import org.ejml.simple.*;
import org.ejml.simple.SimpleBase;
import java.security.*;
import java.util.*;

public class KyberKeyGenerator implements KyberKeyGeneratorInterface
{
private static KyberKeyGeneratorInterface kyberKeyGenerator=null;

private KyberParameterInterface kyberParameters=null;
private SecureRandom secureRandom=null;
private HashingInterface hashing=null;
private MathematicalOperationsInterface mathematicalOperations=null;
private SerializerInterface serializer=null;
private byte[] serializedMatrixA=null;
private byte[] serializedPublicKey=null;
private byte[] serializedPrivateKey=null;
private int n=0,q=0,k=0;

private KyberKeyGenerator(KyberParameterInterface kyberParameters) throws KyberException
{
try
{
this.kyberParameters=kyberParameters.getKyberParametersCopy();
this.secureRandom=new SecureRandom();
this.hashing=new Hashing();
this.mathematicalOperations=new MathematicalOperations();
this.serializer=new Serializer();
this.n=this.kyberParameters.getN();
this.q=this.kyberParameters.getQ();
this.k=this.kyberParameters.getK();
}catch(KyberException exception)
{
throw exception;
}
}

public static KyberKeyGeneratorInterface getInstance(KyberParameterInterface kyberParameters) throws KyberException
{
if(kyberParameters==null) throw new KyberException("Invalid instantiation: Need to pass an argument of type KyberParameterInterface, found null");
if(kyberKeyGenerator==null) kyberKeyGenerator=new KyberKeyGenerator(kyberParameters);
return kyberKeyGenerator;
}

public KyberKeyPairInterface generateKeyPair() throws KyberException
{
try
{
//Step1: Generating Uniform random seed
byte[] seed=new byte[this.kyberParameters.getNumberOfSymmetricBytes()];
secureRandom.nextBytes(seed);

//Step2: Exanding seed to Matrix A, Secret s and Error e.
//Generating Matrix A using SHAKEDigest 128

byte[] hash=hashing.SHAKEHash(seed,HashType.SHAKEHash128,k*n);

SimpleMatrix matrixA=new SimpleMatrix(k,n);
for(int i=0; i<k; i++)
{
for(int j=0;j<n;j++)
{
int index=(i*n)+j;
matrixA.set(i,j,(hash[index]&0xFF)%this.q);
}
}

serializedMatrixA=serializer.serializeMatrix(matrixA);
//Generating the Secret s and Error e vectors
SimpleMatrix secretVectorS=mathematicalOperations.generateCenteredBinomialMatrix(this.n,1,kyberParameters.getErrorDistribution());
SimpleMatrix errorVectorE=mathematicalOperations.generateCenteredBinomialMatrix(this.k,1,kyberParameters.getErrorDistribution()/2);

//computing the public Key
SimpleMatrix publicKey=matrixA.mult(secretVectorS);
publicKey=publicKey.plus(errorVectorE);
// Convert SimpleMatrix to a 2D array and applying modulo operation
publicKey=mathematicalOperations.modMatrix(publicKey,this.q);
serializedPublicKey=serializer.serializeMatrix(publicKey);
serializedPrivateKey=serializer.serializeMatrix(secretVectorS);
}catch(Exception e)
{
System.out.println("Error in KyberKeyGeneration class: \n"+e.getMessage());
}
return new KyberKeyPair(new KyberPublicKey(serializedPublicKey,serializedMatrixA), new KyberPrivateKey(serializedPrivateKey,serializedMatrixA));
}

}


