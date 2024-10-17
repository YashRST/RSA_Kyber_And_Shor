package com.thinking.machines.kyber.parameters.source;
import com.thinking.machines.kyber.parameters.interfaces.*;
import com.thinking.machines.kyber.exceptions.*;

import java.util.Arrays;

public class KyberPrivateKey implements KyberKeyInterface
{
private final byte[] privateKey;
private final byte[] matrixA;
public KyberPrivateKey(byte[] privateKey, byte[] matrixA)
{
this.privateKey=Arrays.copyOf(privateKey,privateKey.length);
this.matrixA=Arrays.copyOf(matrixA, matrixA.length);
}

public byte[] getKyberKey() throws KyberException
{
return Arrays.copyOf(this.privateKey,this.privateKey.length);
}
public byte[] getMatrixA() throws KyberException
{
return Arrays.copyOf(this.matrixA, this.matrixA.length);
}
public int getKyberKeyLength() throws KyberException
{
return this.privateKey.length;
}

}
