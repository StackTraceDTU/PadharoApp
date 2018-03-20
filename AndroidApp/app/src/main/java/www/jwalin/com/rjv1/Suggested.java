package www.jwalin.com.rjv1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class Suggested extends Fragment {
    private ArrayList<Places> placesArrayList=new ArrayList<Places>();
    public  Suggested(){}
//
//    public Suggested(ArrayList<Places> placesArrayList){
//        this.placesArrayList=placesArrayList;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(((ArrayList<Places>)getArguments().getSerializable("placeslist")).size()!=0){
        placesArrayList=(ArrayList<Places>)getArguments().getSerializable("placeslist");
        View rootView= inflater.inflate(R.layout.fragment_suggested, container, false);
        ListView lv= (ListView) rootView.findViewById(R.id.suggestListView);
        AdapterForPlaces adapter=new AdapterForPlaces(this.getActivity(),placesArrayList);
        lv.setAdapter(adapter);
        return rootView;}
        return inflater.inflate(R.layout.fragment_suggested, container, false);
    }




}
