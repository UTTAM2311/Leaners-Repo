package functioalInterfaces;

import java.util.List;

@FunctionalInterface
public interface FreeNodesFinder<T> {
    public List<T> find(List<T> nodes);
}
