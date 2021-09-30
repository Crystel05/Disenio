package Modelo.PrototypePattern;

public interface IPrototype<T> {
    T clone();
    T deepClone();

}