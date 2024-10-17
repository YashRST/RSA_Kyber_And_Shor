package com.thinking.machines.utilities.source;
import com.thinking.machines.utilities.interfaces.*;
import com.thinking.machines.utilities.exceptions.*;

import org.bouncycastle.crypto.digests.*;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.*;

import java.util.*;


public class Hashing implements HashingInterface
{
//this hashing function takes two arguments 1st is the data to be hashed and the second is the length of the desired hash

public byte[] SHAHash(byte[] data, HashType hashType) throws UtilitiesException
{
Digest digest=null;
byte[] hash=null;
if(data==null) throw new UtilitiesException("Invalid Input: need data to hash found none");
if(hashType==null) throw new UtilitiesException("");
try
{
//validating the values of parameters
if(hashType.getCategory()!=HashType.Category.SHA) throw new UtilitiesException("Invalid Input: please enter a valid input of Hashing type.");

if(hashType.getValue()==256) digest=new SHA256Digest();
else digest=new SHA512Digest();

digest.update(data,0,data.length);
hash=new byte[digest.getDigestSize()];
digest.doFinal(hash,0);

}catch(Exception exception)
{
throw new UtilitiesException("In Hashing class --> SHAHash"+exception.getMessage());
}
return hash;
}

//this function create a shake128 (part of the SHA3 family) Hash for the given data
public byte[] SHAKEHash(byte[] data,  HashType hashType, int digestSize) throws UtilitiesException
{
SHAKEDigest digest=null;
byte[] hash=null;
if(data==null || hashType==null) throw new UtilitiesException("Invalid Input: Usage SHAHash(byte[],HashType)");

try
{
if(hashType.getCategory()!=HashType.Category.SHAKE) throw new UtilitiesException("Invalid Input: please enter a valid input of Hashing type.");

if(hashType.getValue()==128) digest=new SHAKEDigest(128);
else if(hashType.getValue()==256) digest=new SHAKEDigest(256);

digest.update(data,0,data.length);
if(digestSize==0) hash=new byte[digest.getDigestSize()];
else hash=new byte[digestSize];
digest.doFinal(hash,0,digestSize);

}catch(Exception exception)
{
throw new UtilitiesException(exception.getMessage());
}
return hash;
}

}