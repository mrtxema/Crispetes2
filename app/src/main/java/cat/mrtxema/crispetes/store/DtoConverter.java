package cat.mrtxema.crispetes.store;

import cat.mrtxema.crispetes.service.converter.Converter;

public class DtoConverter<T> implements Converter<Dto<T>,T> {

    @Override
    public T convert(Dto<T> dto) {
        return dto.toModel();
    }
}
