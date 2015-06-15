package com.example.heinenj.heinenjocelinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fbm on 06/03/2015.
 */

public class QuestionDAO extends DAOBase {

    // Nom de la table
    public static final String TABLE_QUESTION_REPONSE = "QUESTION_REPONSE";

    // Table: QUESTION
    public static final String COL_ID = "id";
    public static final String COL_QUESTION = "question";
    public static final String COL_BONNE_REPONSE = "bonne_reponse";
    public static final String COL_MAUVAISE_REPONSE_1 = "mauvaise_reponse_1";
    public static final String COL_MAUVAISE_REPONSE_2 = "mauvaise_reponse_2";
    public static final String COL_CATEGORY = "category";

    //rajouter CATEGORY

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_QUESTION_REPONSE + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_CATEGORY + " TEXT NOT NULL, " +
                    COL_QUESTION + " TEXT NOT NULL, " +
                    COL_BONNE_REPONSE + " TEXT NOT NULL, " +
                    COL_MAUVAISE_REPONSE_1 + " TEXT NOT NULL, " +
                    COL_MAUVAISE_REPONSE_2 + " TEXT NOT NULL);" ;


    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_QUESTION_REPONSE + ";";


    // Données pour la table
    private static final String[] DATA = new String[] {
            "'Ecrivain', 'Qui est Marcel Proust?', 'Un dieu','ta mère', 'Une station balneaire'",
            "'Ecrivain', 'Marcel Proust portait ?', 'La moustache', 'Le monde', 'Des portes jartelles'",
            "'Ecrivain', 'Marcel Proust ?', 'Oui',  'Non', 'Peut être'",
            "'Ecrivain', 'Marcel Proust ?', 'Info', 'Intox', 'Stephanie de monaco'",
            "'Ecrivain', 'Si on vous invite vous allez: ?', 'Chez swan', 'Chez Mickey', 'Chez moi'",
            "'Ecrivain', 'Marcel a dit : Fumer ', 'La pipe', 'Tue', 'Moi ce fumier'",
            "'Ecrivain', 'Marcel Proust en fond decran mosaique cest: ? ', 'Swag', 'Naze', 'Ridicule'",
            "'Ecrivain', 'Quelle etait la derniere parole de Marcel avant de mourir ? ', 'Cordialement', 'Rosebud', 'Sert men un autre!'",
            "'Ecrivain', 'Marcel Proust etait plutot: ', 'No undies like a boss', 'Slip', 'Calecon'",
            "'Ecrivain', 'Marcelo Di Prousti le cousin de Proust a balancer pendant une soiree Berlusconi: ?', 'Its a codzy!', 'Mamamia!', 'Lets e go!'",
            "'Ecrivain', 'Qui a écrit Les Misérables ?', 'Victro Hugo', 'Voltaire', 'Balzac'",
            "'Ecrivain', 'Quel est le véritable nom de Molière?', 'Jean-Baptiste Poquelin ', 'Henry Dess', 'Francois Arouet'",
            "'Ecrivain', 'Quel animal est élégant pour Muriel Barbery ?', 'Le herisson', 'Le chat', 'Le chien'",
            "'Ecrivain', 'Quel est le titre de ce livre qui se présente comme un manuel de philosophie pour tous ?', 'Le monde selon Garp', 'Le monde de narnia', 'Le monde de Sophie'",
            "'Ecrivain', 'Quel jour les écureuils de Central Park sont-ils tristes pour Katherine Pancol ?', 'Lundi', 'Samedi', 'Dimanche'",
            "'Ecrivain', 'De quelle couleur est la dame qui rend visite a Oscar dans le roman d Eric Emmanuel Schmitt ?', 'Rose', 'Rouge', 'Blanche'",
            "'Ecrivain', 'Molière a écrit: Les fourberies de ', 'Scapin', 'Galopin', 'Harpagon'",
            "'Ecrivain', 'Comment est Gatsby dans le roman de Francis Scott Fitzgerald ? ', 'Magnifique', 'Arrogant', 'Beau'",
            "'Ecrivain', 'Que veut l homme dans le roman de Douglas Kennedy ?', 'Vivre sa vie', 'Devenir riche', 'Partir en voyage'",
            "'Ecrivain', 'Son Emma eut un triste sort mais fut un succès fracassant ', 'Zola', 'Maupassant', 'Flaubert'",
            "'Ecrivain', 'Homme de lettres  Etranger et politique :', 'Camus', 'Sartre', 'Malraux'",
            "'Ecrivain', 'Il rechercha le temps perdu avec sa Madeleine qui nétait pas du gateau mais une veuve', 'Proust', 'Balzac', 'Chateaubriand'",
            "'Ecrivain', 'Il a voyagé au bout de la nuit avec son Bardamu ', 'Celine', 'Maupassant', 'Flaubert'",
            "'Ecrivain', 'Qui a écrit Le horla ? ', 'Maupassant', 'Zola', 'Balzac'",
            "'Ecrivain', 'Quel est le prénom de Zola ?', 'Emile', 'Marcel', 'Gustave'",
            "'Ecrivain', 'A quel écrivain doit-t-on: La confusion des sentiments ?', 'Zweig', 'Zwag', 'Zworg'",
            "'Ecrivain', 'Qui a écrit: Le lys dans la vallée ?', 'Balzac', 'Zola', 'Hugo'",
            "'Ecrivain', 'Dans quel roman de Stendhal voit-t-on Julien Sorel ?', 'Le Rouge et le Noir', 'Le Blanc et le Bleu', 'Le Jaune et l Eau'",
            "'Ecrivain', 'Quel écrivain a écrit: Jaccuse concernant l affaire Dreyfus ?', 'Zola', 'Sartre', 'Flaubert'",
            "'Ecrivain', 'Qui a écrit: La peste ?', 'Camus', 'Maupassant', 'Baudelaire'",
            "'Ecrivain', 'Les Fleurs du mal par: ', 'Baudelaire', 'Hugo', 'La Fontaine'",
            "'Ecrivain', 'Dans quel roman de Maupassant lit-on la vie de Jeanne ?', 'Une vie', 'La vie', 'Ma vie'",
            "'Ecrivain', 'Qui a écrit Madame Bovary ?', 'Flaubert', 'Hugo', 'Moliere'",


            "'Sport', 'Qui est Nikola Karabatich ', 'Un handballeur', 'Un ecrivain', 'Mon pere'",
            "'Sport', 'Zizou na jamais fait de la pub pour : ', 'Xerox', 'Vittel', 'Orange'",
            "'Sport', 'Jannie Longo legende du: ', 'Velo', 'Dopage', 'Dessiner cest gagner'",
            "'Sport', 'Marcel Desailly etait: ', 'Noir', 'Blanc', 'Jaune'",
            "'Sport', 'Carlos Tevez est surnomme: ', 'Le cheyenne', 'argentin', 'le jouer de foot'",
            "'Sport', 'Renaud Lavinellie est un: ', 'Perchiste', 'Rugbyman', 'Danseur'",
            "'Sport', 'Suria Bonalie etait une : ', 'Patineuse', 'Comptable', 'Pervanche'",
            "'Sport', 'Lebron James na jamais remporte la NBA : ', 'FAUX', 'VRAI', 'Qui est Lebron?'",
            "'Sport', 'Le PSG appartient a : ', 'Une bande de gars dans le desert', 'Des capitalists americain', 'Justin bridou'",
            "'Sport', 'Qui a gagne la derniere C1 : ', 'FCB', 'MU', 'Juventus'",
            "'Sport', 'De quelle equipe Clovis Cornillac est il supporter ? ', 'Lyon', 'Marseille', 'Paris'",
            "'Sport', 'Dans quel stade peut on souvent apercevoir Nicolas Sarkozy ? ', 'Parc', 'Louis II', 'Chavan'",
            "'Sport', ' Quelle equipe soutient le chanteur Pascal Obispo ? ', 'Bordeaux', 'Lille', 'Marseille'",
            "'Sport', ' Quel club espagnol soutient le tennisman Rafael Nadal ?','Real Madrid' , 'Atletico Madrid', 'FC Barcelona'",
            "'Sport', ' En Angleterre les freres Gallagher du groupe Oasis sont fans de...', 'Man City', 'MU', 'Arsenal'",
            "'Sport', 'Usain Bolt est fan d un club anglais. lequel ?', 'MU', 'Liverpool', 'Arsenal'",
            "'Sport', ' Pour quel club francais bat le cœur du chanteur Benjamin Biolay ?', 'OL', 'OM', 'Saint Etienne'",
            "'Sport', ' Pour quel club francais bat le cœur de Bruno  Solo ?', 'ASSE', 'OM', 'Nice'",
            "'Sport', 'Quel club a les faveurs de l acteur  Sean Connery ?', 'Glasgow Rangers', 'Newcastle', 'Celtics Glasgow'",
            "'Sport', ' Quel club de l Est de la France soutient l acteur Richard Bohringer ?', 'Nancy', 'Metz', 'Reims'",
            "'Sport', ' De quel club Jean Pierre Foucault est il un fan inconditionnel ?', 'Marseille', 'Montpellier', 'Istre'",
            "'Sport', ' Dans quelle region est ne le pilote Sebastien Loeb ? ', 'Alsace', 'Lorraine', 'Normandie'",
            "'Sport', ' Dans quel departement est ne Nabil Fekir l attaquant de Olympique lyonnais ? ', 'Rhone', 'Essone', 'Pas en france'",
            "'Sport', ' Quel est le departement de naissance Andre Pierre Gignac  l attaquant de l OM ?', 'Bouche du Rhone', 'Gard', 'Vard'",
            "'Sport', ' Dans quelle ville la France a t elle remporte la Coupe Davis en 1991 ?  ', 'Lyon', 'Lille', 'Marseille'",
            "'Sport', 'Dans quelle ville espagnole l equipe de France de foot a-t-elle connu une cruelle defaite en demi-finale de la Coupe du monde 1982 ?', 'Seville', 'Valence', 'Madrid'",
            "'Sport', 'De quel port selance la Route du Rhum?', 'Saint malot', 'Les sables d olonne', 'Lorient'",
            "'Sport', 'Sur quel circuit Ayrton Senna a-t-il subi un accident mortel en 1994 ?', 'Imola', 'Monza', 'Silverstone'",
            "'Sport', 'De quelle ville s est elance le rallye Dakar en 2015 ?', 'Buenos Aires', 'Santiago du chili', 'Caracas'",
            "'Sport', 'Dans quelle ville l Olympique de Marseille est-il devenu champion d Europe de foot en 1993 ?', 'Munich', 'Berlin', 'Dortmund'",
            "'Sport', 'De quelle ville anglaise s est elance le Tour de France cycliste en 2014 ?', 'Leeds', 'Birmingham', 'Brighton'",


    };

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + TABLE_QUESTION_REPONSE + "("
                + COL_CATEGORY + ", "
                + COL_QUESTION + ", "
                + COL_BONNE_REPONSE + ", "
                + COL_MAUVAISE_REPONSE_1 + ", "
                + COL_MAUVAISE_REPONSE_2 + ") VALUES ";

