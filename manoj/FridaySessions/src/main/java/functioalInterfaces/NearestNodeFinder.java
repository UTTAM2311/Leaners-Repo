package functioalInterfaces;

import java.util.List;

@FunctionalInterface
public interface NearestNodeFinder<T> {

    public T find(List<T> node, T fromNode);

}
