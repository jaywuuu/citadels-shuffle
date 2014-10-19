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
	public static final String KEY_CHOSEN_CHARS = "chosenChars";
	public static final String KEY_CHOSEN_DIST = "chosenDist";
	
	// Define items on the layout
	private TextView mTvChars;
	private TextView mTvDistricts;
	
	// String to be output to textviews
	private String mCharDisp;
	private String mDistDisp;
	
	// Cards 
	private Card mCharacterDB[] = new Card[Card.NUM_CHAR_CARDS];
	private Card mDistrictDB[] = new Card[Card.NUM_BONUS_DISTRICT];
	private int mNumChosenChars = Card.MAX_CHOSEN_CHARS;
	private int mNumChosenDist = Card.MAX_CHOSEN_DIST;
	
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
			mCharDisp = savedInstanceState.getString(KEY_CHOSEN_CHARS);
			mDistDisp = savedInstanceState.getString(KEY_CHOSEN_DIST);
		}
		else {
			// Choose random characters
			shuffleCharacters(mNumChosenChars);

			// Choose random bonus districts
			shuffleDistricts(mNumChosenDist);
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
		mCharDisp = "";
		mDistDisp = "";
		
		// Setup cards and such only if we haven't initially done so.
		Card.initCharacterDB(mCharacterDB, Card.NUM_CHAR_CARDS);
		Card.initDistrictsDB(mDistrictDB, Card.NUM_BONUS_DISTRICT);
	}
	
	private void updateDisplayChars() {
		mTvChars.setText(mCharDisp);
	}
	
	private void updateDisplayDistricts() {
		mTvDistricts.setText(mDistDisp);
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
			shuffleCharacters(mNumChosenChars);
			return true;
		case R.id.action_shuffle_district:
			shuffleDistricts(mNumChosenDist);
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
		shuffleCharacters(mNumChosenChars);
		shuffleDistricts(mNumChosenDist);
	}
	
	// Shuffle only characters and update display
	private void shuffleCharacters(int maxChars) {
		Random randGen = new Random();
		int rand = 0;
		
		// Reset String
		mCharDisp = "";
		
		for (int i = 0; i<maxChars; i ++) {
			rand = randGen.nextInt(2);
			mCharDisp = mCharDisp + "(" + (i+1) + ") " + mCharacterDB[2*i+rand].name;
			// Add comma if not the last one
			if (i<maxChars-1) mCharDisp = mCharDisp + "\n";
		}
		updateDisplayChars();
	}
	
	// shuffle only districts and update display
	private void shuffleDistricts(int maxDist) {
		Random randGen = new Random();
		int rand = 0;
		
		// Reset String
		mDistDisp = "";
		
		for (int i = 0; i<maxDist; i ++) {
			rand = randGen.nextInt(Card.NUM_BONUS_DISTRICT);
			mDistDisp = mDistDisp + mDistrictDB[rand].name;
			if (i<maxDist-1) mDistDisp = mDistDisp + "\n";
		}
		updateDisplayDistricts();
	}
	
	// Application life cycle overrides
	// Persist data, etc
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString(KEY_CHOSEN_CHARS, mCharDisp);
		savedInstanceState.putString(KEY_CHOSEN_DIST, mDistDisp);
	}
	
}

