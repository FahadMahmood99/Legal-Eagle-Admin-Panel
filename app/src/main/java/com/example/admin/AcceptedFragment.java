    package com.example.admin;

    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link AcceptedFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public class AcceptedFragment extends Fragment {

        private ListView listView;
        private static ArrayAdapter<String> adapter;
        private ArrayList<String> approvedList = new ArrayList<>();

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public AcceptedFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AcceptedFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static AcceptedFragment newInstance(String param1, String param2) {
            AcceptedFragment fragment = new AcceptedFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }



        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            rootView = inflater.inflate(R.layout.fragment_pending, container, false);

            listView=rootView.findViewById(R.id.listView);

            adapter = new ArrayAdapter<String>(getContext(),R.layout.list_item,approvedList);

            listView.setAdapter(adapter);


            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Approved Lawyers");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    approvedList.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        approvedList.add((String) snapshot1.getValue());
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            return rootView;
        }

        public static void addToList(String item) {

            FirebaseDatabase.getInstance().getReference().child("Approved Lawyers").child("name").setValue(item);


        }


        public static void notifyAdapter() {

            // Notify the adapter of the ListView associated with the ApprovedFragment to update the UI
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }