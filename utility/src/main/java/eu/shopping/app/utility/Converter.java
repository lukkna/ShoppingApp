package eu.shopping.app.utility;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public abstract class Converter<I, O> {
    protected abstract O process(I data);

    public Optional<O> convert(I data) {
        return isNull(data) ?
                empty() :
                ofNullable(process(data));
    }

    public List<O> convert(List<I> data) {
        return isNull(data)
                ? Collections.emptyList()
                : processList(data);
    }

    private List<O> processList(List<I> data) {
        return data.stream()
                .map(this::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }
}