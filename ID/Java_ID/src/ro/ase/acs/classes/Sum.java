package ro.ase.acs.classes;

import ro.ase.acs.interfaces.Computable;

public class Sum implements Computable {
    @Override
    public float compute(float a, float b) {
        return a + b;
    }
}
