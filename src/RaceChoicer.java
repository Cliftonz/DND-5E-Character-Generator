
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class RaceChoicer {
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner reader = new Scanner(System.in);

        String[] races
                = {
                    "Dwarf",
                    "Elf",
                    "Halfling",
                    "Human",
                    "Dragonborn",
                    "Gnome",
                    "Half-Elf",
                    "Half-Orc",
                    "Tiefling",
                    "Aarakocra",
                    "Gensai",
                    "Aasimar",
                    "Firbolg",
                    "Goliath",
                    "Kenku",
                    "Lizardfolk",
                    "Tabaxi",
                    "Triton",
                    "Tortle",
                    "Monster"
                };

        String[] classes
                = {
                    "Barbarian",
                    "Bard",
                    "Cleric",
                    "Druid",
                    "Fighter",
                    "Monk",
                    "Paladin",
                    "Ranger",
                    "Rogue",
                    "Sorcerer",
                    "Warlock",
                    "Wizard", //"Artificer",
                //"mystic"
                };

        String[] background
                = {
                    "Acolyte",
                    "Charlatan",
                    "City Watch",
                    "Clan Crafter",
                    "Cloistered Scholar",
                    "Courtier",
                    "Criminal",
                    "Entertainer",
                    "Faction Agent",
                    "Far Traveler",
                    "Folk Hero",
                    "Guild Artisan",
                    "Hermit",
                    "Inheritor",
                    "Knight of the Order",
                    "Mercenary Veteran",
                    "Noble",
                    "Outlander",
                    "Sage",
                    "Sailor",
                    "Soldier",
                    "Urban Bounty Hunter",
                    "Urchin",
                    "Uthgardt Tribe Member",
                    "WaterDhavian Noble"
                };

        boolean repeat = true;
        while (repeat == true) {
            System.out.println("Randomly selected race: " 
                    + "\n" + "\t" + getRace(races) + "\n");

            String clas = getClass(classes);

            System.out.println("Randomly selected class: " 
                    + "\n" + "\t" + clas + "\n");

            switch (clas) {
                case "Cleric:\n\tKnowledge Domain":
                case "Cleric:\n\tLife Domain":
                case "Cleric:\n\tLight Domain":
                case "Cleric:\n\tNature Domain":
                case "Cleric:\n\tTempest Domain":
                case "Cleric:\n\tTrickery Domain":
                case "Cleric:\n\tWar Domain":
                case "Cleric:\n\tDeath Domain":
                case "Cleric:\n\tArcana Domain":
                case "Cleric:\n\tForge Domain":
                case "Cleric:\n\tGrave Domain":
                    System.out.println("Who worships the randomly selected God: "
                            + "\n" + "\t" + getGod(clas) + "\n");
                    break;
                default:
                    break;
            }
            
            System.out.println("Randomly Selected Background: " + "\n" + "\t" + getBackground(background) + "\n");

            System.out.println("Randomly rolled Stat Block is: " + "\n");
             
            System.out.print("\t");
            
            int[] array = getStatBlock();

            outputArray(array);
            
            System.out.println("");
            
            System.out.println("Or (if you use get 7 numbers drop the lowest)");
            
            System.out.println("");
            
            System.out.print("\t");
            
            int[] array2 = getStatBlock7();

            outputArray(array2);
            
            System.out.println("");
            
            System.out.println("Or with total mofifers between +4 to +8");
            
            System.out.println("");
            
            System.out.print("\t");
            
            int[] array3 = getStatBlockE();
           
            outputArray(array3);
            
            System.out.println("");
            
            System.out.println("the total modifer is "+modiferchecker(array3));
            
            System.out.println("");
            
            int[] array4 = getStatBlockL();
            
            outputArray(array4);
            
            System.out.println("");
            
            System.out.println("the total modifer is "+modiferchecker(array4));
            
            System.out.println("");
            
            System.out.println("Again? (y or n)");

            String n = reader.next();

            System.out.println("----------------------------------------");

            switch (n) {
                case "Y":
                case "y":
                    repeat = true;
                    break;
                case "n":
                default:
                    repeat = false;
                    break;
            }
        
        }
    }

    public static int[] getStatBlock() {
        int[][] array = new int[6][4];
        populateMatrix(array);
        sortedRow(array);
        int[] ret = rowSumofFirst3(array);
        bubbleSort(ret);
        return ret;
    }

    public static int[] getStatBlock7() {
        int[][] array = new int[7][4];
        populateMatrix(array);
        sortedRow(array);
        int[] ret = rowSumofFirst3(array);
        bubbleSort(ret);
        int[] ret2 = cutter(ret, 6);
        return ret2;
    }
    
    public static int[] getStatBlockE()
    { 
       // System.out.println("in BlockE");
        int[][] array = new int[6][4];// make array
        int[] ret = null;
        final int stop=-2;
        int i=0;
        while (i !=stop )
            {
                i++;
//                System.out.println("in L1");
                populateMatrix(array);// populate 
                sortedRow(array);//sorts each row
                ret = rowSumofFirst3(array); // gets an array with the final stat numbers
                bubbleSort(ret);// sorts final stat numbers
                int modifer =modiferchecker(ret);// get unmodified modifer
//                System.out.println(modifer+" time "+i);
                if (modifer>8) //if unmodified modifer is over 8 modify it down to be
                    {
//                    System.out.println("in if");
                    for(int k=1;k!=-1;k++)
                        {   
//                            System.out.println("in L2");
                            modifer =modiferchecker(ret);// check modifer
//                            System.out.println(modifer+" Time 2 K is "+k);
                            
                            if(modifer > 8) // if over modify
                                {
//                                    System.out.println("in if 2");
                                    for (int j = 6; j <0; j--)
                                        {
//                                            System.out.println("in L3");
                                            ret[j]=ret[j]-1;//highest is taken sone by 1
                                            if (modiferchecker(ret) <= 8)
                                            {
                                                break;
                                            }
                                        }

                                }
                            if(modifer <= 8) // if not break out of loop
                                {
                                    k=stop;
                                }
                        }
                    }
                if (modifer >= 4 && modifer <= 8)
                    {
                        i=stop;
                    }
                
            }
        return ret;
    }
    
    public static int[] getStatBlockL() {
        int[][] array = new int[6][4];// make array
        int[] ret = null;
        final int stop = -2;
        int i = 0;
        while (i != stop) {
            i++;
            populateMatrix(array);// populate 
            sortedRow(array);//sorts each row
            ret = rowSumofFirst3(array); // gets an array with the final stat numbers
            bubbleSort(ret);// sorts final stat numbers
            int modifer = modiferchecker(ret);// get unmodified modifer
            if (modifer < 4) {
                i = stop;
            }
        }
        return ret;
    }
    
    public static int modiferchecker(int[] a){
        
        //System.out.println("in mod");
        double totalmodifier=0;
            for (int i = 0; i < a.length ; i++)
                {
                    totalmodifier += Math.floor((a[i]-10)/2);
                }
        return (int) totalmodifier;
    }

    public static int[] cutter(int[] array, int keep) {
        int[] ar = new int[keep];
        System.arraycopy(array, 0, ar, 0, keep);
        return ar;
    }

    public static void populateMatrix(int[][] data) {
        for (int[] data1 : data) {
            for (int j = 0; j < data1.length; j++) {
                data1[j] = ((int) (Math.random() * 6) + 1);
            }
        }
    }

    public static void outputMatrix(int[][] data) {
        for (int[] data1 : data) {
            for (int j = 0; j < data1.length; j++) {
                System.out.print(data1[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void sortedRow(int[][] data) {
        for (int[] data1 : data) {
            for (int column = 0; column < data1.length; column++) {
                for (int columnTwo = column + 1; columnTwo < data1.length; columnTwo++) {
                    if (data1[column] < data1[columnTwo]) {
                        int temp = data1[column];
                        data1[column] = data1[columnTwo];
                        data1[columnTwo] = temp;
                    }
                }
            }
        }
    }

    public static int[] rowSumofFirst3(int[][] data) {
        int[] rowSum = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            //rowSum[i] = 0;
            for (int j = 0; j < (data[i].length - 1); j++) {
                rowSum[i] += data[i][j];
            }
        }
        return rowSum;
    }

    public static void outputArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) //n-1
        {
            for (int j = 0; j < n - i - 1; j++) // n-i-1
            {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static String getGod(String clas) {
        String[] clericknowledge
                = {
                    "Azuth,god of wizards",
                    "Deneir,god of writing",
                    "Gond,god of craft",
                    "Mystra, goddess of magic",
                    "Oghma, god of knowledge",
                    "Savras, god of divination and fate",
                    "Selune, goddess of darkness and loss",
                    "Waukeen, goddess of trade",
                    "Moradin, dwarf god of creation",
                    "Sehanine Moonbow, elf goddess of the moon",
                    "Skoreaus Stonebones, god of stone giants and art",
                    "Surtur, god of fire giants and craft",
                    "Vecna, god of evil secrets"
                };

        String[] clericlife
                = {
                    "Chauntea, goddess of agriculture",
                    "Eldath, goddess of peace",
                    "Helm, god of protection",
                    "Ilmater, god of endurance",
                    "Lathander, god of birth and renewal",
                    "Lhira, goddess of joy",
                    "Selune, goddess of darkness and loss",
                    "Sune, goddess of love and beauty",
                    "Bahamut, dragon god of good",
                    "Semuanya, lizardfolk deity of survival",
                    "Yondalla, halfling goddess of fertillity and protection"
                };

        String[] clericlight
                = {
                    "Helm, god of protection",
                    "Lathander, god of birth and renewal",
                    "Milil, god of poetry and song",
                    "Sune, goddess of love and beauty",
                    "Corellon Larethian, elf deity of art and magic"
                };

        String[] clericNature
                = {
                    "Auril, goddess of winter",
                    "Eldath, goddess of peace",
                    "Malar,god of the hunt",
                    "Mielikki, goddess of forests",
                    "Silvanus, god of wild nature",
                    "Deep Sashelas, elf deity of art and magic",
                    "Eadro, merfolk deity of the sea",
                    "Rilifane Rallathil, wood elf god of nature",
                    "Sekolah, sahuagin god of the hunt",
                    "Skerrit, centaur and styr god of nature",};

        String[] clerictempest
                = {
                    "Auril, goddess of winter",
                    "Talos, og of storms",
                    "Umberlee, goddess of the sea",
                    "Deep Sashelas, elf deity of art and magic",
                    "Eadro, merfolk deity of the sea",
                    "Gruumsh, orc god of storms and war",
                    "Sekolah, sahuagin god of the hunt"
                };

        String[] clerictrickery
                = {
                    "Beshaba, goddess of misfortune",
                    "Cyric, god of lies",
                    "Leira, goddess of illusion",
                    "Mask, god of thieves",
                    "Shar, goddess of  darkness and loss",
                    "Tymora, goddess of good furtune",
                    "Garl Glittergold, gnome god of trickery and wiles",
                    "Lolth, drow goddess of spiders",
                    "Tiamat, dragon poddess of evil"
                };

        String[] clericwar
                = {
                    "Bane, god of tyranny",
                    "Tempus, god of war",
                    "Torm, god of curage and self scrifice",
                    "Tyr, god of justice",
                    "Bahamut, dragon god of good",
                    "Grolantor, hill giant god of war",
                    "Gruumsh, orc god of storms and war",
                    "Hruggek, bugbear god of violence",
                    "Kurtulmark, kobold god of war and mining",
                    "maglubiyet, goblinoid god of war",
                    "Surtur, god of fire giants and craft",
                    "Thrym, god of frost giants and strength"

                };
        String[] clericdeath
                = {
                    "Bhaal, god of murder",
                    "Kelemvor, god of the dead",
                    "Lviator, gooddess of pain",
                    "Myrkul, god of death",
                    "Shar, goddess of  darkness and loss",
                    "Talona, goddess of disease and poison",
                    "Blibdoolpoolp, kuo-toa goddess",
                    "Laogzed, troglodyte god of hunger"
                };
        String[] clericarcana
                = {
                    "Azuth,god of wizards",
                    "Mystra, goddess of magic",
                    "Corellon Larethian, elf deity of art and magic",
                    //"Hecate, goddess of magic and the moon", 
                    //"Math Mathonwy, god of magic ",
                    //"Isis, goddess of fertility and magic", 
                    "Vecna, god of evil secrets",};
        String[] clericforge
                = {
                    "Gond,god of craft",
                    "Moradin, dwarf god of creation",};
        String[] clericgrave
                = {
                    "Undying Court",
                    "Kelemvor, god of the dead"
                };
        String c;
        switch (clas) {
            case "Cleric:\n\tKnowledge Domain":
                c = clericknowledge[(int) (Math.random() * clericknowledge.length)];
                break;

            case "Cleric:\n\tLife Domain":
                c = clericlife[(int) (Math.random() * clericlife.length)];
                break;

            case "Cleric:\n\tLight Domain":
                c = clericlight[(int) (Math.random() * clericlight.length)];
                break;

            case "Cleric:\n\tNature Domain":
                c = clericNature[(int) (Math.random() * clericNature.length)];
                break;

            case "Cleric:\n\tTempest Domain":
                c = clerictempest[(int) (Math.random() * clerictempest.length)];
                break;

            case "Cleric:\n\tTrickery Domain":
                c = clerictrickery[(int) (Math.random() * clerictrickery.length)];
                break;

            case "Cleric:\n\tWar Domain":
                c = clericwar[(int) (Math.random() * clericwar.length)];
                break;

            case "Cleric:\n\tDeath Domain":
                c = clericdeath[(int) (Math.random() * clericdeath.length)];
                break;

            case "Cleric:\n\tArcana Domain":
                c = clericarcana[(int) (Math.random() * clericarcana.length)];
                break;

            case "Cleric:\n\tForge Domain":
                c = clericforge[(int) (Math.random() * clericforge.length)];
                break;

            case "Cleric:\n\tGrave Domain":
                c = clericgrave[(int) (Math.random() * clericgrave.length)];
                break;

            default:
                c = "";
                break;
        }
        return c;
    }

    public static String getBackground(String[] array) {
        String background = array[(int) (Math.random() * array.length)];
        return background;
    }

    public static String getClass(String[] array) {
        String classes = array[(int) (Math.random() * array.length)];
        String[] barbarian
                = {
                    "Barbarian:\n\tPath of the Berserker",
                    "Barbarian:\n\tPath of the Totem Warrior",
                    "Barbarian:\n\tPath of the Ancestral Guardian",
                    "Barbarian:\n\tPath of the Storm Herald",
                    "Barbarian:\n\tPath of the Zealot",
                    "Barbarian:\n\tPath of the BattleRager"
                };

        String[] bard
                = {
                    "Bard:\n\tCollege of Lore",
                    "Bard:\n\tCollege of Valor",
                    "Bard:\n\tCollege of Glamour",
                    "Bard:\n\tCollege of Swords",
                    "Bard:\n\tCollege of Whispers"
                };
        String[] cleric
                = {
                    "Cleric:\n\tKnowledge Domain",
                    "Cleric:\n\tLife Domain",
                    "Cleric:\n\tLight Domain",
                    "Cleric:\n\tNature Domain",
                    "Cleric:\n\tTempest Domain",
                    "Cleric:\n\tTrickery Domain",
                    "Cleric:\n\tWar Domain",
                    "Cleric:\n\tDeath Domain",
                    "Cleric:\n\tArcana Domain",
                    "Cleric:\n\tForge Domain",
                    "Cleric:\n\tGrave Domain"
                };

        String[] druid
                = {
                    "Druid:\n\tCircle of the Land",
                    "Druid:\n\tCircle of the Moon",
                    "Druid:\n\tCircle of the Dreams",
                    "Druid:\n\tCircle of the Shepherd"
                };

        String[] druidCotL
                = {
                    "Druid:\n\tCircle of the Land:\n\tArctic",
                    "Druid:\n\tCircle of the Land:\n\tCoast",
                    "Druid:\n\tCircle of the Land:\n\tDesert",
                    "Druid:\n\tCircle of the Land:\n\tForest",
                    "Druid:\n\tCircle of the Land:\n\tGrassland",
                    "Druid:\n\tCircle of the Land:\n\tMountain",
                    "Druid:\n\tCircle of the Land:\n\tSwamp",
                    "Druid:\n\tCircle of the Land:\n\tUnderdark"
                };

        String[] fighter
                = {
                    "Fighter:\n\tChampion",
                    "Fighter:\n\tBattle Master",
                    "Fighter:\n\tEldritch Knight",
                    "Fighter:\n\tArcane Archer",
                    "Fighter:\n\tCavalier",
                    "Fighter:\n\tSamurai",
                    "Fighter:\n\tPurple Dragon Knight"
                };

        String[] monk
                = {
                    "Monk:\n\tWay of the Open Hand",
                    "Monk:\n\tWay of the Shadow",
                    "Monk:\n\tWay of the Four Elements",
                    "Monk:\n\tWay of the Drunken Master",
                    "Monk:\n\tWay of the Kensei",
                    "Monk:\n\tWay of the Sun Soul",
                    "Monk:\n\tWay of the Long Death",};

        String[] paladin
                = {
                    "Paladin:\n\tOath of Devotion",
                    "Paladin:\n\tOath of the Ancients",
                    "Paladin:\n\tOath of Vengeance",
                    "Paladin:\n\tOath of Conquest",
                    "Paladin:\n\tOath of Redemption",
                    "Paladin:\n\tOath of the Crown",
                    "Paladin:\n\tOathBreaker"
                };

        String[] ranger
                = {
                    "Ranger:\n\tHunter",
                    "Ranger:\n\tBeast Master",
                    "Ranger:\n\tGloom Stalker",
                    "Ranger:\n\tHorizon Walker",
                    "Ranger:\n\tMonster Slayer"
                };

        String[] rogue
                = {
                    "Rogue:\n\tThief",
                    "Rogue:\n\tAssassin",
                    "Rogue:\n\tArcane Trickster",
                    "Rogue:\n\tInquisitive",
                    "Rogue:\n\tMastermind",
                    "Rogue:\n\tScout",
                    "Rogue:\n\tSwashbuckler"

                };

        String[] sorcerer
                = {
                    "Sorcerer:\n\tStorm Sorcery",
                    "Sorcerer:\n\tDraconic Bloodline",
                    "Sorcerer:\n\tWild Magic",
                    "Sorcerer:\n\tDivine Soul",
                    "Sorcerer:\n\tShadow Magic"
                };

        String[] sorcererDA
                = {
                    "Sorcerer:\n\tDraconic Bloodline:\n\tBlack",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tBlue",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tBrass",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tBronze",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tCopper",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tGold",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tGreen",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tRed",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tSilver",
                    "Sorcerer:\n\tDraconic Bloodline:\n\tWhite"
                };

        String[] warlock
                = {
                    "Warlock:\n\tThe Undying",
                    "Warlock:\n\tThe Archfey",
                    "Warlock:\n\tThe Fiend",
                    "Warlock:\n\tThe Great Old One",
                    "Warlock:\n\tThe Celestial",
                    "Warlock:\n\tThe Hexblade"
                };

        String[] wizard
                = {
                    "Wizard:\n\tBladesinging",
                    "Wizard:\n\tWar Magic",
                    "Wizard:\n\tAbjuration",
                    "Wizard:\n\tConjuration",
                    "Wizard:\n\tDivination",
                    "Wizard:\n\tEnchantment",
                    "Wizard:\n\tEvocation",
                    "Wizard:\n\tIllusion",
                    "Wizard:\n\tNecromancy",
                    "Wizard:\n\tTransmutation"
                };

        switch (classes) {
            case "Barbarian":
                classes = barbarian[(int) (Math.random() * barbarian.length)];
                break;

            case "Bard":
                classes = bard[(int) (Math.random() * bard.length)];
                break;

            case "Cleric":
                classes = cleric[(int) (Math.random() * cleric.length)];
                break;

            case "Druid":
                classes = druid[(int) (Math.random() * druid.length)];
                if ("Druid:\n\tCircle of the Land".equals(classes)) {
                    classes = druidCotL[(int) (Math.random() * druidCotL.length)];
                }
                break;

            case "Fighter":
                classes = fighter[(int) (Math.random() * fighter.length)];
                break;

            case "Monk":
                classes = monk[(int) (Math.random() * monk.length)];
                break;

            case "Paladin":
                classes = paladin[(int) (Math.random() * paladin.length)];
                break;

            case "Ranger":
                classes = ranger[(int) (Math.random() * ranger.length)];
                break;

            case "Rogue":
                classes = rogue[(int) (Math.random() * rogue.length)];
                break;

            case "Sorcerer":
                classes = sorcerer[(int) (Math.random() * sorcerer.length)];
                if ("Sorcerer:\n\tDraconic Bloodline".equals(classes)) {
                    classes = sorcererDA[(int) (Math.random() * sorcererDA.length)];
                }
                break;

            case "Warlock":
                classes = warlock[(int) (Math.random() * warlock.length)];
                break;

            case "Wizard":
                classes = wizard[(int) (Math.random() * wizard.length)];
                break;
        }
        return classes;
    }

    public static String getRace(String[] array) {
        String race = array[(int) (Math.random() * array.length)];

        String[] dwarf
                = {
                    "Dwarf:\n\tHill",
                    "Dwarf:\n\tMountain",
                    "Dwarf:\n\tDuergar"
                };

        String[] elf
                = {
                    "Elf:\n\tHigh",
                    "Elf:\n\tWood",
                    "Elf:\n\tDrow",
                    "Elf:\n\tMoon",
                    "Elf:\n\tSun",
                    "Elf:\n\tEladrin",
                    "Elf:\n\tGith"
                };

        String[] halfling
                = {
                    "Halfling:\n\tLightfoot",
                    "Halfling:\n\tStout",
                    "Halfling:\n\tStrongheart",
                    "Halfling:\n\tGhostwise"
                };

        String[] human
                = {
                    "Human:\n\tDefault",
                    "Human:\n\tVariant"
                };

        String[] dragonborn
                = {
                    "Dragonborn:\n\tBlack",
                    "Dragonborn:\n\tBlue",
                    "Dragonborn:\n\tBrass",
                    "Dragonborn:\n\tBronze",
                    "Dragonborn:\n\tCopper",
                    "Dragonborn:\n\tGold",
                    "Dragonborn:\n\tGreen",
                    "Dragonborn:\n\tRed",
                    "Dragonborn:\n\tSilver",
                    "Dragonborn:\n\tWhite"
                };

        String[] gnome
                = {
                    "Gnome:\n\tForest",
                    "Gnome:\n\tRock",
                    "Gnome:\n\tDeep"
                };

        String[] halfelf
                = {
                    "Half-Elf:\n\tDefault",
                    "Half-Elf:\n\tWood",
                    "Half-Elf:\n\tMoon/Sun",
                    "Half-Elf:\n\tDrow",
                    "Half-Elf:\n\tAquatic"
                };

        String[] tiefling
                = {
                    "Tiefling:\n\tDefault (Asmodeous)",
                    "Tiefling:\n\tFeral",
                    "Tiefling:\n\tDevil's Tongue",
                    "Tiefling:\n\tHellfire",
                    "Tiefling:\n\tWinged",
                    "Tiefling:\n\tAbyssal",
                    "Tiefling:\n\tBaalzebul",
                    "Tiefling:\n\tDispater",
                    "Tiefling:\n\tFierna",
                    "Tiefling:\n\tGlasya",
                    "Tiefling:\n\tLevistus",
                    "Tiefling:\n\tMammon",
                    "Tiefling:\n\tMephistopheles",
                    "Tiefling:\n\tZariel"
                };

        String[] gensai
                = {
                    "Gensai:\n\tAir",
                    "Gensai:\n\tEarth",
                    "Gensai:\n\tFire",
                    "Gensai:\n\tWater"
                };

        String[] assimar
                = {
                    "Aasimar:\n\tProtector",
                    "Aasimar:\n\tScourge",
                    "Aasimar:\n\tFallen"
                };

        String[] monster
                = {
                    "Bugbear",
                    "Goblin",
                    "Hobgoblin",
                    "Kobold",
                    "Minotaur",
                    "Orc",
                    "Yuan-Ti Pureblood"
                };

        switch (race) {
            case "Dwarf":
                race = dwarf[(int) (Math.random() * dwarf.length)];
                break;
            case "Elf":
                race = elf[(int) (Math.random() * elf.length)];
                break;
            case "Halfling":
                race = halfling[(int) (Math.random() * halfling.length)];
                break;
            case "Human":
                race = human[(int) (Math.random() * human.length)];
                break;
            case "Dragonborn":
                race = dragonborn[(int) (Math.random() * dragonborn.length)];
                break;
            case "Gnome":
                race = gnome[(int) (Math.random() * gnome.length)];
                break;
            case "Half-Elf":
                race = halfelf[(int) (Math.random() * halfelf.length)];
                break;
            case "Tiefling":
                race = tiefling[(int) (Math.random() * tiefling.length)];
                break;
            case "Gensai":
                race = gensai[(int) (Math.random() * gensai.length)];
                break;
            case "Aasimar":
                race = assimar[(int) (Math.random() * assimar.length)];
                break;
            case "Monster":
                race = monster[(int) (Math.random() * monster.length)];
                break;
            default:
                break;
        }
        return race;
    }
}
