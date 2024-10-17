package com.thinking.machines.kyber.parameters.interfaces;
import com.thinking.machines.kyber.exceptions.*;

public interface KyberParameterInterface
{
public int getN();
public int getQ();
public int getK();
public int getPolynomialSize();
public int getNumberOfSymmetricBytes();
public int getErrorDistribution();
public KyberParameterInterface getKyberParametersCopy() throws KyberException;
}