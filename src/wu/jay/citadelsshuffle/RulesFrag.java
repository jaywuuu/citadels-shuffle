package wu.jay.citadelsshuffle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import wu.jay.citadelsshuffle.R;

public class RulesFrag extends Fragment {
	
	private TextView mTvRules;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.rules_frag, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initLayout();
	}
	
	// intiialize layout items to fields
	private void initLayout() {
		mTvRules = (TextView) getActivity().findViewById(R.id.rules_Tv_rules);
	}
	
	// public methods for updating rule fields
	public void updateRules(String rules) {
		mTvRules.setText(rules);
	}
}
