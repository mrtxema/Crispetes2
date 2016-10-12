package cat.mrtxema.crispetes.service.converter;

public interface Converter<I,O> {
    O convert(I i);
}
