package flyweight;

import java.util.HashMap;
import java.util.Map;

class TreeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        TreeType treeType = treeTypes.get(name);
        if (treeType == null) {
            treeType = new ConcreteTreeType(name, color, texture);
            treeTypes.put(name, treeType);
        }
        return treeType;
    }
}
