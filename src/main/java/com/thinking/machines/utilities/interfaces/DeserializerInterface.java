package com.thinking.machines.utilities.interfaces;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.parameters.source.*;
import org.ejml.simple.*;
import java.util.*;

public interface DeserializerInterface
{

public SimpleMatrix deserializeMatrix(byte[] matrixBytes, int rows, int columns) throws UtilitiesException;
public SimpleMatrix deserializeMatrix(byte[] matrixBytes, KyberParameterInterface kyberParameters) throws UtilitiesException;

}
