package by.gradomski.parsing.entity;

public class VisualParameters {
    private String color;
    private int clarity;
    private int facets;

    public VisualParameters(){}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getClarity() {
        return clarity;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public int getFacets() {
        return facets;
    }

    public void setFacets(int facets) {
        this.facets = facets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisualParameters that = (VisualParameters) o;

        if (clarity != that.clarity) return false;
        if (facets != that.facets) return false;
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + clarity;
        result = 31 * result + facets;
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName());
        builder.append(": color=");
        builder.append(color);
        builder.append(", ");
        builder.append("clarity=");
        builder.append(clarity);
        builder.append(", ");
        builder.append("facets=");
        builder.append(facets);
        return builder.toString();
    }
}
