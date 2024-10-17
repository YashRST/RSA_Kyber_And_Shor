package com.thinking.machines.utilities.source;

public enum HashType
{

SHAHash256(256, Category.SHA),
SHAHash512(512, Category.SHA),
SHAKEHash128(128, Category.SHAKE),
SHAKEHash256(256, Category.SHAKE);

private final int hashValue;
private final Category category;

HashType(int hashValue, Category category)
{
this.hashValue=hashValue;
this.category=category;
}

public int getValue()
{
return this.hashValue;
}
public Category getCategory()
{
return this.category;
}

public enum Category{ SHA, SHAKE}

}
