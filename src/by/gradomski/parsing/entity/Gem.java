package by.gradomski.parsing.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Gem {
    private static Logger logger = LogManager.getLogger();
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

        if (value != gem.value) {
            System.out.println("val");
            return false;
        }
        if(!id.equals(gem.id)) {
            logger.error("id");
            System.out.println("id");
            return false;
        }
        if(!name.equals(gem.name)) {
            logger.error("name");
            System.out.println("name");
            return false;
        }
        if(!preciousness.equals(gem.preciousness)) {
            logger.error("preci");
            System.out.println("preci");
            return false;
        }
        if(!origin.equals(gem.origin)) {
            logger.error("origin");
            System.out.println("origin");
            return false;
        }
        if(!cuttingDate.equals(gem.cuttingDate)) {
            logger.error("cut, this=" + cuttingDate + ", gem.cut=" + gem.cuttingDate);
            System.out.println("cut");
            return false;
        }

        if (visualParameters == null){
            logger.error("visualParameters == null");
            System.out.println("vis = null");
            return (gem.visualParameters == null);
        } else {
            return visualParameters.equals(gem.visualParameters);
        }
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
        builder.append(", ");
        builder.append("visual parameters=");
        builder.append(visualParameters.toString());
        return builder.toString();
    }
}
