package edu.put.mobapp_lab


class Sciezka private constructor(
    val name: String,
    val imageId: Int,
    val info_cities: String,
    val info_tour: String,
    val level: String
) {

    override fun toString(): String {
        return name +"\n" + level + "\n" + info_cities + "\n" + info_tour
    }

    companion object {
        val info_easy = arrayOf(
            Sciezka(
                "Jastrowie", R.drawable.jastrowie,"Jastrowie – rez. „Diabli Skok”\n" + "dł. 20,0 km",
                "0,0 Jastrowie – Samborsko – Brzeźnica – Budy – 20,0 rez. „Diabli Skok”, szl.3501y", "Poziom: zielony"),
            Sciezka(
            "Lędyczek", R.drawable.ledyczek,"Lędyczek – Człuchów – Stara Rogoźnica\n" +
                        "dł. 70,0 km.",
                "0,0 Lędyczek – 1,6 Prądy Kol. – (WK/PM) – Debrzno – Człuchów (PKP, PKS) – 70,0 St. Rogoźnica (PKS)","Poziom: zielony"),
            Sciezka(
                "Żelichowo", R.drawable.zelichowo, "Żelichowo – Człopa – Tuczno\n" +
                        "dł. 30,0 km.", "0,0 Żelichowo(PKS), szl. niebieski – 2,2 Przelewice – (WK/ZP) – 10,0 Trzebin – 13,0 Człopa (PKS), szl.1135c, 3001y – 19,0 Golin – 22,0 Brzeźniak – 26,5 Martew – 30,0 Tuczno (PKP, PKS)",
                "Poziom: zielony"
            )
        )
        val info_hard = arrayOf(
            Sciezka(
                "Nadarzyce", R.drawable.nadarzyce,"Nadarzyce – Człopa\n" +
                        "dł. 100 km.",  "0,0 Nadarzyce (PKS) – 12,4 jez. Karpiowe – (WK/ZP) – Wałcz – Nakielno - Strzaliny – Tuczno – 100,0 Człopa (PKS), szl.1136z, 3001y",
                "Poziom: czerwony")

        )
    }
}

