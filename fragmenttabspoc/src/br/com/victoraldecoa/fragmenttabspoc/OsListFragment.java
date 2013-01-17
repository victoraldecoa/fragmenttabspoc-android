package br.com.victoraldecoa.fragmenttabspoc;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OsListFragment extends ListFragment{
	
    /** An array of items to display in ArrayList */
    String os_versions[] = new String[]{
            "Mountain Lion",
            "Lion",
            "Snow Leopard",
            "Leopard",
            "Tiger",
            "Panther",
            "Jaguar",
            "Puma"
    };
    	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		os_versions = getArguments().getStringArray("os_versions");
		
		/** Creating array adapter to set data in listview */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, os_versions);

        /** Setting the array adapter to the listview */
        setListAdapter(adapter);

		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
    public void onStart() {
            super.onStart();

            /** Setting the multiselect choice mode for the listview */
            getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}
