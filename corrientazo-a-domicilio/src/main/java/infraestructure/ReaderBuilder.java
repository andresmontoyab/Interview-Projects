package infraestructure;

import infraestructure.converter.Converter;
import usecases.DeliverLunchService;


public class ReaderBuilder {

    private Converter converter;
    private DeliverLunchService deliverLunchService;
    private String sourceFolder;

    public static ReaderBuilder builder() {
        return new ReaderBuilder();
    }

    public ReaderBuilder setConverter(Converter converter) {
        this.converter = converter;
        return this;
    }

    public ReaderBuilder setDeliveryLunch(DeliverLunchService deliverLunches) {
        this.deliverLunchService = deliverLunches;
        return this;
    }

    public ReaderBuilder setSourceFolder(String sourceFolder) {
        this.sourceFolder= sourceFolder;
        return this;
    }

    public Reader build() {
        return new Reader(converter, deliverLunchService, sourceFolder);
    }
}
