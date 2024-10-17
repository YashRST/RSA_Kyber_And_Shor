package com.thinking.machines.RSA.decryptor.source;
import com.thinking.machines.RSA.decryptor.interfaces.*;
import com.thinking.machines.RSA.keyGenerator.interfaces.*;
import com.thinking.machines.RSA.encryptor.interfaces.*;
import com.thinking.machines.RSA.encryptor.source.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RSADecryptor implements RSADecryptorInterface
{
public String decrypt(String message, RSAKeyInterface privateKey)
{
byte messageByteArray[]=Base64.getDecoder().decode(message);
BigInteger intMessageValue=new BigInteger(1,messageByteArray);
BigInteger processedMessageValue=intMessageValue.modPow(privateKey.getKeyExponent(),privateKey.getKeyModulo());
return new String(processedMessageValue.toByteArray(),StandardCharsets.UTF_8);
}
}

