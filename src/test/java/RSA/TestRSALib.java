package RSA;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;
import com.thinking.machines.RSA.keyGenerator.source.*;
import com.thinking.machines.RSA.encryptor.interfaces.*;
import com.thinking.machines.RSA.encryptor.source.*;
import com.thinking.machines.RSA.decryptor.interfaces.*;
import com.thinking.machines.RSA.decryptor.source.*;


import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestRSALib
{
@Test
public void testFunction()
{
try
{
// Message to encrypt
String message = "Hello, this is a secret message!";
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
System.out.println("RSA Encryption Test");
System.out.println("Length of message : "+message.length());
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
System.out.print("\t Key Size \t|");
System.out.print("\t Public Key Size \t|");
System.out.print("\t Private Key Size \t|");
System.out.print("\t Key Generation Time \t|");
System.out.print("\t Encryption Time \t|");
System.out.print("\t Decryption Time \t|");
System.out.print("\t Total Time  \t|");
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");

for(int keySize=512;keySize<=8192;keySize+=keySize)
{
long keyGenStart, keyGenEnd, encryStart, encryEnd, decryStart, decryEnd;
long keyGenTime=0, encryTime=0, decryTime=0, totalTime=0;
String decryptedMessage="";
PublicKey publicKey=null;
PrivateKey privateKey=null;
System.out.print("(");
for(int i=0;i<5;i++)
{
System.out.print("#####");
// Generate a key pair
keyGenStart=System.nanoTime();
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
keyGen.initialize(keySize);
KeyPair keyPair = keyGen.generateKeyPair();
publicKey = keyPair.getPublic();
privateKey = keyPair.getPrivate();
keyGenEnd=System.nanoTime();

// Encrypt the message
encryStart=System.nanoTime();
String encryptedMessage=encrypt(message, publicKey);

encryEnd=System.nanoTime();

// Decrypt the message
decryStart=System.nanoTime();
decryptedMessage = decrypt(encryptedMessage, privateKey);
decryEnd=System.nanoTime();
keyGenTime+=((keyGenEnd-keyGenStart)/1000);
encryTime+=((encryEnd-encryStart)/1000);
decryTime+=((decryEnd-decryStart)/1000);
totalTime+=((decryEnd-keyGenStart)/1000);
}
System.out.println(")");
if(decryptedMessage.equals(message)) System.out.print("\t ~"+ keySize +" \t\t|");
else System.out.print("\t *"+ keySize +" \t\t|");
System.out.print("\t\t "+publicKey.getEncoded().length+" \t\t|");
System.out.print("\t \t"+privateKey.getEncoded().length+" \t\t|");
System.out.print("\t\t"+keyGenTime/5+" \t\t|");
System.out.print("\t\t"+encryTime/5+" \t\t|");
System.out.print("\t\t"+decryTime/5+" \t\t|");
System.out.print("\t\t"+totalTime/5+" \t\t|");
System.out.println("\n");
}
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
}catch(Exception e)
{
e.printStackTrace();
}

try
{
String message="Hello, this is a secret message!";

for(int keySize=512;keySize<=8192;keySize+=keySize)
{
long keyGenStart, keyGenEnd, encryStart, encryEnd, decryStart, decryEnd;
long keyGenTime=0, encryTime=0, decryTime=0, totalTime=0;
String decryptedMessage="";
RSAKeyInterface publicKey=null;
RSAKeyInterface privateKey=null;
System.out.print("(");
for(int i=0;i<5;i++)
{
System.out.print("#####");
keyGenStart=System.nanoTime();
RSAKeyGeneratorInterface keyGenerator=new RSAKeyGenerator(keySize);
RSAKeyInterface[] keyPair=keyGenerator.generateKeyPair();
publicKey=keyPair[0];
privateKey=keyPair[1];
keyGenEnd=System.nanoTime();

encryStart=System.nanoTime();
RSAEncryptorInterface encryptor=new RSAEncryptor();
String encryptedMessage=encryptor.encrypt(message,publicKey);
encryEnd=System.nanoTime();

decryStart=System.nanoTime();
RSADecryptorInterface decryptor=new RSADecryptor();
decryptedMessage=decryptor.decrypt(encryptedMessage,privateKey);
decryEnd=System.nanoTime();

keyGenTime+=((keyGenEnd-keyGenStart)/1000);
encryTime+=((encryEnd-encryStart)/1000);
decryTime+=((decryEnd-decryStart)/1000);
totalTime+=((decryEnd-keyGenStart)/1000);
}
System.out.println(")");
if(decryptedMessage.equals(message)) System.out.print("\t ~"+ keySize +" \t\t|");
else System.out.print("\t *"+ keySize +" \t\t|");
System.out.print("\t\t"+publicKey.getKeyExponentSize()+" \t\t|");
System.out.print("\t \t"+privateKey.getKeyExponentSize()+" \t\t|");
System.out.print("\t\t"+keyGenTime/5+" \t\t|");
System.out.print("\t\t"+encryTime/5+" \t\t|");
System.out.print("\t\t"+decryTime/5+" \t\t|");
System.out.print("\t\t"+totalTime/5+" \t\t|");
System.out.println("\n");
}
}catch(Exception e)
{
System.out.println("In TestRSARandomNumberGenerator: "+e.getMessage());
}



}

public static String encrypt(String message, PublicKey publicKey) throws Exception 
{
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.ENCRYPT_MODE, publicKey);
byte[] encryptedBytes = cipher.doFinal(message.getBytes());
return Base64.getEncoder().encodeToString(encryptedBytes);
}

public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception
{
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.DECRYPT_MODE, privateKey);
byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
return new String(decryptedBytes);
}

public static void main(String[] args)
{
TestRSALib obj=new TestRSALib();
obj.testFunction();
}
}
