package infraestructure.converter;

public interface Converter<S,R> {

    R convert(S source);
}
