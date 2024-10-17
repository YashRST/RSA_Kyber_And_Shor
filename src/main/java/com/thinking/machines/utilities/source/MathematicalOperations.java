package com.thinking.machines.utilities.source;

import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;

import org.ejml.simple.*;
import java.security.*;
import org.bouncycastle.crypto.digests.*;

public class MathematicalOperations implements MathematicalOperationsInterface
{

private  double generateCenteredBinomialSample(int errorDistribution)
{
double sum=0;
for(int i=0; i<errorDistribution; i++) sum+=new SecureRandom().nextInt(2);
return sum-errorDistribution/2;
}

public SimpleMatrix generateCenteredBinomialMatrix(int rows, int columns, int errorDistribution) throws UtilitiesException
{
SimpleMatrix matrix=null;
if(rows<=0 || columns<=0)  throw new UtilitiesException("In MathmeticalOperations --> generateCenteredBinomialMatrix: \n"+"Invalid input: rows and columns valve can't be less then or equal to zero");
if(errorDistribution<=0) throw new UtilitiesException("In MathmeticalOperations --> generateCenteredBinomialMatrix: \n"+"Invalid input: error distribution can't be less then or equal to zero");
try
{
matrix=new SimpleMatrix(rows, columns);
for(int i=0;i<rows;i++) 
{
for(int j=0; j<columns;j++)
{
matrix.set(i,j,generateCenteredBinomialSample(errorDistribution));
}
}
}catch(Exception exception)
{
throw new UtilitiesException("In MathmeticalOperations --> generateCenteredBinomialMatrix: \n"+exception.getMessage());
}
return matrix;
}

public SimpleMatrix modMatrix(SimpleMatrix matrix, int modulus) throws UtilitiesException
{
if(matrix==null || matrix.numRows()==0 || matrix.numCols()==0) throw new UtilitiesException("In MathematicalOperations ---> modMatrix: \n"+"Invalid Input: Need a valid matrix.");
if(modulus<=0) throw new UtilitiesException("In MathematicalOperations ---> modMatrix: \n"+"Invalid Input: The value of Modulus factor need to be greater than zero (0).");
SimpleMatrix resultModMatrix=null;
try
{
resultModMatrix=matrix.copy();
for(int i=0; i<resultModMatrix.numRows();i++)
{
for(int j=0; j< resultModMatrix.numCols();j++)
{
resultModMatrix.set(i,j,resultModMatrix.get(i,j)%modulus);
}
}
}catch(Exception exception)
{
throw new UtilitiesException("In MathematicalOperations ---> modMatrix: \n"+exception.getMessage());
}
return resultModMatrix;
}

public byte[] createMatrixDigest(SimpleMatrix matrix, HashType hashType, int digestSize) throws UtilitiesException
{
byte[] digestedMatrix=null;
if(matrix==null) throw new UtilitiesException("In MathematicalOperations --> createMatrixDigest: \n"+"createMatrixDigest(SimpleMatrix, HashType, int) , No SimpleMatrix found.");
try
{
if(hashType.getCategory()==HashType.Category.SHAKE) digestedMatrix=new Hashing().SHAKEHash(new Serializer().serializeMatrix(matrix),hashType,digestSize);
else  digestedMatrix=new Hashing().SHAHash(new Serializer().serializeMatrix(matrix),hashType);
}catch(Exception exception)
{
throw new UtilitiesException("In MathematicalOperations ---> createMatrixDigest : \n"+exception.getMessage());
}
return digestedMatrix;
}

public byte[] createMatrixDigest(SimpleMatrix matrix, HashType hashType) throws UtilitiesException
{
byte[] digestedMatrix=null;
if(matrix==null) throw new UtilitiesException("In MathematicalOperations --> createMatrixDigest: \n"+"createMatrixDigest(SimpleMatrix, HashType, int) , No SimpleMatrix found.");
try
{
if(hashType.getCategory()==HashType.Category.SHAKE) digestedMatrix=new Hashing().SHAKEHash(new Serializer().serializeMatrix(matrix),hashType,new Serializer().serializeMatrix(matrix).length);
else  digestedMatrix=new Hashing().SHAHash(new Serializer().serializeMatrix(matrix),hashType);
}catch(Exception exception)
{
throw new UtilitiesException("In MathematicalOperations ---> createMatrixDigest : \n"+exception.getMessage());
}
return digestedMatrix;
}


}