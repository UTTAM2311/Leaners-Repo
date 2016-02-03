package functioalInterfaces;

import java.util.List;

@FunctionalInterface
public interface FreeNodesFinder<T, R> {
    public List<T> find(List<T> names, List<R> states);
}
