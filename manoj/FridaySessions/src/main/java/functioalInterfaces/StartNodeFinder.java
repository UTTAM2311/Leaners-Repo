package functioalInterfaces;

import java.util.List;

@FunctionalInterface
public interface StartNodeFinder<T, R> {
    public T find(List<T> nodes, ShortestDistanceFinder<R> shortestDistanceFinder);
}
