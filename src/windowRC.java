/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZAC16
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.undo.*;
import java.util.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class windowRC implements ActionListener {

    //attributes
    UndoManager manager = new UndoManager();
    String S = "";
    JFrame wind;
    JPanel panel;
    JTextArea show;
    JLabel label, again;
    JButton Yes, No, Undo;
    final UndoManager undoManager0 = new UndoManager();
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

    //constructors
    //methods
//public static void main(String[] args)
//{
//    java.awt.EventQueue.invokeLater(new Runable(){
//        public void run() {
//            new createinterface().setVisible(true);
//        }
//    }));
//}
    public static void main(String[] args) {
        windowRC frus = new windowRC();
        frus.createinterface();
    }

    public void createinterface() {
        wind = new JFrame("Player Character Generator");
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        show = new JTextArea(10, 30);
        show.setDocument(new CustomUndoPlainDocument());

//        JScrollPane scrollPane = new JScrollPane(show);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setPreferredSize(new Dimension(150, 150));
        show.setEditable(false);

        label = new JLabel("The random character that has been made for you is an: ");
        again = new JLabel("Again?");
        Yes = new JButton();
        //No = new JButton();
//        Undo =new JButton();

        Yes.setText("Yes");
        //No.setText("No");
        show.getDocument().addUndoableEditListener(undoManager0);

        panel.add(label);
        panel.add(show);
        panel.add(again);
        panel.add(Yes);
        panel.add(new JButton(new AbstractAction("undo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager0.canUndo()) {
                    undoManager0.undo();
                }
            }
        }));
        panel.add(new JButton(new AbstractAction("redo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager0.canRedo()) {
                    undoManager0.redo();
                }
            }
        }));
        //panel.add(No);

        wind.add(panel);
        wind.setSize(500, 600);
        wind.setLocationRelativeTo(null);
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setVisible(true);

        Yes.addActionListener(this);
//        No.addActionListener(this);

        String clas = getClass(classes);
        String god = "";
        switch (clas) {
            case "Cleric:\nKnowledge Domain":
            case "Cleric:\nLife Domain":
            case "Cleric:\nLight Domain":
            case "Cleric:\nNature Domain":
            case "Cleric:\nTempest Domain":
            case "Cleric:\nTrickery Domain":
            case "Cleric:\nWar Domain":
            case "Cleric:\nDeath Domain":
            case "Cleric:\nArcana Domain":
            case "Cleric:\nForge Domain":
            case "Cleric:\nGrave Domain":
                god = getGod(clas);
                break;
            default:
                System.out.print("");
                break;
        }
        int[] array = getStatBlock();
        int[] array2 = getStatBlock7();
        int[] array3 = getStatBlockE();
        int[] array4 = getStatBlockL();

        S = getRace(races) + "\n\n" + clas + "\n\n" + god + getBackground(background)
                + "\n\n" + "Standard set rolled"
                + "\n\n" + "\t" + Arrays.toString(array)
                + "\n\n" + "Or (if you use get 7 numbers drop the lowest)"
                + "\n\n" + "\t" + Arrays.toString(array2)
                + "\n\n" + "Or with total modifers balanced between +4 to +8"
                + "\n\n" + "\t" + Arrays.toString(array3)
                + "\n\n" + "The Total Modifier is: " + modiferchecker(array3)
                + "\n\n" + "Or if you want a low stat block and get a treat!"
                + "\n\n" + "\t" + Arrays.toString(array4)
                + "\n\n" + "The Total Modifier is: " + modiferchecker(array4);
        show.setText(S);
//        S = getRace(races) + "\n\n" + clas + "\n\n" + god+ getBackground(background) + "\n\n";
//        show.setText(S);
//        for(int i=0;i<array.length;i++)
//        {
//            show.append(array[i]+" ");
//        }
//        show.append("\n\n");
//        show.append("Or (if you use get 7 numbers drop the lowest)"+"\n\n");
//        for(int i=0;i<array2.length;i++)
//            {
//                show.append(array2[i]+" ");
//            }  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clas = getClass(classes);
        String god = "";
