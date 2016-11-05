package cat.mrtxema.crispetes.store;

public interface Dto<T> {

    Dto<T> fromModel(T model);

    T toModel();
}
