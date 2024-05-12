package com.example.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingFragment extends Fragment {

    private ListView listView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PendingFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingFragment newInstance(String param1, String param2) {
        PendingFragment fragment = new PendingFragment();
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

//        FirebaseDatabase.getInstance().getReference().child("Pending Lawyers").child("name1").setValue("Lawyer 1");
//        FirebaseDatabase.getInstance().getReference().child("Pending Lawyers").child("name2").setValue("Lawyer 2");
//        FirebaseDatabase.getInstance().getReference().child("Pending Lawyers").child("name3").setValue("Lawyer 3");
//        FirebaseDatabase.getInstance().getReference().child("Pending Lawyers").child("name4").setValue("Lawyer 4");
//        FirebaseDatabase.getInstance().getReference().child("Pending Lawyers").child("name5").setValue("Lawyer 5");

        // Create a map to hold data for multiple people
        //Map<String, Object> peopleData = new HashMap<>();

        // Populate the map with data for each person
        // peopleData.put("branch1", createPersonData("John", "john@gmail.com"));
        //peopleData.put("branch2", createPersonData("Emily", "emily@example.com"));
        //peopleData.put("branch3", createPersonData("Dan", "dan@example.com"));
        //// Add more people as needed

        // Update the Firebase database with the data for all people
        //FirebaseDatabase.getInstance().getReference().child("Information").updateChildren(peopleData);

        listView=rootView.findViewById(R.id.listView);

        ArrayList<String>list=new ArrayList<>();
        ArrayAdapter adapter = new CustomListAdapter(getContext(),list);

        listView.setAdapter(adapter);

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Lawyers");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    // Get the unique ID of the lawyer
                    String lawyerId = dataSnapshot.getKey();

                    // Get the name of the lawyer
                    String lawyerName = dataSnapshot.child("name").getValue(String.class);

                    // Combine the ID and name to display in the list item
                    String listItem = lawyerName + " (ID: " + lawyerId + ")";

                    // Add the combined string to the list
                    list.add(listItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                // Get the selected item
//                String selectedItem = list.get(position);
//
//                // Create and show the AlertDialog
//                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//                builder.setTitle("Select Action");
//                builder.setMessage("Do you want to Approve or Disapprove " + selectedItem+"?");
//
//
//
//                builder.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//
//                        // Add the selected item to the list of approved items
//                        AcceptedFragment.addToList(selectedItem);
//
//                        // Notify the adapter of the ListView associated with the ApprovedFragment to update the UI
//                        AcceptedFragment.notifyAdapter();
//
//                        // Dismiss the dialog
//                        dialog.dismiss();
//                        // Dismiss the dialog
//
//                        list.remove(position);
//                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Pending Lawyers").child(selectedItem);
//                        databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(getContext(), "Passed", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                        adapter.notifyDataSetChanged();
//
//
//                    }
//                });
//
//
//
//
//
//                builder.setNegativeButton("Disapprove", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String selectedItem = list.remove(position);
//
//                        // Add the selected item to the list of disapproved items
//                        DeclinedFragment.addToList(selectedItem);
//
//                        // Notify the adapter of the ListView associated with the DisapprovedFragment to update the UI
//                        DeclinedFragment.notifyAdapter();
//
//                        // Dismiss the dialog
//                        dialog.dismiss();
//                    }
//                });
//
//
//
//                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//
//                // Return true to indicate that the event has been consumed
//                return true;
//            }
//
//        });



        return rootView;
    }


//    private Map<String, Object> createPersonData(String name, String email) {
//        Map<String, Object> personData = new HashMap<>();
//        personData.put("Name", name);
//        personData.put("Email", email);
//        return personData;
//    }

}