//        if(e.getSource()== "Yes")
//        {
        switch (clas) {
            case "Cleric:\nKnowledge Domain":
            case "Cleric:\nLife Domain":
            case "Cleric:\nLight Domain":
            case "Cleric:\nNature Domain":
            case "Cleric:\nTempest Domain":
            case "Cleric:\nTrickery Domain":
            case "Cleric:\nWar Domain":
            case "Cleric:\nDeath Domain":
            case "Cleric:\nArcana Domain":
            case "Cleric:\nForge Domain":
            case "Cleric:\nGrave Domain":
                god = getGod(clas);
                break;
            default:
                System.out.print("");
                break;
        }
        int[] array = getStatBlock();
        int[] array2 = getStatBlock7();
        int[] array3 = getStatBlockE();
        int[] array4 = getStatBlockL();

        S = getRace(races) + "\n\n" + clas + "\n\n" + god + getBackground(background)
                + "\n\n" + "Standard set rolled"
                + "\n\n" + "\t" + Arrays.toString(array)
                + "\n\n" + "Or (if you use get 7 numbers drop the lowest)"
                + "\n\n" + "\t" + Arrays.toString(array2)
                + "\n\n" + "Or with total modifers balanced between +4 to +8"
                + "\n\n" + "\t" + Arrays.toString(array3)
                + "\n\n" + "The Total Modifier is: " + modiferchecker(array3)
                + "\n\n" + "Or if you want a low stat block and get a treat!"
                + "\n\n" + "\t" + Arrays.toString(array4)
                + "\n\n" + "The Total Modifier is: " + modiferchecker(array4);

        show.setText(S);

