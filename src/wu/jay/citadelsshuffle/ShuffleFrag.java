package wu.jay.citadelsshuffle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import wu.jay.citadelsshuffle.R;

public class ShuffleFrag extends Fragment {
	
	// Define items on the layout
	private TextView mTvChars;
	private TextView mTvDistricts;
	
	// Inflate layout
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, 
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.shuffle_frag, container, false);
	}
	
	// Fill out layout fields
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Call initLayout() to initalize layout fields
		initLayout();
	}
	
	private void initLayout() {
		// Layout items
		mTvChars = (TextView) getActivity().findViewById(R.id.shuf_Tv_character_cards_display);
		mTvDistricts = (TextView) getActivity().findViewById(R.id.shuf_Tv_district_cards_display);
	}
	
	public void updateDisplayChars(String chars) {
		mTvChars.setText(chars);
	}
	
	public void updateDisplayDistricts(String districts) {
		mTvDistricts.setText(districts);
	}
}
