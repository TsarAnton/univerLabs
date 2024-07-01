package pack;

import java.util.Comparator;

public interface SortInterface<Type> {
    public Type[] sort(Type[] elements, Comparator<Type> comparator);
}