//            for(int i=0;i<array.length;i++)
//            {
//                show.append(array[i]+" ");
//            }
//            show.append("\n\n");
//            show.append("Or (if you use get 7 numbers drop the lowest)"+"\n\n");
//            for(int i=0;i<array2.length;i++)
//            {
//                show.append(array2[i]+" ");
//            }
        //show.append(array2);
        if (e.getSource() == "No") {
            System.exit(0);
        }
        //}
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

    public static int[] getStatBlockE() {
        //System.out.println("in BlockE");
        int[][] array = new int[6][4];// make array
        int[] ret = null;
        final int stop = -2;
        int i = 0;
        boolean a = true;
        while (i != stop) {
            i++;
//                System.out.println("in L1");
            while(a == true){
            populateMatrix(array);// populate 
            sortedRow(array);//sorts each row
            ret = rowSumofFirst3(array); // gets an array with the final stat numbers
            bubbleSort(ret);// sorts final stat numbers
            a = ret[0] < 18;
            }
            int modifer = modiferchecker(ret);// get unmodified modifer
//                System.out.println(modifer+" time "+i);
//            if (modifer > 8) //if unmodified modifer is over 8 modify it down to be
//            {
//                    System.out.println("in if");
            for (int k = 1; k != -1; k++) {
//                            System.out.println("in L2");
                modifer = modiferchecker(ret);// check modifer
//                            System.out.println(modifer+" Time 2 K is "+k);

                if (modifer > 8) // if over modify
                {
//                                    System.out.println("in if 2");
                    for (int j = 0; j < 6; j++) {
//                                            System.out.println("in L3");
                        ret[j] = ret[j] - 1;//highest is taken sone by 1
                        if (modiferchecker(ret) <= 8) {
                            break;
                        }
                    }
                } else if (modifer < 4) {
                    for (int j = 0; j < 6; j++) {
//                                            System.out.println("in L3");
                        ret[j] = ret[j] + 1;//highest is taken sone by 1
                        if (modiferchecker(ret) <= 4) {
                            break;
                        }
                    }
                 // if not break out of loop
                }else{
                    k = stop;
                }
            }
            if (modifer >= 4 && modifer <= 8) {
                i = stop;
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

    public static int modiferchecker(int[] a) {

        //System.out.println("in mod");
        double totalmodifier = 0;
        for (int i = 0; i < a.length; i++) {
            totalmodifier += Math.floor((a[i] - 10) / 2);
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
            rowSum[i] = 0;
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

    public static void bubbleSort(int[] arr) {
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
                    "Azuth,god of wizards\n\n",
                    "Deneir,god of writing\n\n",
                    "Gond,god of craft\n\n",
                    "Mystra, goddess of magic\n\n",
                    "Oghma, god of knowledge\n\n",
                    "Savras, god of divination and fate\n\n",
                    "Selune, goddess of darkness and loss\n\n",
                    "Waukeen, goddess of trade\n\n",
                    "Moradin, dwarf god of creation\n\n",
                    "Sehanine Moonbow, elf goddess of the moon\n\n",
                    "Skoreaus Stonebones, god of stone giants and art\n\n",
                    "Surtur, god of fire giants and craft\n\n",
                    "Vecna, god of evil secrets\n\n"
                };

        String[] clericlife
                = {
                    "Chauntea, goddess of agriculture\n\n",
                    "Eldath, goddess of peace\n\n",
                    "Helm, god of protection\n\n",
                    "Ilmater, god of endurance\n\n",
                    "Lathander, god of birth and renewal\n\n",
                    "Lhira, goddess of joy\n\n",
                    "Selune, goddess of darkness and loss\n\n",
                    "Sune, goddess of love and beauty\n\n",
                    "Bahamut, dragon god of good\n\n",
                    "Semuanya, lizardfolk deity of survival\n\n",
                    "Yondalla, halfling goddess of fertillity and protection\n\n"
                };

        String[] clericlight
                = {
                    "Helm, god of protection\n\n",
                    "Lathander, god of birth and renewal\n\n",
                    "Milil, god of poetry and song\n\n",
                    "Sune, goddess of love and beauty\n\n",
                    "Corellon Larethian, elf deity of art and magic\n\n"
                };

        String[] clericNature
                = {
                    "Auril, goddess of winter\n\n",
                    "Eldath, goddess of peace\n\n",
                    "Malar,god of the hunt\n\n",
                    "Mielikki, goddess of forests\n\n",
                    "Silvanus, god of wild nature\n\n",
                    "Deep Sashelas, elf deity of art and magic\n\n",
                    "Eadro, merfolk deity of the sea\n\n",
                    "Rilifane Rallathil, wood elf god of nature\n\n",
                    "Sekolah, sahuagin god of the hunt\n\n",
                    "Skerrit, centaur and styr god of nature\n\n",};

        String[] clerictempest
                = {
                    "Auril, goddess of winter\n\n",
                    "Talos, og of storms\n\n",
                    "Umberlee, goddess of the sea\n\n",
                    "Deep Sashelas, elf deity of art and magic\n\n",
                    "Eadro, merfolk deity of the sea\n\n",
                    "Gruumsh, orc god of storms and war\n\n",
                    "Sekolah, sahuagin god of the hunt\n\n"
                };

        String[] clerictrickery
                = {
                    "Beshaba, goddess of misfortune\n\n",
                    "Cyric, god of lies\n\n",
                    "Leira, goddess of illusion\n\n",
                    "Mask, god of thieves\n\n",
                    "Shar, goddess of  darkness and loss\n\n",
                    "Tymora, goddess of good furtune\n\n",
                    "Garl Glittergold, gnome god of trickery and wiles\n\n",
                    "Lolth, drow goddess of spiders\n\n",
                    "Tiamat, dragon poddess of evil\n\n"
                };

        String[] clericwar
                = {
                    "Bane, god of tyranny\n\n",
                    "Tempus, god of war",
                    "Torm, god of curage and self scrifice\n\n",
                    "Tyr, god of justice\n\n",
                    "Bahamut, dragon god of good\n\n",
                    "Grolantor, hill giant god of war\n\n",
                    "Gruumsh, orc god of storms and war\n\n",
                    "Hruggek, bugbear god of violence\n\n",
                    "Kurtulmark, kobold god of war and mining\n\n",
                    "maglubiyet, goblinoid god of war\n\n",
                    "Surtur, god of fire giants and craft\n\n",
                    "Thrym, god of frost giants and strength\n\n"

                };
        String[] clericdeath
                = {
                    "Bhaal, god of murder\n\n",
                    "Kelemvor, god of the dead\n\n",
                    "Lviator, gooddess of pain\n\n",
                    "Myrkul, god of death\n\n",
                    "Shar, goddess of  darkness and loss\n\n",
                    "Talona, goddess of disease and poison\n\n",
                    "Blibdoolpoolp, kuo-toa goddess\n\n",
                    "Laogzed, troglodyte god of hunger\n\n"
                };
        String[] clericarcana
                = {
                    "Azuth,god of wizards\n\n",
                    "Mystra, goddess of magic\n\n",
                    "Corellon Larethian, elf deity of art and magic\n\n",
                    //"Hecate, goddess of magic and the moon", 
                    //"Math Mathonwy, god of magic ",
                    //"Isis, goddess of fertility and magic", 
                    "Vecna, god of evil secrets\n\n",};
        String[] clericforge
                = {
                    "Gond,god of craft\n\n",
                    "Moradin, dwarf god of creation\n\n",};
        String[] clericgrave
                = {
                    "Undying Court\n\n",
                    "Kelemvor, god of the dead\n\n"
                };
        String c;
        switch (clas) {
            case "Cleric:\nKnowledge Domain":
                c = clericknowledge[(int) (Math.random() * clericknowledge.length)];
                break;

            case "Cleric:\nLife Domain":
                c = clericlife[(int) (Math.random() * clericlife.length)];
                break;

            case "Cleric:\nLight Domain":
                c = clericlight[(int) (Math.random() * clericlight.length)];
                break;

            case "Cleric:\nNature Domain":
                c = clericNature[(int) (Math.random() * clericNature.length)];
                break;

            case "Cleric:\nTempest Domain":
                c = clerictempest[(int) (Math.random() * clerictempest.length)];
                break;

            case "Cleric:\nTrickery Domain":
                c = clerictrickery[(int) (Math.random() * clerictrickery.length)];
                break;

            case "Cleric:\nWar Domain":
                c = clericwar[(int) (Math.random() * clericwar.length)];
                break;

            case "Cleric:\nDeath Domain":
                c = clericdeath[(int) (Math.random() * clericdeath.length)];
                break;

            case "Cleric:\nArcana Domain":
                c = clericarcana[(int) (Math.random() * clericarcana.length)];
                break;

            case "Cleric:\nForge Domain":
                c = clericforge[(int) (Math.random() * clericforge.length)];
                break;

            case "Cleric:\nGrave Domain":
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
                    "Barbarian:\nPath of the Berserker",
                    "Barbarian:\nPath of the Totem Warrior",
                    "Barbarian:\nPath of the Ancestral Guardian",
                    "Barbarian:\nPath of the Storm Herald",
                    "Barbarian:\nPath of the Zealot",
                    "Barbarian:\nPath of the BattleRager"
                };

        String[] bard
                = {
                    "Bard:\nCollege of Lore",
                    "Bard:\nCollege of Valor",
                    "Bard:\nCollege of Glamour",
                    "Bard:\nCollege of Swords",
                    "Bard:\nCollege of Whispers"
                };
        String[] cleric
                = {
                    "Cleric:\nKnowledge Domain",
                    "Cleric:\nLife Domain",
                    "Cleric:\nLight Domain",
                    "Cleric:\nNature Domain",
                    "Cleric:\nTempest Domain",
                    "Cleric:\nTrickery Domain",
                    "Cleric:\nWar Domain",
                    "Cleric:\nDeath Domain",
                    "Cleric:\nArcana Domain",
                    "Cleric:\nForge Domain",
                    "Cleric:\nGrave Domain"
                };

        String[] druid
                = {
                    "Druid:\nCircle of the Land",
                    "Druid:\nCircle of the Moon",
                    "Druid:\nCircle of the Dreams",
                    "Druid:\nCircle of the Shepherd"
                };

        String[] druidCotL
                = {
                    "Druid:\nCircle of the Land:\nArctic",
                    "Druid:\nCircle of the Land:\nCoast",
                    "Druid:\nCircle of the Land:\nDesert",
                    "Druid:\nCircle of the Land:\nForest",
                    "Druid:\nCircle of the Land:\nGrassland",
                    "Druid:\nCircle of the Land:\nMountain",
                    "Druid:\nCircle of the Land:\nSwamp",
                    "Druid:\nCircle of the Land:\nUnderdark"
                };

        String[] fighter
                = {
                    "Fighter:\nChampion",
                    "Fighter:\nBattle Master",
                    "Fighter:\nEldritch Knight",
                    "Fighter:\nArcane Archer",
                    "Fighter:\nCavalier",
                    "Fighter:\nSamurai",
                    "Fighter:\nPurple Dragon Knight"
                };

        String[] monk
                = {
                    "Monk:\nWay of the Open Hand",
                    "Monk:\nWay of the Shadow",
                    "Monk:\nWay of the Four Elements",
                    "Monk:\nWay of the Drunken Master",
                    "Monk:\nWay of the Kensei",
                    "Monk:\nWay of the Sun Soul",
                    "Monk:\nWay of the Long Death",};

        String[] paladin
                = {
                    "Paladin:\nOath of Devotion",
                    "Paladin:\nOath of the Ancients",
                    "Paladin:\nOath of Vengeance",
                    "Paladin:\nOath of Conquest",
                    "Paladin:\nOath of Redemption",
                    "Paladin:\nOath of the Crown",
                    "Paladin:\nOathBreaker"
                };

        String[] ranger
                = {
                    "Ranger:\nHunter",
                    "Ranger:\nBeast Master",
                    "Ranger:\nGloom Stalker",
                    "Ranger:\nHorizon Walker",
                    "Ranger:\nMonster Slayer"
                };

        String[] rogue
                = {
                    "Rogue:\nThief",
                    "Rogue:\nAssassin",
                    "Rogue:\nArcane Trickster",
                    "Rogue:\nInquisitive",
                    "Rogue:\nMastermind",
                    "Rogue:\nScout",
                    "Rogue:\nSwashbuckler"

                };

        String[] sorcerer
                = {
                    "Sorcerer:\nStorm Sorcery",
                    "Sorcerer:\nDraconic Bloodline",
                    "Sorcerer:\nWild Magic",
                    "Sorcerer:\nDivine Soul",
                    "Sorcerer:\nShadow Magic"
                };

        String[] sorcererDA
                = {
                    "Sorcerer:\nDraconic Bloodline:\nBlack",
                    "Sorcerer:\nDraconic Bloodline:\nBlue",
                    "Sorcerer:\nDraconic Bloodline:\nBrass",
                    "Sorcerer:\nDraconic Bloodline:\nBronze",
                    "Sorcerer:\nDraconic Bloodline:\nCopper",
                    "Sorcerer:\nDraconic Bloodline:\nGold",
                    "Sorcerer:\nDraconic Bloodline:\nGreen",
                    "Sorcerer:\nDraconic Bloodline:\nRed",
                    "Sorcerer:\nDraconic Bloodline:\nSilver",
                    "Sorcerer:\nDraconic Bloodline:\nWhite"
                };

        String[] warlock
                = {
                    "Warlock:\nThe Undying",
                    "Warlock:\nThe Archfey",
                    "Warlock:\nThe Fiend",
                    "Warlock:\nThe Great Old One",
                    "Warlock:\nThe Celestial",
                    "Warlock:\nThe Hexblade"
                };

        String[] wizard
                = {
                    "Wizard:\nBladesinging",
                    "Wizard:\nWar Magic",
                    "Wizard:\nAbjuration",
                    "Wizard:\nConjuration",
                    "Wizard:\nDivination",
                    "Wizard:\nEnchantment",
                    "Wizard:\nEvocation",
                    "Wizard:\nIllusion",
                    "Wizard:\nNecromancy",
                    "Wizard:\nTransmutation"
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
                if ("Druid:\nCircle of the Land".equals(classes)) {
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
                if ("Sorcerer:\nDraconic Bloodline".equals(classes)) {
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
                    "Dwarf:\nHill",
                    "Dwarf:\nMountain",
                    "Dwarf:\nDuergar"
                };

        String[] elf
                = {
                    "Elf:\nHigh",
                    "Elf:\nWood",
                    "Elf:\nDrow",
                    "Elf:\nMoon",
                    "Elf:\nSun",
                    "Elf:\nEladrin",
                    "Elf:\nGith",
                    "Elf:\nSea elf",
                    "Elf:\nShader-Kai"
                };
        String[] Gith
                = {
                    "Elf:\nGithyanki",
                    "Elf:\nGithzerai"
                };

        String[] halfling
                = {
                    "Halfling:\nLightfoot",
                    "Halfling:\nStout",
                    "Halfling:\nStrongheart",
                    "Halfling:\nGhostwise"
                };

        String[] human
                = {
                    "Human:\nDefault",
                    "Human:\nVariant"
                };

        String[] dragonborn
                = {
                    "Dragonborn:\nBlack",
                    "Dragonborn:\nBlue",
                    "Dragonborn:\nBrass",
                    "Dragonborn:\nBronze",
                    "Dragonborn:\nCopper",
                    "Dragonborn:\nGold",
                    "Dragonborn:\nGreen",
                    "Dragonborn:\nRed",
                    "Dragonborn:\nSilver",
                    "Dragonborn:\nWhite"
                };

        String[] gnome
                = {
                    "Gnome:\nForest",
                    "Gnome:\nRock",
                    "Gnome:\nDeep"
                };

        String[] halfelf
                = {
                    "Half-Elf:\nDefault",
                    "Half-Elf:\nWood",
                    "Half-Elf:\nMoon/Sun",
                    "Half-Elf:\nDrow",
                    "Half-Elf:\nAquatic"
                };

        String[] tiefling
                = {
                    "Tiefling:\nDefault (Asmodeous)",
                    "Tiefling:\nFeral",
                    "Tiefling:\nDevil's Tongue",
                    "Tiefling:\nHellfire",
                    "Tiefling:\nWinged",
                    "Tiefling:\nAbyssal",
                    "Tiefling:\nBaalzebul",
                    "Tiefling:\nDispater",
                    "Tiefling:\nFierna",
                    "Tiefling:\nGlasya",
                    "Tiefling:\nLevistus",
                    "Tiefling:\nMammon",
                    "Tiefling:\nMephistopheles",
                    "Tiefling:\nZariel"
                };

        String[] gensai
                = {
                    "Gensai:\nAir",
                    "Gensai:\nEarth",
                    "Gensai:\nFire",
                    "Gensai:\nWater"
                };

        String[] assimar
                = {
                    "Aasimar:\nProtector",
                    "Aasimar:\nScourge",
                    "Aasimar:\nFallen"
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
        String[] Eladrin
                = {
                    "Elf:\nEladrin\nAutumn",
                    "Elf:\nEladrin\nWinter",
                    "Elf:\nEladrin\nSpring",
                    "Elf:\nEladrin\nSummer"
                };

        OUTER:
        switch (race) {
            case "Dwarf":
                race = dwarf[(int) (Math.random() * dwarf.length)];
                break;
            case "Elf":
                race = elf[(int) (Math.random() * elf.length)];
                
                switch (race) {
                    case "Elf:\nEladrin":
                        race = Eladrin[(int) (Math.random() * Eladrin.length)];
                        break;
                    case "Elf:\nGith":
                        race = Gith[(int) (Math.random() * Gith.length)];
                        break;
                    default:
                        break;
                }

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

    public static String toString(int[] a) {
        if (a == null) {
            return "null";
        }
        int iMax = a.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0;; i++) {
            b.append(a[i]);
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }
}

class CustomUndoPlainDocument extends PlainDocument {

    private CompoundEdit compoundEdit;
//  @Override protected void fireUndoableEditUpdate(UndoableEditEvent e) {
//    if (compoundEdit == null) {
//      super.fireUndoableEditUpdate(e);
//    } else {
//      compoundEdit.addEdit(e.getEdit());
//    }
//  }

    @Override
    public void replace(
            int offset, int length,
            String text, AttributeSet attrs) throws BadLocationException {
        if (length == 0) {

            super.replace(offset, length, text, attrs);
        } else {

            compoundEdit = new CompoundEdit();
            super.fireUndoableEditUpdate(new UndoableEditEvent(this, compoundEdit));
            super.replace(offset, length, text, attrs);
            compoundEdit.end();
            compoundEdit = null;
        }
    }
}
