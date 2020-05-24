package by.gradomski.parsing.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gem {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private String id;
    private String name;
    private int value;
    private String preciousness;
    private String origin;
    private Date cuttingDate;
    private VisualParameters visualParameters;

    public Gem(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(String preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getCuttingDate() {
        return cuttingDate;
    }

    public void setCuttingDate(Date cuttingDate) {
        this.cuttingDate = cuttingDate;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gem gem = (Gem) o;

        if (value != gem.value) return false;
        if (id != null ? !id.equals(gem.id) : gem.id != null) return false;
        if (name != null ? !name.equals(gem.name) : gem.name != null) return false;
        if (preciousness != null ? !preciousness.equals(gem.preciousness) : gem.preciousness != null) return false;
        if (origin != null ? !origin.equals(gem.origin) : gem.origin != null) return false;
        if (cuttingDate != null ? !cuttingDate.equals(gem.cuttingDate) : gem.cuttingDate != null) return false;
        return visualParameters != null ? visualParameters.equals(gem.visualParameters) : gem.visualParameters == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + value;
        result = 31 * result + (preciousness != null ? preciousness.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (cuttingDate != null ? cuttingDate.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName());
        builder.append(": id=");
        builder.append(id);
        builder.append(", ");
        builder.append("name=");
        builder.append(name);
        builder.append(", ");
        builder.append("value=");
        builder.append(value);
        builder.append(", ");
        builder.append("origin=");
        builder.append(origin);
        builder.append(", ");
        builder.append("preciousness=");
        builder.append(preciousness);
        builder.append(", ");
        builder.append("cutting date=");
        builder.append(DATE_FORMAT.format(cuttingDate));
//        builder.append(cuttingDate);
        builder.append(", ");
        builder.append("visual parameters=");
        builder.append(visualParameters.toString());
        return builder.toString();
    }
}
