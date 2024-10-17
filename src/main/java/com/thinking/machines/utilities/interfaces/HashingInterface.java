package com.thinking.machines.utilities.interfaces;
import com.thinking.machines.utilities.exceptions.*;
import com.thinking.machines.utilities.source.*;

public interface HashingInterface
{

public byte[] SHAHash(byte[] data, HashType hashType) throws UtilitiesException;
public byte[] SHAKEHash(byte[] data, HashType hashType, int digestSize) throws UtilitiesException;

}
