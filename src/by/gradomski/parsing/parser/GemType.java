package by.gradomski.parsing.parser;

import by.gradomski.parsing.exception.IncorrectGemTypeException;

import java.util.Arrays;
import java.util.Optional;

public enum GemType {
    GEMS("gems"),
    GEM("gem"),
    ID("id"),
    NAME("name"),
    VALUE("value"),
    PRECIOUSNESS("preciousness"),
    ORIGIN("origin"),
    CUTTING_DATE("cutting-date"),
    VISUAL_PARAMETERS("visual-parameters"),
    COLOR("color"),
    CLARITY("clarity"),
    FACETS("facets");

    private String value;

    GemType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static GemType getTypeByValue(String value) throws IncorrectGemTypeException {
        GemType[] gemTypes = GemType.values();
        Optional<GemType> optionalGemType = Arrays.stream(gemTypes).filter(g -> g.getValue().equals(value)).findFirst();
        if (optionalGemType.isEmpty()){
            throw new IncorrectGemTypeException(value + " type not present in GemType enum");
        }
        return optionalGemType.get();
    }
}
