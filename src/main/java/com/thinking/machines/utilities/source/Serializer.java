package com.thinking.machines.utilities.source;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;
import org.ejml.simple.*;
import java.util.*;

public class Serializer implements SerializerInterface
{

public byte[] serializeMatrix(SimpleMatrix matrix) throws UtilitiesException
{
byte[] serializedMatrix;
if(matrix==null || matrix.numRows()==0 || matrix.numCols()==0) throw new UtilitiesException("In Serializer --> serializeMatrix : \n Invalid Input: SimpleMatrix needed found null serializeMatrix(SimpleMatrix)");

try
{
int rows=matrix.numRows();
int columns=matrix.numCols();
serializedMatrix=new byte[rows*columns*4];
int index=0;
for(int i=0; i<rows; i++)
{
for(int j=0; j<columns; j++)
{
int value=(int)matrix.get(i,j);
serializedMatrix[index++]=(byte)(value>>24);
serializedMatrix[index++]=(byte)(value>>16);
serializedMatrix[index++]=(byte)(value>>8);
serializedMatrix[index++]=(byte) value;
}
}
}catch(Exception exception)
{
throw new UtilitiesException(exception.getMessage());
}
return serializedMatrix;
}

}