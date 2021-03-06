package wu.jay.citadelsshuffle;

import java.util.Random;
import wu.jay.citadelsshuffle.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CitadelsShuffle extends Activity {
	// Some constants for bundle and saving state
	public static final String KEY_CHOSEN_CHARS = "chosenChars";
	public static final String KEY_CHOSEN_DIST = "chosenDist";
	
	// Define items on the layout
	// Fragments
	private ShuffleFrag mShuffleFrag;
	private RulesFrag mRulesFrag;
	
	// String to be output to textviews
	private String mCharDisp;
	private String mDistDisp;
	private String mRules;
	
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

		setContentView(R.layout.main);
		
		// init
		init();
		
		if ( savedInstanceState != null )
		{
			mCharDisp = savedInstanceState.getString(KEY_CHOSEN_CHARS);
			mDistDisp = savedInstanceState.getString(KEY_CHOSEN_DIST);
		}
	}
	
	// Update textview texts and shuffle some characters and districts if they are not already
	// determined by the savedInstanceState.
	@Override
	public void onResume() {
		super.onResume();
		
		if (mCharDisp == null) shuffleCharacters(mNumChosenChars);
		if (mDistDisp == null) shuffleDistricts(mNumChosenDist);
		
		// Update display
		updateDisplayChars();
		updateDisplayDistricts();
		updateRules();
	}
	
	// Initialize everything...
	private void init() {
		// Get fragments
		mShuffleFrag = (ShuffleFrag) getFragmentManager().findFragmentById(R.id.shuffle_frag);
		mRulesFrag = (RulesFrag) getFragmentManager().findFragmentById(R.id.rules_frag);
		
		// String reset
		mCharDisp = null;
		mDistDisp = null;
		mRules = null;
		
		// Setup cards and such only if we haven't initially done so.
		Card.initCharacterDB(mCharacterDB, Card.NUM_CHAR_CARDS);
		Card.initDistrictsDB(mDistrictDB, Card.NUM_BONUS_DISTRICT);
	}
	
	// Fragment update textview wrappers
	private void updateDisplayChars() {
		mShuffleFrag.updateDisplayChars(mCharDisp);
	}
	
	// Fragment update textview wrappers
	private void updateDisplayDistricts() {
		mShuffleFrag.updateDisplayDistricts(mDistDisp);
	}
	
	private void updateRules() {
		mRulesFrag.updateRules(mRules);
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
			showMainFragments();
			return true;
		case R.id.action_shuffle_char:
			shuffleCharacters(mNumChosenChars);
			showMainFragments();
			return true;
		case R.id.action_shuffle_district:
			shuffleDistricts(mNumChosenDist);
			showMainFragments();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	// Settings
	private void openSettings() {
		// Hide the other fragments.
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.hide(mShuffleFrag);
		transaction.hide(mRulesFrag);
		
		// Get fragment manager and transaction.  Replace with settings fragment.
		transaction.replace(android.R.id.content, new Settings());
		transaction.addToBackStack(null);
		transaction.commit();
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
			mCharDisp = mCharDisp + (i+1) + ". " + mCharacterDB[2*i+rand].name;
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
			mDistDisp = mDistDisp + (i+1) + ". " + mDistrictDB[rand].name;
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
	
	// Reveal Shuffle and Rules fragment, while removing settings fragment.
	private void showMainFragments() {
		// If the fragment is hidden, reveal it. Assume also that the rules fragment is
		// also hidden and the settings fragment is revealed, so detach that.
		if (mShuffleFrag.isHidden() || mRulesFrag.isHidden()) {
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.show(mShuffleFrag);
			transaction.show(mRulesFrag);
			transaction.remove(getFragmentManager().findFragmentById(android.R.id.content));
			transaction.commit();
		}
	}
}

