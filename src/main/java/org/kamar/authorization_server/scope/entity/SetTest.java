package org.kamar.authorization_server.scope.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTest {

    public static void main(String[] args) {

        Sam sam = new Sam();
        ArrayList<String> names = new ArrayList<>();
        names.add("kamar");
        names.add("baraka");
        names.add("kombe");
        sam.getNames().addAll(names);
        System.out.println(sam);

    }
}

@Data
class Sam {

    private final Set<String> names = new HashSet<>();
}