package br.com.edsondev.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FacesUtils {

    public static Map<String, List<String>> getEtapasAno() {
	List<String> anos = new ArrayList<>();
	anos.add("1° ano");
	anos.add("2° ano");
	anos.add("3° ano");
	anos.add("4° ano");
	anos.add("5° ano");
	anos.add("6° ano");
	anos.add("7° ano");
	anos.add("8° ano");
	anos.add("9° ano");

	Map<String, List<String>> etapasAno = new HashMap<>();
	etapasAno.put("Médio", anos.subList(0, 3));
	etapasAno.put("Fundamental", anos);
	etapasAno.put("IME/ITA", anos.subList(0, 3));

	return etapasAno;
    }
}
