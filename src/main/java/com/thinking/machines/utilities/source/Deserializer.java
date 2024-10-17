package com.thinking.machines.utilities.source;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;
import org.ejml.simple.*;
import java.util.*;

public class Deserializer implements DeserializerInterface
{

public SimpleMatrix deserializeMatrix(byte[] matrixBytes, int rows, int columns) throws UtilitiesException
{
if(matrixBytes==null) throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \n Need a byte array. Function Signature: public SimpleMatrix deserializeMatrix(byte[], KyberParameterInterface, int , int )");
if(matrixBytes.length==0) throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \n Need a byte array. Function Signature: public SimpleMatrix deserializeMatrix(byte[], KyberParameterInterface, int , int )");

SimpleMatrix resultMatrix=null;
try
{
resultMatrix=new SimpleMatrix(rows,columns);
int index=0;
for(int i=0;i<rows;i++)
{
for(int j=0;j<columns;j++)
{
int deserializedValue=((matrixBytes[index++] & 0xFF)<<24) | ((matrixBytes[index++] & 0xFF) << 16) | ((matrixBytes[index++] & 0xFF) << 8) | (matrixBytes[index++] & 0xFF);
resultMatrix.set(i,j,deserializedValue);
}
}

}catch(Exception exception)
{
throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \n"+exception);
}
return resultMatrix;
}

public SimpleMatrix deserializeMatrix(byte[] matrixBytes, KyberParameterInterface kyberParameters) throws UtilitiesException
{
if(matrixBytes==null) throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \nNeed a byte array. Function Signature: public SimpleMatrix deserializeMatrix(byte[], KyberParameterInterface, int , int )");
if(matrixBytes.length==0) throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \n Need a byte array. Function Signature: public SimpleMatrix deserializeMatrix(byte[], KyberParameterInterface, int , int )");
if(kyberParameters==null) throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \n Need a byte array. Function Signature: public SimpleMatrix deserializeMatrix(byte[], KyberParameterInterface, int , int )");

SimpleMatrix resultMatrix=null;
int rows, columns;
rows=kyberParameters.getN();
columns=kyberParameters.getK();

try
{
resultMatrix=new SimpleMatrix(rows,columns);
int index=0;
for(int i=0;i<rows;i++)
{
for(int j=0;j<columns;j++)
{
int deserializedValue=((matrixBytes[index++] & 0xFF)<<24) | ((matrixBytes[index++] & 0xFF) << 16) | ((matrixBytes[index++] & 0xFF) << 8) | (matrixBytes[index++] & 0xFF);
resultMatrix.set(i,j,deserializedValue%kyberParameters.getQ());
}
}

}catch(Exception exception)
{
throw new UtilitiesException("In Deserializer class --> deserializeMatrix: \n"+exception);
}
return resultMatrix;
}


}