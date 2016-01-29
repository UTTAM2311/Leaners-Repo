package functioalInterfaces;

import java.util.Collection;

@FunctionalInterface
public interface ShortestDistanceFinder<T> {

    public T find(Collection<T> nodeSegments);

}
