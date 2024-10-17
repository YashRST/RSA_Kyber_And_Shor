package com.thinking.machines.kyber.keyEncryption.interfaces;
import com.thinking.machines.kyber.exceptions.*;
import com.thinking.machines.kyber.parameters.interfaces.*;

public interface KyberSharedSecretKeyGeneratorInterface
{
public byte[][] generateSharedSecretKey(KyberKeyInterface key, KyberParameterInterface kyberParameters) throws KyberException;

}
