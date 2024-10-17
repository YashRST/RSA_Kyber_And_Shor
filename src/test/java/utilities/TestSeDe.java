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

public class TestSeDe
{

public void testFunction()
{
try
{
System.out.println("1. Testing Serialize and deserialize");
SerializerInterface serializer=new Serializer();
DeserializerInterface deserializer=new Deserializer();
MathematicalOperationsInterface mo=new MathematicalOperations();
SimpleMatrix s=mo.generateCenteredBinomialMatrix(5,5,50);

for(int i=0;i<s.numRows();i++)
{
for(int j=0;j<s.numCols();j++)
{
System.out.print(s.get(i,j));
}
System.out.println("");
}

System.out.println("Serializing test Start.");
byte[] sMatrix=serializer.serializeMatrix(s);
System.out.println("Serialized Matrix value: "+Arrays.toString(sMatrix)+"\n length of serialized matrix: "+sMatrix.length);
SimpleMatrix dMatrix=deserializer.deserializeMatrix(sMatrix, 5, 5);

for(int i=0;i<dMatrix.numRows();i++)
{
for(int j=0;j<dMatrix.numCols();j++)
{
System.out.print(dMatrix.get(i,j));
}
System.out.println("");
}


}catch(Exception e)
{
System.out.println("In TestRSARandomNumberGenerator: "+e.getMessage());
}
}

public static void main(String gg[])
{
TestSeDe t=new TestSeDe();
t.testFunction();
}
}