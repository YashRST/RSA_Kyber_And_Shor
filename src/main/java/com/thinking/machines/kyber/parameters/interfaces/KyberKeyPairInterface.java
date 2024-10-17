package com.thinking.machines.kyber.parameters.interfaces;
import com.thinking.machines.kyber.exceptions.*;

public interface KyberKeyPairInterface
{
public KyberKeyInterface getPublicKey() throws KyberException;
public KyberKeyInterface getPrivateKey() throws KyberException;
}