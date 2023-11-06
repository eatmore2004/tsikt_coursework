package BLL_Abstractions;

import java.util.List;
import java.util.function.Predicate;

public interface IService<T> {

    List<T> getAll();

    List<T> getAllByPredicate(Predicate<T> predicate);

    void saveAll(List<T> items);
}