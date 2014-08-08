package org.flowdev.base.data;

import java.util.ArrayList;
import java.util.List;

public final class Feedback {
    private List<String> infos = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    public List<String> infos() {
        return this.infos;
    }

    public List<String> warnings() {
        return this.warnings;
    }

    public List<String> errors() {
        return this.errors;
    }

    public Feedback infos(final List<String> infos) {
        this.infos = infos;
        return this;
    }

    public Feedback warnings(final List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    public Feedback errors(final List<String> errors) {
        this.errors = errors;
        return this;
    }
}
