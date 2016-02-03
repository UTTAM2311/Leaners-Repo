package functioalInterfaces;

import java.util.List;

@FunctionalInterface
public interface StartNodeFinder<T> {
    public T find(List<T> nodes);
}
