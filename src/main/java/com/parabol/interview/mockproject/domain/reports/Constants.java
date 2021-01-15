package com.parabol.interview.mockproject.domain.reports;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Constants {

    public static Map<String, Integer> BIDDING_TYPE_LOOKUP = new HashMap<String, Integer>() {{
        put("Veri Girilmemiştir", null);
        put("Hizmet Alım", 3);
        put("Mal Alım", 1);
        put("Danışmanlık", 4);
        put("Yapım", 2);
        put("Yapım(Bakım)", 9999);
    }};

    public static Map<Integer, String> BIDDING_TYPE_LOOKUP_REVERSE = BIDDING_TYPE_LOOKUP.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public static Map<String, Integer> BIDDING_PROCEDURE_LOOKUP = new HashMap<String, Integer>() {{
        put("Veri Girilmemiştir", null);
        put("Bilinmiyor",0);
        put("Açık",1);
        put("Belli İstekli",2);
        put("Pazarlık 21A",3);
        put("Pazarlık 21B",4);
        put("Pazarlık 21C",5);
        put("Pazarlık 21D",6);
        put("Pazarlık 21E",7);
        put("Pazarlık 21F",8);
        put("İstisna 3-a",10);
        put("İstisna 3-b",11);
        put("İstisna 3-c",12);
        put("İstisna 3-d",13);
        put("İstisna 3-e",14);
        put("İstisna 3-f",15);
        put("İstisna 3-g",16);
        put("İstisna 3-h",17);
        put("İstisna 3-i",18);
        put("İstisna 3-j",19);
        put("İstisna 3-k79",20);
        put("İstisna 3-k9",21);
        put("İstisna 3-l",22);
        put("İstisna 3-m",23);
        put("İstisna 3-n",24);
        put("İstisna 3-g4964 m1",25);
        put("İstisna 3-Kikgm",26);
        put("İstisna 3-o",27);
        put("İstisna 3-p",28);
        put("İstisna 3-r",29);
        put("Tasarım-Yar",40);
        put("Diğer",50);
        put("Pazarlık",60);
        put("Doğrudan Temin 22A",9989);
        put("Doğrudan Temin 22B",9990);
        put("Doğrudan Temin 22C",9991);
        put("Doğrudan Temin 22D",9992);
        put("Doğrudan Temin 22E",9993);
        put("Doğrudan Temin 22F",9994);
        put("Doğrudan Temin 22G",9995);
        put("Doğrudan Temin 22H",9996);
        put("Doğrudan Temin 22I",9997);
        put("Doğrudan Temin 22İ",9998);
        put("Kültür ve Tabiat 3İ",9999);

    }};

    public static Map<Integer, String> BIDDING_PROCEDURE_LOOKUP_REVERSE = BIDDING_PROCEDURE_LOOKUP.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public static Map<String, Integer> BIDDING_DEPARTMENT_LOOKUP = new HashMap<String, Integer>() {{
        put("Veri Girilmemiştir", null);
        put("1. Bölge Müdürlüğü (İstanbul)",1);
        put("2. Bölge Müdürlüğü (İzmir)",2);
        put("3. Bölge Müdürlüğü (Konya)",3);
        put("4. Bölge Müdürlüğü (Ankara)",4);
        put("5. Bölge Müdürlüğü (Mersin)",5);
        put("6. Bölge Müdürlüğü (Kayseri)",6);
        put("7. Bölge Müdürlüğü (Samsun)",7);
        put("8. Bölge Müdürlüğü (Elazığ)",8);
        put("9. Bölge Müdürlüğü (Diyarbakır)",9);
        put("10. Bölge Müdürlüğü (Trabzon)",10);
        put("11. Bölge Müdürlüğü (Van)",11);
        put("12. Bölge Müdürlüğü (Erzurum)",12);
        put("13. Bölge Müdürlüğü (Antalya)",13);
        put("14. Bölge Müdürlüğü (Bursa)",14);
        put("15. Bölge Müdürlüğü (Kastamonu)",15);
        put("16. Bölge Müdürlüğü (Sivas)",16);
        put("17. Bölge Müdürlüğü (İstanbul)",17);
        put("18. Bölge Müdürlüğü (Kars)",18);
        put("Mali ve İdari İşler Dairesi Başkanlığı",101);
        put("Program ve İzleme Dairesi Başkanlığı",102);
        put("Etüt ve Proje Dairesi Başkanlığı",103);
        put("İşletmeler Dairesi Başkanlığı",105);
        put("Atölye Müdürlüğü (Akköprü)",107);
        put("Makine İkmal Şube Müdürlüğü",108);
        put("Kamu Özel Sektör Ortaklığı Bölge Müdürlüğü",9999);
    }};

    public static Map<Integer, String> BIDDING_DEPARTMENT_LOOKUP_REVERSE = BIDDING_DEPARTMENT_LOOKUP.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
}
