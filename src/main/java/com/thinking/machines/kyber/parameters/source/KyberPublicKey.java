package com.thinking.machines.kyber.parameters.source;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.exceptions.*;

import java.util.Arrays;

public class KyberPublicKey implements KyberKeyInterface
{

private final byte[] publicKey;
private final byte[] matrixA;

public KyberPublicKey(byte[] publicKey, byte[] matrixA)
{
this.publicKey=Arrays.copyOf(publicKey,publicKey.length);
this.matrixA=Arrays.copyOf(matrixA, matrixA.length);
}

public byte[] getKyberKey() throws KyberException
{
return Arrays.copyOf(this.publicKey,this.publicKey.length);
}
public byte[] getMatrixA() throws KyberException
{
return Arrays.copyOf(this.matrixA, this.matrixA.length);
}

public int getKyberKeyLength() throws KyberException
{
return this.publicKey.length;
}

}
