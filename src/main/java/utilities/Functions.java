package utilities;

import java.util.Arrays;

public class Functions {
    public String parseNameImage(String name) {
        StringBuilder nameAcronym = new StringBuilder();
        Arrays.stream(name.split(" ")).forEach(str -> {
            nameAcronym.append(str.charAt(0));
        });

        return nameAcronym.toString();
    }
}
