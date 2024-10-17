package com.thinking.machines.RSA.decryptor.interfaces;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;

import java.math.BigInteger;

public interface RSADecryptorInterface
{
public String decrypt(String message, RSAKeyInterface privateKey);
}
