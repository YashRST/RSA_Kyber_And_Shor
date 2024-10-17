package com.thinking.machines.kyber.keyEncryption.source;
import com.thinking.machines.kyber.keyEncryption.interfaces.*;
import com.thinking.machines.kyber.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;

import java.security.*;
import java.util.*;
import org.bouncycastle.crypto.digests.*;
import org.ejml.simple.*;

public class KyberSharedSecretKeyGenerator implements KyberSharedSecretKeyGeneratorInterface
{
private SecureRandom secureRandom=null;
private DeserializerInterface deserializer=null;
private SerializerInterface serializer=null;
private MathematicalOperationsInterface mathematicalOperations=null;

public KyberSharedSecretKeyGenerator()
{
secureRandom=new SecureRandom();
deserializer=new Deserializer();
serializer=new Serializer();
mathematicalOperations=new MathematicalOperations();
}

public byte[][] generateSharedSecretKey(KyberKeyInterface key, KyberParameterInterface kyberParameters) throws KyberException
{
byte[][] sharedSecretKey=new byte[3][];

try
{
byte[] seed=new byte[kyberParameters.getNumberOfSymmetricBytes()];
int n=kyberParameters.getN();
int k=kyberParameters.getK();
int q=kyberParameters.getQ();
int eta=kyberParameters.getErrorDistribution();

secureRandom.nextBytes(seed);
SimpleMatrix matrixA=deserializer.deserializeMatrix(key.getMatrixA(), k,n);
matrixA.print();
SimpleMatrix deserializedKey=deserializer.deserializeMatrix(key.getKyberKey(), k,1);

SimpleMatrix message=null;
byte[] hash=new Hashing().SHAKEHash(seed,HashType.SHAKEHash128,k*n);
message=new SimpleMatrix(k,n);
System.out.println("--------------------------------------------------------------In Source: Printing Message");
for(int i=0; i<k; i++)
{
for(int j=0;j<n;j++)
{
int index=(i*n)+j;
message.set(i,j,(hash[index]&0xFF)%q);
}
}

System.out.println("--------------------------------------------------------------In Source: Printing Message");
message.print();
SimpleMatrix errorVector=mathematicalOperations.generateCenteredBinomialMatrix(1,n, eta/2);
System.out.println("--------------------------------------------------------------In Source: Printing ErrorVector");
errorVector.print();

SimpleMatrix u=matrixA.mult(errorVector.transpose());
SimpleMatrix v=deserializedKey.mult(errorVector).plus(message);
//System.out.println("--------------------------------------------------------------In Source: Printing U");
//u.print();
//System.out.println("--------------------------------------------------------------In Source: Printing V");
//v.print();

sharedSecretKey[0]=serializer.serializeMatrix(mathematicalOperations.modMatrix(u,kyberParameters.getQ()));
sharedSecretKey[1]=serializer.serializeMatrix(mathematicalOperations.modMatrix(v,kyberParameters.getQ()));
sharedSecretKey[2]=mathematicalOperations.createMatrixDigest(message,HashType.SHAKEHash256);
//System.out.println("********************************************************Serialized all the values******************************************************");
//System.out.println("***************************************************************************************************************************************");


}catch(Exception exception)
{
System.out.println("In KyberKeyEncryptor: "+exception.getMessage());
}
return sharedSecretKey;
}

}