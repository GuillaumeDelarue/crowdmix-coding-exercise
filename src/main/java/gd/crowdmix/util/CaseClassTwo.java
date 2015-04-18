package gd.crowdmix.util;

public abstract class CaseClassTwo<ONE, TWO> {
    protected final ONE $1;
    protected final TWO $2;

    public CaseClassTwo(final ONE param1, final TWO param2) {
        this.$1 = param1;
        this.$2 = param2;
    }

    public boolean equals(final Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && $1.equals(((CaseClassTwo<?, ?>) obj).$1) && $2.equals(((CaseClassTwo<?, ?>) obj).$2);
    }

    public int hashCode() {
        int result = 31 + ($1 == null ? 0 : $1.hashCode());
        result = 31 * result + ($2 == null ? 0 : $2.hashCode());
        return result;
    }

    public String toString() {
        return String.format("[%s,%s]", $1, $2);
    }
}
