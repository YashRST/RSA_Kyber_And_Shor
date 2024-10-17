package com.thinking.machines.utilities.interfaces;
import com.thinking.machines.utilities.source.*;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import org.ejml.simple.*;

public interface MathematicalOperationsInterface
{

public SimpleMatrix generateCenteredBinomialMatrix(int rows, int columns, int errorDistribution) throws  UtilitiesException;
public SimpleMatrix modMatrix(SimpleMatrix matrix, int modulus) throws  UtilitiesException;
public byte[] createMatrixDigest(SimpleMatrix matrix, HashType hashType, int digestSize) throws UtilitiesException;
public byte[] createMatrixDigest(SimpleMatrix matrix, HashType hashType) throws UtilitiesException;

}