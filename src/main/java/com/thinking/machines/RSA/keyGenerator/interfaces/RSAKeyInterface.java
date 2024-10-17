package com.thinking.machines.RSA.keyGenerator.interfaces;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.source.*;

import java.math.BigInteger;

public interface RSAKeyInterface
{

public BigInteger getKeyExponent();
public BigInteger getKeyModulo();
public int getKeyModuloSize();
public int getKeyExponentSize();
public int getHash();
}