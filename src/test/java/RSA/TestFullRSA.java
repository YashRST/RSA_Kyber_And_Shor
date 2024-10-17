package RSA;

import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;
import com.thinking.machines.RSA.keyGenerator.source.*;
import com.thinking.machines.RSA.encryptor.interfaces.*;
import com.thinking.machines.RSA.encryptor.source.*;
import com.thinking.machines.RSA.decryptor.interfaces.*;
import com.thinking.machines.RSA.decryptor.source.*;

import org.junit.*;
import static org.junit.Assert.*;
import org.ejml.simple.*;
import org.ejml.simple.SimpleBase;
import java.security.*;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.math.BigInteger;

public class TestFullRSA
{
//@Test
public void testFunction()
{
try
{
String testString="Hello world";

System.out.println("\n------------------------------Key Generation starts---------------------------------------");
RSAKeyGeneratorInterface keyGenerator=new RSAKeyGenerator(512);
RSAKeyInterface[] keyPair=keyGenerator.generateKeyPair();
RSAKeyInterface publicKey=keyPair[0];
RSAKeyInterface privateKey=keyPair[1];
System.out.println("publicKey: "+publicKey);
System.out.println("Private key: "+privateKey);
System.out.println("Comparing Key Exponent are: "+publicKey.getKeyExponent().equals(privateKey.getKeyExponent()));
System.out.println("\n------------------------------Key Generation Finished---------------------------------------");


System.out.println("\n------------------------------Encryption Start---------------------------------------");
RSAEncryptorInterface encryptor=new RSAEncryptor();
String encryptedMsg=encryptor.encrypt(testString,publicKey);
System.out.println("\nEncrypted Message: "+encryptedMsg);
System.out.println("\n------------------------------Encryption Finished---------------------------------------");


System.out.println("\n------------------------------decryption Start---------------------------------------");
RSADecryptorInterface decryptor=new RSADecryptor();
String decryptedMsg=decryptor.decrypt(encryptedMsg,privateKey);
System.out.println("\n Decrypted Message: "+decryptedMsg);
System.out.println("\n------------------------------decryption Finished---------------------------------------");



}catch(Exception e)
{
System.out.println("In TestRSARandomNumberGenerator: "+e.getMessage());
}
}

public static void main(String gg[])
{
TestFullRSA t=new TestFullRSA();
t.testFunction();
}
}