package wu.jay.citidelsshuffle;

import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class CitidelsShuffle extends Activity {
	// Some constants for bundle and saving state
	private final String KEY_CHOSEN_CHARS = "strChosenChars";
	private final String KEY_CHOSEN_DIST = "strChosenDist";
	
	// Define items on the layout
	private TextView mTvChars;
	private TextView mTvDistricts;
	
	// String to be output to textviews
	private String strCharDisp;
	private String strDistDisp;
	
	// Cards 
	private Card CharacterDB[] = new Card[Card.NUM_CHAR_CARDS];
	private Card DistrictDB[] = new Card[Card.NUM_BONUS_DISTRICT];
	private int numChosenChars = Card.MAX_CHOSEN_CHARS;
	private int numChosenDist = Card.MAX_CHOSEN_DIST;
	
	// Number of players
	// private int numPlayers;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup action bar as tabs
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);

		setContentView(R.layout.activity_citidels_shuffle);
		
		// init
		init();
		
		if ( savedInstanceState != null )
		{
			strCharDisp = savedInstanceState.getString(KEY_CHOSEN_CHARS);
			strDistDisp = savedInstanceState.getString(KEY_CHOSEN_DIST);
		}
		else {
			// Choose random characters
			shuffleCharacters(numChosenChars);

			// Choose random bonus districts
			shuffleDistricts(numChosenDist);
		}
		
		// Update display
		updateDisplayChars();
		updateDisplayDistricts();
		
	}
	
	// Initialize everything...
	private void init() {
		// Layout items
		mTvChars = (TextView) findViewById(R.id.main_Tv_character_cards_display);
		mTvDistricts = (TextView) findViewById(R.id.main_Tv_district_cards_display);	
		
		// String reset
		strCharDisp = "";
		strDistDisp = "";
		
		// Setup cards and such only if we haven't initially done so.
		Card.initCharacterDB(CharacterDB, Card.NUM_CHAR_CARDS);
		Card.initDistrictsDB(DistrictDB, Card.NUM_BONUS_DISTRICT);
	}
	
	private void updateDisplayChars() {
		mTvChars.setText(strCharDisp);
	}
	
	private void updateDisplayDistricts() {
		mTvDistricts.setText(strDistDisp);
	}

	// Add action bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflates menu items
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_action, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Respond to action items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_shuffle:
			shuffleAll();
			return true;
		case R.id.action_shuffle_char:
			shuffleCharacters(numChosenChars);
			return true;
		case R.id.action_shuffle_district:
			shuffleDistricts(numChosenDist);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	// Settings
	private void openSettings() {
		return;
	}
	
	// Shuffle everything
	private void shuffleAll() {
		shuffleCharacters(numChosenChars);
		shuffleDistricts(numChosenDist);
	}
	
	// Shuffle only characters and update display
	private void shuffleCharacters(int maxChars) {
		Random randGen = new Random();
		int rand = 0;
		
		// Reset String
		strCharDisp = "";
		
		for (int i = 0; i<maxChars; i ++)
		{
			rand = randGen.nextInt(2);
			strCharDisp = strCharDisp + "(" + i + ") " + CharacterDB[2*i+rand].name;
			// Add comma if not the last one
			if (i<maxChars-1)
				strCharDisp = strCharDisp + "\n";
		}
		updateDisplayChars();
	}
	
	// shuffle only districts and update display
	private void shuffleDistricts(int maxDist) {
		Random randGen = new Random();
		int rand = 0;
		
		// Reset String
		strDistDisp = "";
		
		for (int i = 0; i<maxDist; i ++)
		{
			rand = randGen.nextInt(Card.NUM_BONUS_DISTRICT);
			strDistDisp = strDistDisp + DistrictDB[rand].name;
			if (i<maxDist-1)
				strDistDisp = strDistDisp + "\n";
		}
		updateDisplayDistricts();
	}
	
	// Application life cycle overrides
	// Persist data, etc
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString(KEY_CHOSEN_CHARS, strCharDisp);
		savedInstanceState.putString(KEY_CHOSEN_DIST, strDistDisp);
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
}

