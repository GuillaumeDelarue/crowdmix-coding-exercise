package gd.crowdmix.util;

public abstract class CaseClassOne<ONE> {
    protected final ONE $1;

    public CaseClassOne(final ONE parameter) {
        this.$1 = parameter;
    }

    public boolean equals(final Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && $1.equals(((CaseClassOne<?>) obj).$1);
    }

    public int hashCode() {
        return 31 + ($1 == null ? 0 : $1.hashCode());
    }

    public String toString() {
        return String.format("[%s]", $1);
    }
}