        //
        String[] liste = new String[DATA.length];
        int i = 0;
        for (String questionReponse : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + questionReponse + ")";
            i++;
        }

        //
        return liste;
    }

    public QuestionDAO(Context context) {
        super(context);
    }

    public long insert(Question question) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur

        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_BONNE_REPONSE, question.getBonneReponse());
        values.put(COL_CATEGORY,question.getCategory());
        values.put(COL_MAUVAISE_REPONSE_1, question.getMauvaiseReponse1());
        values.put(COL_MAUVAISE_REPONSE_2, question.getMauvaiseReponse2());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_QUESTION_REPONSE, null, values);
    }

    public int update(Question question) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_CATEGORY,question.getCategory());
        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_BONNE_REPONSE, question.getBonneReponse());
        values.put(COL_MAUVAISE_REPONSE_1, question.getMauvaiseReponse1());
        values.put(COL_MAUVAISE_REPONSE_2, question.getMauvaiseReponse2());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(TABLE_QUESTION_REPONSE, values, COL_ID + " = " + question.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une question de la BD à partir de l'ID
        return getDB().delete(TABLE_QUESTION_REPONSE, COL_ID + " = " + id, null);
    }

    public int remove(Question question) {

        return removeByID(question.getId());
    }

    public List<Question> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de question contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE, null);

        return cursorToListQuestion(cursor);
    }

    public Question retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstQuestion(cursor);
    }


    public Question getQuestionRandom() {

        //Récupère dans un Cursor les valeur correspondant à une question au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstQuestion(cursor);
    }

    public Question getQuestionCat(String category){
        //recup cat question
        Cursor cursor =getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " WHERE " + COL_CATEGORY + "=? ORDER BY RANDOM () LIMIT 1", new String[]{category});

        return cursorToFirstQuestion(cursor);

    }

    //Cette méthode permet de convertir un cursor en une liste de questions
    private List<Question> cursorToListQuestion(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexCategory = cursor.getColumnIndex(COL_CATEGORY);
        int indexQuestion = cursor.getColumnIndex(COL_QUESTION);
        int indexBonneReponse = cursor.getColumnIndex(COL_BONNE_REPONSE);
        int indexMauvaiseReponse1 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_1);
        int indexMauvaiseReponse2 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_2);


        // Declaration et initialisation d'une liste de question
        ArrayList<Question> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une question
            Question question = new Question();
            question.setId(cursor.getInt(indexId));
            question.setCategory(cursor.getString(indexCategory));
            question.setQuestion(cursor.getString(indexQuestion));
            question.setBonneReponse(cursor.getString(indexBonneReponse));
            question.setMauvaiseReponse1(cursor.getString(indexMauvaiseReponse1));
            question.setMauvaiseReponse2(cursor.getString(indexMauvaiseReponse2));

            // Ajout dans la liste
            liste.add(question);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une question
    private Question cursorToFirstQuestion(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexCategory= cursor.getColumnIndex(COL_CATEGORY);
        int indexQuestion = cursor.getColumnIndex(COL_QUESTION);
        int indexBonneReponse = cursor.getColumnIndex(COL_BONNE_REPONSE);
        int indexMauvaiseReponse1 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_1);
        int indexMauvaiseReponse2 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_2);


        // Declaration d'une question
        Question question = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une question
            question = new Question();
            question.setId(cursor.getInt(indexId));
            question.setCategory(cursor.getString(indexCategory));
            question.setQuestion(cursor.getString(indexQuestion));
            question.setBonneReponse(cursor.getString(indexBonneReponse));
            question.setMauvaiseReponse1(cursor.getString(indexMauvaiseReponse1));
            question.setMauvaiseReponse2(cursor.getString(indexMauvaiseReponse2));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return question;
    }
}
