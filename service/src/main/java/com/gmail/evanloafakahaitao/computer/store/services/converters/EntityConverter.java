package com.gmail.evanloafakahaitao.computer.store.services.converters;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface EntityConverter<D, E> {

    E toEntity(D dto);

    default List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    default Set<E> toEntitySet(Set<D> dtoSet) {
        return dtoSet.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toSet());
    }
}
