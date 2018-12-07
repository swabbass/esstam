public class StamDoc {
    public final  String id;
    public final int m1;
    public final int m2;
    public final int m3;
    public final int m4;
    public final int m5;
    public final int m6;

    public StamDoc(String id, int m1, int m2, int m3, int m4, int m5, int m6) {
        this.id = id;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
        this.m5 = m5;
        this.m6 = m6;
    }

    @Override
    public String toString() {
        return "StamDoc{" +
                "id='" + id + '\'' +
                ", m1=" + m1 +
                ", m2=" + m2 +
                ", m3=" + m3 +
                ", m4=" + m4 +
                ", m5=" + m5 +
                ", m6=" + m6 +
                '}';
    }
}
