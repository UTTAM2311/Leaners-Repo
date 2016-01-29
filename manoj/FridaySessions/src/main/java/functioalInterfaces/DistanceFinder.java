package functioalInterfaces;

@FunctionalInterface
public interface DistanceFinder<T, F> {
    public F find(T node1, T node2);

}
