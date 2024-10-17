package com.thinking.machines.utilities.interfaces;
import com.thinking.machines.utilities.exceptions.*;

import java.math.BigInteger;

public interface RSARandomNumberGeneratorInterface 
{

public BigInteger generate() throws UtilitiesException;

}