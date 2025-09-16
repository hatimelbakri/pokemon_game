class Type{
    /*************** 1G *******************/

    // Noms des espèces des pokemons dans le même ordre que le pokedex
    public static final String[] espece = {
        "Bulbizarre","Herbizarre","Florizarre","Salamèche","Reptincel","Dracaufeu","Carapuce","Carabaffe","Tortank",
        "Chenipan","Chrysacier","Papilusion","Aspicot","Coconfort","Dardargnan","Roucool","Roucoups","Roucarnage",
        "Rattata","Rattatac","Piafabec","Rapasdepic","Abo","Arbok","Pikachu","Raichu","Sabelette","Sablaireau",
        "Nidoran♀","Nidorina","Nidoqueen","Nidoran♂","Nidorino","Nidoking","Mélofée","Mélodelfe","Goupix","Feunard",
        "Rondoudou","Grodoudou","Nosferapti","Nosferalto","Mystherbe","Ortide","Rafflesia","Paras","Parasect",
        "Mimitoss","Aéromite","Taupiqueur","Triopikeur","Miaouss","Persian","Psykokwak","Akwakwak","Férosinge",
        "Colossinge","Caninos","Arcanin","Ptitard","Têtarte","Tartard","Abra","Kadabra","Alakazam","Machoc",
        "Machopeur","Mackogneur","Chétiflor","Boustiflor","Empiflor","Tentacool","Tentacruel","Racaillou",
        "Gravalanch","Grolem","Ponyta","Galopa","Ramoloss","Flagadoss","Magnéti","Magnéton","Canarticho","Doduo",
        "Dodrio","Otaria","Lamantine","Tadmorv","Grotadmorv","Kokiyas","Crustabri","Fantominus","Spectrum",
        "Ectoplasma","Onix","Soporifik","Hypnomade","Krabby","Krabboss","Voltorbe","Électrode","Noeunoeuf",
        "Noadkoko","Osselait","Ossatueur","Kicklee","Tygnon","Excelangue","Smogo","Smogogo","Rhinocorne",
        "Rhinoféros","Leveinard","Saquedeneu","Kangourex","Hypotrempe","Hypocéan","Poissirène","Poissoroy",
        "Stari","Staross","M. Mime","Insécateur","Lippoutou","Élektek","Magmar","Scarabrute","Tauros","Magicarpe",
        "Léviator","Lokhlass","Métamorph","Évoli","Aquali","Voltali","Pyroli","Porygon","Amonita","Amonistar",
        "Kabuto","Kabutops","Ptéra","Ronflex","Artikodin","Électhor","Sulfura","Minidraco","Draco","Dracolosse",
        "Mewtwo","Mew"
    };
    // Noms des différents types possibles pour les pokemons. Voir l'image pokemon_table_des_types_1G.png
    public static final String[] nomsType = {
        "NORMAL", "FEU", "EAU", "PLANTE", "ELECTRIK", "GLACE", "COMBAT", "POISON",
        "SOL", "VOL", "PSY", "INSECTE", "ROCHE", "SPECTRE", "DRAGON", "SANS"
    };
    // Constantes pour une utilisation plus pratique des types
    public static final int NORMAL = 0, FEU = 1, EAU = 2, PLANTE = 3, ELECTRIK = 4, GLACE = 5,
        COMBAT = 6, POISON = 7, SOL = 8, VOL = 9, PSY = 10, INSECTE = 11,
        ROCHE = 12, SPECTRE = 13, DRAGON = 14, SANS = 15;

    // Valeur possibles des différentes efficacités.
    public static final double NEUTRE = 1.0;
    public static final double INEFFICACE = 0.0;
    public static final double PAS_EFFICACE = 0.5;
    public static final double SUPER_EFFICACE = 2.0;

    // Tableau des efficacités de type. Voir l'image pokemon_table_des_types_1G.png.
    // Utilisation efficacite[typeAtt][typeDef].
    // Exemple : efficacite[Type.EAU][Type.FEU] retournera Type.SUPER_EFFICACE.
    private static final double[][] efficacite = {
        {1,1,1,1,1,1,1,1,1,1,1,1,0.5,0,1}, // NORMAL
        {1,0.5,0.5,2,1,2,1,1,1,1,1,2,0.5,1,0.5}, // FEU
        {1,2,0.5,0.5,1,1,1,1,2,1,1,1,2,1,0.5}, // EAU
        {1,0.5,2,0.5,1,1,1,0.5,2,0.5,1,0.5,2,1,0.5}, // PLANTE
        {1,1,2,0.5,0.5,1,1,1,0,2,1,1,1,1,0.5}, // ELECTRIK
        {1,1,0.5,2,1,0.5,1,1,2,2,1,1,1,1,2}, // GLACE
        {2,1,1,1,1,2,1,0.5,1,0.5,0.5,0.5,2,0,1}, // COMBAT
        {1,1,1,2,1,1,1,0.5,0.5,1,1,2,0.5,0.5,1}, // POISON
        {1,2,1,0.5,2,1,1,2,1,0,1,0.5,2,1,1}, // SOL
        {1,1,1,2,0.5,1,2,1,1,1,1,2,0.5,1,1}, // VOL
        {1,1,1,1,1,1,2,2,1,1,0.5,1,1,1,1}, // PSY
        {1,0.5,1,2,1,1,0.5,2,1,0.5,2,1,1,0.5,1}, // INSECTE
        {1,2,1,1,1,2,0.5,1,0.5,2,1,2,1,1,1}, // ROCHE
        {0,1,1,1,1,1,1,1,1,1,0,1,1,2,1}, // SPECTRE
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,2} // DRAGON
    };

    // Doit retourner une chaine des caractères contenant le nom de l'espèce du pokemon en fonction de son numero dans le pokedex.
    // Exemple :
    // Type.getEspece(94) doit retourner "Ectoplasma"
    public static String getEspece(int numPokedex){
    	if (numPokedex >= 1 && numPokedex <= espece.length) {
            return espece[numPokedex - 1];
        }
        return "Inconnu";
    }

    // Doit retourner une chaine de caractères contenant le nom du type en fonction de son indice (comprendre constantes qui défini le type).
    // Exemple :
    // Type.getNomType(Type.EAU) doit retourner "eau"
    public static String getNomType(int type){
    	if (type >= 0 && type < nomsType.length) {
            return nomsType[type].toLowerCase();
        }
        return "inconnu";
    }

    // Doit retourner l'efficacité lorsqu'un pokemon de type typeAtt attaque un pokemon de type typeDef.
    // Exemple :
    // Type.getEfficacite(Type.EAU, Type.FEU) doit retourner Type.SUPER_EFFICACE
    public static double getEfficacite(int typeAtt, int typeDef){
    	if (typeAtt >= 0 && typeAtt < efficacite.length && typeDef >= 0 && typeDef < efficacite[typeAtt].length) {
            return efficacite[typeAtt][typeDef];
        }
        return NEUTRE;
    }

    // Doit retourner l'indice du type passé en apramètre sous forme de chaine de caractère.
    // Exemple :
    // Type.getIndiceType("spectre") doit retourner Type.SPECTRE ou 13 plus simplement.
    // Attention à la casse ! 
    public static int getIndiceType(String type){
        for (int i = 0; i < nomsType.length; i++) {
            if (nomsType[i].equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }
}