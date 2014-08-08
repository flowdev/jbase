package org.flowdev.base.data;

import java.util.ArrayList;
import java.util.List;

public final class Feedback {
    private List<String> infos = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    public List<String> infos() {
        return infos;
    }

    public List<String> warnings() {
        return warnings;
    }

    public List<String> errors() {
        return errors;
    }

    public Feedback infos(List<String> infos) {
        this.infos = infos;
        return this;
    }

    public Feedback warnings(List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    public Feedback errors(List<String> errors) {
        this.errors = errors;
        return this;
    }
}
