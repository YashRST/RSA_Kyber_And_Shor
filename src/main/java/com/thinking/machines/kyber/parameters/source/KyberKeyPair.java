package com.thinking.machines.kyber.parameters.source;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.exceptions.*;

public class KyberKeyPair implements KyberKeyPairInterface
{
public final KyberKeyInterface publicKey,privateKey;
public KyberKeyPair(KyberKeyInterface publicKey, KyberKeyInterface privateKey)
{
this.publicKey=publicKey;
this.privateKey=privateKey;
}

public KyberKeyInterface getPublicKey() throws KyberException
{
return this.publicKey;
}
public KyberKeyInterface getPrivateKey() throws KyberException
{
return this.privateKey;
}
}