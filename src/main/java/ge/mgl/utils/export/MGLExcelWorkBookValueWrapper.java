package ge.mgl.utils.export;

/**
 * Created by georgevashakidze on 7/8/17.
 */

public class MGLExcelWorkBookValueWrapper<V> {

    public enum Type {
        LONG,
        STRING,
        DOUBLE,
        INTEGER,
        BOOLEAN
    }

    private V value;
    private Type type;

    public MGLExcelWorkBookValueWrapper(Type type, V value) {
        this.value = value;
        this.type = type;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
