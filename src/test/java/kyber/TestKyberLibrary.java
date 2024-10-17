package kyber;
import org.junit.*;
import static org.junit.Assert.*;

import org.bouncycastle.pqc.crypto.crystals.kyber.*;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.*;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMExtractor;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMGenerator;


import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.spec.*;
import javax.crypto.*;
public class TestKyberLibrary 
{
@Test
public void testFunction()
{
try 
{
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
System.out.println("Kyber KEM Test");
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
System.out.print("\t KeySize \t|");
System.out.print("\t Public Key Size \t|");
System.out.print("\t Private Key Size \t|");
System.out.print("\t Key Generation Time \t|");
System.out.print("\t Encryption Time \t|");
System.out.print("\t Decryption Time \t|");
System.out.print("\t Total Time  \t|");
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
for(int keySize=512;keySize<=1024;keySize+=256)
{
long keyGenStart, keyGenEnd, encryStart, encryEnd, decryStart, decryEnd;
long keyGenTime=0, encryTime=0, decryTime=0, totalTime=0;
byte[] sharedSecret=null;
byte[] recoveredSharedSecret=null;
AsymmetricKeyParameter publicKey=null;
KyberPrivateKeyParameters privateKey=null;

System.out.print("(");
for(int i=0;i<5;i++)
{
System.out.print("#####");
keyGenStart=System.nanoTime();
KyberKeyPairGenerator generator = new KyberKeyPairGenerator();
SecureRandom secureRandom=CryptoServicesRegistrar.getSecureRandom();
KyberKeyGenerationParameters parameters;
if(keySize==512) parameters=new KyberKeyGenerationParameters(secureRandom, KyberParameters.kyber512);
else if(keySize==768) parameters=new KyberKeyGenerationParameters(secureRandom, KyberParameters.kyber768);
else parameters=new KyberKeyGenerationParameters(secureRandom, KyberParameters.kyber1024);
generator.init(parameters);
AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
publicKey = keyPair.getPublic();
privateKey = (KyberPrivateKeyParameters)keyPair.getPrivate();
keyGenEnd=System.nanoTime();

encryStart=System.nanoTime();
KyberKEMGenerator kemGen = new KyberKEMGenerator(new SecureRandom());
SecretWithEncapsulation swe = kemGen.generateEncapsulated(publicKey); 
byte[] ciphertext=swe.getEncapsulation();
sharedSecret=swe.getSecret();
encryEnd=System.nanoTime();


decryStart=System.nanoTime();
KyberKEMExtractor kemExt = new KyberKEMExtractor(privateKey);
recoveredSharedSecret = kemExt.extractSecret(ciphertext);
decryEnd=System.nanoTime();

keyGenTime+=((keyGenEnd-keyGenStart)/1000);
encryTime+=((encryEnd-encryStart)/1000);
decryTime+=((decryEnd-decryStart)/1000);
totalTime+=((decryEnd-keyGenStart)/1000);
}
System.out.println(")");
if(Arrays.equals(sharedSecret, recoveredSharedSecret)) System.out.print("\t ~"+ keySize +" \t\t|");
else System.out.print("\t *"+ keySize +" \t\t|");
System.out.print("\t\t "+publicKey.toString().length()+" \t\t|");
System.out.print("\t \t"+privateKey.toString().length()+" \t\t|");
System.out.print("\t\t"+keyGenTime/5+" \t\t|");
System.out.print("\t\t"+encryTime/5+" \t\t|");
System.out.print("\t\t"+decryTime/5+" \t\t|");
System.out.print("\t\t"+totalTime/5+" \t\t|");
System.out.println("\n");
}
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
}catch(Exception exception) 
{
System.out.println(exception);
}
}

public static void main(String[] args) throws Exception 
{
new TestKyberLibrary().testFunction();
}
}
