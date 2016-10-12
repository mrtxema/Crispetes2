package cat.mrtxema.crispetes.service.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListConverter<I,O> implements Converter<Collection<? extends I>,List<O>> {
    private final Converter<I,O> itemConverter;

    public ListConverter(Converter<I,O> itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    public List<O> convert(Collection<? extends I> items) {
        List<O> result = new ArrayList<>();
        for (I item : items) {
            result.add(itemConverter.convert(item));
        }
        return result;
    }
}
