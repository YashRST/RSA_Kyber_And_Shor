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

public class TestMathematicsOperations
{

public void testFunction()
{
try
{
System.out.println("1. Inside TestMathematicsOperations");
MathematicalOperationsInterface mathematicalOperations=new MathematicalOperations();
SimpleMatrix s=mathematicalOperations.generateCenteredBinomialMatrix(5, 5, 10);
System.out.println("Printing the random matrix after generation: \n S: \n");
for(int i=0;i<s.numRows();i++)
{
for(int j=0;j<s.numCols();j++)
{
System.out.print(s.get(i,j)+"   ");
}
System.out.println("");
}
System.out.println("\n printing the matrix S after modulas. \n S1: \n");
SimpleMatrix s1=mathematicalOperations.modMatrix(s, 2);
for(int i=0;i<s1.numRows();i++)
{
for(int j=0;j<s1.numCols();j++)
{
System.out.print(s1.get(i,j)+"   ");
}
System.out.println("");
}

byte[] sDigest,s1Digest;
sDigest=mathematicalOperations.createMatrixDigest(s, HashType.SHAKEHash256);
s1Digest=mathematicalOperations.createMatrixDigest(s1, HashType.SHAHash512, 128);
System.out.println("Hash of s and s1");
System.out.println("\nS: \n"+Arrays.toString(sDigest)+"\n length of s Digest: "+sDigest.length);
System.out.println("\nS1: \n"+Arrays.toString(s1Digest)+"\n length of s Digest: "+s1Digest.length);

}catch(Exception e)
{
System.out.println("In TestMathematics"+e.getMessage());
}
}

public static void main(String gg[])
{
TestMathematicsOperations t=new TestMathematicsOperations();
t.testFunction();
}
}