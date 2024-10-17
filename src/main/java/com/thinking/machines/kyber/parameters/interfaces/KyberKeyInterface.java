package com.thinking.machines.kyber.parameters.interfaces;
import com.thinking.machines.kyber.exceptions.*;

public interface KyberKeyInterface 
{
public byte[] getKyberKey() throws KyberException;
public byte[] getMatrixA() throws KyberException;
public int getKyberKeyLength() throws KyberException;
}