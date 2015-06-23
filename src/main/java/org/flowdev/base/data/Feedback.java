package org.flowdev.base.data;

import java.util.ArrayList;
import java.util.List;

public final class Feedback {
    private List<String> infos = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    public List<String> getInfos() {
        return infos;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Feedback withInfos(List<String> infos) {
        this.infos = infos;
        return this;
    }

    public Feedback withWarnings(List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    public Feedback withErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }
}
