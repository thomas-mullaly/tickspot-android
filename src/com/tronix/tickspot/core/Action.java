package com.tronix.tickspot.core;

public interface Action<T>
{
    void Invoke(T param);
}
