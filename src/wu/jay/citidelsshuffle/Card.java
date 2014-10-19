package wu.jay.citidelsshuffle;

public class Card {

	// Some constants
	public static final int NUM_BONUS_DISTRICT = 14;
	public static final int NUM_CHAR_CARDS = 18;
	public static final int MAX_CHOSEN_CHARS = 9;
	public static final int MAX_CHOSEN_DIST = 3;

	// Some string constants
	public static final String CHAR_ASSASSIN = "Assassin";
	public static final String CHAR_THIEF = "Thief";
	public static final String CHAR_MAGICIAN = "Magician";
	public static final String CHAR_KING = "King";
	public static final String CHAR_BISHOP = "Bishop";
	public static final String CHAR_MERCH = "Merchant";
	public static final String CHAR_ARCH = "Architect";
	public static final String CHAR_WARLORD = "Warlord";
	public static final String CHAR_WITCH = "Witch";
	public static final String CHAR_TAX = "Tax Collector";
	public static final String CHAR_WIZARD = "Wizard";
	public static final String CHAR_EMPEROR = "Emperor";
	public static final String CHAR_ABBOT = "Abbot";
	public static final String CHAR_ALCHEMIST = "Alchemist";
	public static final String CHAR_NAV = "Navigator";
	public static final String CHAR_DIPLOMAT = "Diplomat";
	public static final String CHAR_QUEEN = "Queen";
	public static final String CHAR_ARTIST = "Artist";

	// Bonus district names
	public static final String DIST_WISHWELL = "Wishing Well";
	public static final String DIST_PHOUSE = "Poor House";
	public static final String DIST_THROOM = "Throne Room";
	public static final String DIST_MPROOM = "Map Room";
	public static final String DIST_ARM = "Armory";
	public static final String DIST_QUARRY = "Quarry";
	public static final String DIST_IMPTREA = "Imperial Treasury";
	public static final String DIST_HOSP = "Hospital";
	public static final String DIST_PARK = "Park";
	public static final String DIST_LIGHT = "Lighthouse";
	public static final String DIST_BELL = "Bell Tower";
	public static final String DIST_BROOM = "Ball Room";
	public static final String DIST_FACT = "Factory";
	public static final String DIST_MUSEUM = "Museum";

	public String name;
	public int rank;

	public Card() {
		name = "";
		rank = 0;
	}

	public Card(String n, int r) {
		name = n;
		rank = r;
	}

	public static void initCharacterDB(Card[] charDB, int size) {

		// Check bounds
		if (size != Card.NUM_CHAR_CARDS) return;

		// ranks and init
		for (int i = 0; i < size; i++) charDB[i] = new Card("", i / 2);

		// Names
		charDB[0].name = CHAR_ASSASSIN;
		charDB[1].name = CHAR_WITCH;
		charDB[2].name = CHAR_THIEF;
		charDB[3].name = CHAR_TAX;
		charDB[4].name = CHAR_MAGICIAN;
		charDB[5].name = CHAR_WIZARD;
		charDB[6].name = CHAR_KING;
		charDB[7].name = CHAR_EMPEROR;
		charDB[8].name = CHAR_BISHOP;
		charDB[9].name = CHAR_ABBOT;
		charDB[10].name = CHAR_MERCH;
		charDB[11].name = CHAR_ALCHEMIST;
		charDB[12].name = CHAR_ARCH;
		charDB[13].name = CHAR_NAV;
		charDB[14].name = CHAR_WARLORD;
		charDB[15].name = CHAR_DIPLOMAT;
		charDB[16].name = CHAR_ARTIST;
		charDB[17].name = CHAR_QUEEN;

	}

	public static void initDistrictsDB(Card[] distDB, int size) {
		// Check bounds
		if (size != Card.NUM_BONUS_DISTRICT) return;

		// init
		for (int i = 0; i < size; i++) distDB[i] = new Card();

		// Fill out constants
		distDB[0].name = DIST_WISHWELL;
		distDB[1].name = DIST_PHOUSE;
		distDB[2].name = DIST_THROOM;
		distDB[3].name = DIST_MPROOM;
		distDB[4].name = DIST_ARM;
		distDB[5].name = DIST_QUARRY;
		distDB[6].name = DIST_IMPTREA;
		distDB[7].name = DIST_HOSP;
		distDB[8].name = DIST_PARK;
		distDB[9].name = DIST_LIGHT;
		distDB[10].name = DIST_BELL;
		distDB[11].name = DIST_BROOM;
		distDB[12].name = DIST_FACT;
		distDB[13].name = DIST_MUSEUM;

	}
}
