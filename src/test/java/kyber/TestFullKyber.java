package kyber;

import com.thinking.machines.kyber.exceptions.*;
import com.thinking.machines.kyber.keyGeneration.interfaces.*;
import com.thinking.machines.kyber.keyGeneration.source.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;
import com.thinking.machines.kyber.keyEncryption.source.*;
import com.thinking.machines.kyber.keyEncryption.interfaces.*;

import org.junit.*;
import static org.junit.Assert.*;
import org.bouncycastle.crypto.digests.*;
import org.ejml.simple.*;
import org.ejml.simple.SimpleBase;
import java.security.*;
import java.util.*;
import java.io.*;

public class TestFullKyber
{
//@Test
public void generatingKey()
{
KyberParameterInterface kyberParameter;
KyberKeyGeneratorInterface keyGenerator;
KyberKeyPairInterface kyberKeyPair=null;
KyberKeyInterface publicKey,privateKey;
DeserializerInterface deserializer=null;
MathematicalOperations mathematicalOperations=null;

try
{
deserializer=new Deserializer();
kyberParameter=new KyberParameter(2);
mathematicalOperations=new MathematicalOperations();
int n=kyberParameter.getN();
int q=kyberParameter.getQ();
int k=kyberParameter.getK();
int symBytes=kyberParameter.getNumberOfSymmetricBytes();
int eta=kyberParameter.getErrorDistribution();

System.out.println("\n______________________________________");
System.out.println("Generating the Key pair");
System.out.println("______________________________________");

keyGenerator=KyberKeyGenerator.getInstance(kyberParameter);
kyberKeyPair=keyGenerator.generateKeyPair();
publicKey=kyberKeyPair.getPublicKey();
privateKey=kyberKeyPair.getPrivateKey();

byte[] publicKeyBytes=publicKey.getKyberKey();
byte[] privateKeyBytes=privateKey.getKyberKey();
byte[] matrixABytes=publicKey.getMatrixA();

System.out.println("______________________________________");
System.out.println("Key Generation Complete");
System.out.println("\n______________________________________");


SimpleMatrix pub=deserializer.deserializeMatrix(publicKeyBytes,k,1);
SimpleMatrix pri=deserializer.deserializeMatrix(privateKeyBytes,n,1);
SimpleMatrix mA=deserializer.deserializeMatrix(matrixABytes,k,n);

System.out.println("--------------------------------------------------------------In Test Case: Printing MatrixA");
mA.print();
System.out.println("--------------------------------------------------------------In Test Case: Printing publicKey");
pub.print();
System.out.println("--------------------------------------------------------------In Test Case: Printing privateKey");
pri.print();
System.out.println("--------------------------------------------------------------");



System.out.println("\n______________________________________");
System.out.println("                 Starting Shared Key Generation");
System.out.println("\n______________________________________");

KyberSharedSecretKeyGeneratorInterface sharedSecretGenerator=new KyberSharedSecretKeyGenerator();

byte[][] sharedSecret=sharedSecretGenerator.generateSharedSecretKey(publicKey, kyberParameter); 

SimpleMatrix u = deserializer.deserializeMatrix(sharedSecret[0],k,1);
System.out.println("--------------------------------------------------------------In Test Case: Printing U");
u.print();
System.out.println("--------------------------------------------------------------In Test Case: Printing V");

SimpleMatrix v = deserializer.deserializeMatrix(sharedSecret[1],k,n);
v.print();
System.out.println("--------------------------------------------------------------");

SimpleMatrix m1 = v.minus(u.mult(pri.transpose()));
m1 = mathematicalOperations.modMatrix(m1, q);
System.out.println("--------------------------------------------------------------In Test Case: Printing recovered Message");
m1.print();

//TRYING TO WRITE THE DECAPSULATION CODE TO RECOVER THE MESSAGE AND HENCE SHARED SECREET
System.out.println("********************************************************Removing the noise from the MESSAGE HERE******************************************************");
double halfQ = q/2.0;
double threshold = eta/2.0;
 SimpleMatrix cleanedMatrix = new SimpleMatrix(m1.numRows(), m1.numCols());
for (int i = 0; i < m1.numRows(); i++) 
{
for (int j = 0; j < m1.numCols(); j++) 
{
double value = m1.get(i, j);
if (value < 0) value += q;
cleanedMatrix.set(i, j, (value < q / 2) ? 0 : 1);
}
}
System.out.println("***************************************************************************************************************************************");
m1=cleanedMatrix;
m1.print();

byte[] d=mathematicalOperations.createMatrixDigest(m1,HashType.SHAKEHash256);
System.out.println("NewHhash: length: "+Arrays.toString(d).length()+"\n");//+Arrays.toString(sharedSecret[2]));

if(Arrays.toString(d).equals(Arrays.toString(sharedSecret[2]))) System.out.println("YYYYYYaaaaaaaaaaaaaaaaaahhhhhhhhhooooooooooooooooooooooooo");
else System.out.println("Ooo NooooooooooOOOOOOoooooooooo");



}catch(Exception e)
{
System.out.println(e.getMessage());
}
}

public static void main(String gg[])
{
try
{
System.out.println("Starting the test case. In main()");
TestFullKyber t=new TestFullKyber();
System.out.println("Calling the function for test");
t.generatingKey();
}catch(Exception e)
{

}
}

}


