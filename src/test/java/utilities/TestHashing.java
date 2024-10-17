package utilities;

import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;

import org.junit.*;
import static org.junit.Assert.*;
import org.ejml.simple.*;
import org.ejml.simple.SimpleBase;
import java.security.*;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.math.BigInteger;

public class TestHashing
{

public void testFunction()
{
try
{
System.out.println("1. Inside TestHashing");
HashingInterface hasher=new Hashing();
String testString="Hello world";
System.out.println("1. Bytes of "+testString+" :"+Arrays.toString(testString.getBytes(StandardCharsets.UTF_8))+" , Length: "+testString.getBytes().length);

System.out.println("\nTest for SHA 256 start");
byte[] i=hasher.SHAHash(null, HashType.SHAHash256);
System.out.println("1. "+i.length);
System.out.println("1. "+new String(i,StandardCharsets.UTF_8));
System.out.println("Test for SHA 256 ends here \n");

System.out.println("Test for SHA 512 starts here");
i=hasher.SHAHash(testString.getBytes(StandardCharsets.UTF_8), HashType.SHAHash512);
System.out.println("1. "+i.length);
System.out.println("1. "+new String(i,StandardCharsets.UTF_8));
System.out.println("Test for SHA 512 ennds here\n");


System.out.println("Test for SHAKE 128 starts here");
i=hasher.SHAKEHash(testString.getBytes(StandardCharsets.UTF_8), HashType.SHAKEHash128,128);
System.out.println("1. "+i.length);
System.out.println("1. "+new String(i,StandardCharsets.UTF_8));
System.out.println("Test for SHAKE 128 ends here\n");


System.out.println("Test for SHAKE 256 starts here");
i=hasher.SHAKEHash(testString.getBytes(StandardCharsets.UTF_8), HashType.SHAKEHash256,256);
System.out.println("1. "+i.length);
System.out.println("1. "+new String(i,StandardCharsets.UTF_8));
System.out.println("Test for SHAKE 256 emds here\n");


}catch(Exception e)
{
System.out.println("In TestRSARandomNumberGenerator: "+e.getMessage());
}
}

public static void main(String gg[])
{
TestHashing t=new TestHashing();
t.testFunction();
}
}