package com.thinking.machines.kyber.keyGeneration.interfaces;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.exceptions.*;

public interface KyberKeyGeneratorInterface
{
public KyberKeyPairInterface generateKeyPair() throws KyberException;
}