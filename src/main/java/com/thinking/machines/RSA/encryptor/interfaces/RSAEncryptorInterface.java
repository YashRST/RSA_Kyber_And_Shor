package com.thinking.machines.RSA.encryptor.interfaces;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;

import java.math.BigInteger;

public interface RSAEncryptorInterface
{
public String encrypt(String message, RSAKeyInterface publicKey);
}
