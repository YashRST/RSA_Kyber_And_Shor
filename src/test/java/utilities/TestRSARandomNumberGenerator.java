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

import java.math.BigInteger;

public class TestRSARandomNumberGenerator
{
public void testFunction()
{
try
{
System.out.println("Inside TestRSARandomNumberGenerator");
RSARandomNumberGeneratorInterface generator=new RSARandomNumberGenerator(0,1);
BigInteger i=generator.generate();
System.out.println(i);
System.out.println(i.bitLength());
}catch(Exception e)
{
System.out.println("In TestRSARandomNumberGenerator: "+e.getMessage());
}
}

public static void main(String gg[])
{
TestRSARandomNumberGenerator t=new TestRSARandomNumberGenerator();
t.testFunction();
}
}