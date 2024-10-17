package com.thinking.machines.RSA.encryptor.source;
import com.thinking.machines.RSA.encryptor.interfaces.*;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RSAEncryptor implements RSAEncryptorInterface
{

public String encrypt(String message, RSAKeyInterface publicKey)
{
byte messageByteArray[]=message.getBytes(StandardCharsets.UTF_8);
BigInteger intMessageValue=new BigInteger(1,messageByteArray);
BigInteger processedMessageValue=intMessageValue.modPow(publicKey.getKeyExponent(),publicKey.getKeyModulo());
return Base64.getEncoder().encodeToString(processedMessageValue.toByteArray());
}

}