package br.com.victoraldecoa.fragmenttabspoc;

import br.com.victoraldecoa.fragmenttabspoc.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity {
	TabHost tHost;
	
    /** An array of items to display in ArrayList */
    String android_versions[] = new String[]{
            "Jelly Bean",
            "IceCream Sandwich",
            "HoneyComb",
            "Ginger Bread",
            "Froyo"
    };
    
    /** An array of items to display in ArrayList */
	 String apple_versions[] = new String[]{
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tHost = (TabHost) findViewById(android.R.id.tabhost);
        tHost.setup();
        
        /** Defining Tab Change Listener event. This is invoked when tab is changed */
        TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				android.support.v4.app.FragmentManager fm =   getSupportFragmentManager();
				OsListFragment androidFragment = (OsListFragment) fm.findFragmentByTag("android");
				OsListFragment appleFragment = (OsListFragment) fm.findFragmentByTag("apple");
								
				android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
				
				/** Detaches the androidfragment if exists */
				if(androidFragment!=null)
					ft.detach(androidFragment);
				
				/** Detaches the applefragment if exists */
				if(appleFragment!=null)
					ft.detach(appleFragment);
				
				/** If current tab is android */
				if(tabId.equalsIgnoreCase("android")){
					
					if(androidFragment==null){		
						/** Create AndroidFragment and adding to fragmenttransaction */
						OsListFragment f = new OsListFragment();

						Bundle args = new Bundle();
						args.putStringArray("os_versions", android_versions);
						f.setArguments(args);
						
						ft.add(R.id.realtabcontent,f, "android");				
					}else{
						/** Bring to the front, if already exists in the fragmenttransaction */
						ft.attach(androidFragment);						
					}
					
				}else{	/** If current tab is apple */
					if(appleFragment==null){
						/** Create AppleFragment and adding to fragmenttransaction */

						OsListFragment f = new OsListFragment();

						Bundle args = new Bundle();
						args.putStringArray("os_versions", apple_versions);
						f.setArguments(args);
						ft.add(R.id.realtabcontent,f, "apple");			
					}else{
						/** Bring to the front, if already exists in the fragmenttransaction */
						ft.attach(appleFragment);						
					}
				}
				ft.commit();				
			}
		};
		
		
		/** Setting tabchangelistener for the tab */
		tHost.setOnTabChangedListener(tabChangeListener);
                
		/** Defining tab builder for Android tab */
        TabHost.TabSpec tSpecAndroid = tHost.newTabSpec("android");
        tSpecAndroid.setIndicator("Android",getResources().getDrawable(R.drawable.android));       
        tSpecAndroid.setContent(new TabHost.TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				return new View(getBaseContext());
			}
		});
        tHost.addTab(tSpecAndroid);
        
        
        /** Defining tab builder for Apple tab */
        TabHost.TabSpec tSpecApple = tHost.newTabSpec("apple");
        tSpecApple.setIndicator("Apple",getResources().getDrawable(R.drawable.apple));       
        tSpecApple.setContent(new TabHost.TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				return new View(getBaseContext());
			}
		});
        tHost.addTab(tSpecApple);
        
     }   
 	
}

