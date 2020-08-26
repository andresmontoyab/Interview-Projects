package infraestructure;

import domain.Drone;
import domain.Position;
import usecases.ReportWritterPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReportWritterAdapterBuilder {

    //
    //"(%s, %s) Direccion %s"
    private String pattern;
    private String targetFolder;

    public static ReportWritterAdapterBuilder builder() {
        return new ReportWritterAdapterBuilder();
    }

    public ReportWritterAdapterBuilder setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public ReportWritterAdapterBuilder setTargetFolder(String targetFolder) {
        this.targetFolder= targetFolder;
        return this;
    }

    public ReportWritterAdapter build() {
        return new ReportWritterAdapter(pattern, targetFolder);
    }

}
