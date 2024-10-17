package com.thinking.machines.utilities.interfaces;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;
import org.ejml.simple.*;

public interface SerializerInterface
{
public byte[] serializeMatrix(SimpleMatrix matrix) throws UtilitiesException;
}