package com.example.projectphase1.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectphase1.AdapterTasks;
import com.example.projectphase1.ClassJobFB;
import com.example.projectphase1.ClassTasks;
import com.example.projectphase1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentTask extends Fragment {
    View view;
    private RecyclerView myRecyclerView;
    private List<ClassTasks> myList;
    AdapterTasks recyclerViewAdopter;
    ProgressBar progressBar;

    public FragmentTask() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_task, container, false);
        progressBar=view.findViewById(R.id.progressBar);
        myRecyclerView = view.findViewById(R.id.recycleView_task);
        recyclerViewAdopter = new AdapterTasks(getContext(), myList,progressBar);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdopter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("jobs");

        // recyclerViewAdopter.notifyDataSetChanged();

        databaseReference.child("0").setValue(new ClassJobFB("https://bigbudsmag.com/wp-content/uploads/shutterstock_115404436.jpg",0,"Electrician",1000));
        databaseReference.child("1").setValue(new ClassJobFB("https://www.pngtube.com/myfile/detail/453-4532620_smile-carpentry-clipart-carpenter-clipart-png.png",1,"Carpenter",800));
        databaseReference.child("2").setValue(new ClassJobFB("https://friendlystock.com/wp-content/uploads/2018/06/9-plumber-holding-wrench-cartoon-clipart.jpg",2,"Plumber",700));
         databaseReference.child("3").setValue(new ClassJobFB("https://previews.123rf.com/images/shadowstudio/shadowstudio1405/shadowstudio140500032/29202768-street-cleaner.jpg",3,"Cleaner",800));
        databaseReference.child("4").setValue(new ClassJobFB("https://media.istockphoto.com/vectors/painter-vector-id472419742?k=6&m=472419742&s=612x612&w=0&h=7nvEaPugMrUrTYwX_QceTyNw0dr8t4x-roAn57iuSIM=",4,"Painter",600));
        databaseReference.child("5").setValue(new ClassJobFB("https://images.vector-images.com/clp5/264704/clp3888718.jpg",5,"Technician",1200));
        databaseReference.child("6").setValue(new ClassJobFB("https://img.123clipartpng.com/9395-car-wash-stock-vector-illustration-and-royalty-free-car-wash-clipart-washing-the-car-326_450.jpg",6,"Car Washer",250));
        databaseReference.child("7").setValue(new ClassJobFB("https://library.kissclipart.com/20181207/qxw/kissclipart-school-gardener-clipart-computer-icons-gardener-cl-c03e025e1d736d02.jpg",7,"Gardener",300));
        databaseReference.child("8").setValue(new ClassJobFB("https://us.123rf.com/450wm/azuzl/azuzl1312/azuzl131200002/24471889-barber-combing-cute-client-girl.jpg?ver=6",8,"Beautician",1500));
        databaseReference.child("9").setValue(new ClassJobFB("https://library.kissclipart.com/20190227/geq/kissclipart-personal-protective-equipment-clipart-welding-work-5f23884b0252bbc2.png",9,"Welder",800));
        databaseReference.child("10").setValue(new ClassJobFB("https://www.pinclipart.com/picdir/middle/39-394393_svg-freeuse-mathsub-com-gre-mathematics-test-preparation.png",10,"Tutor",1000));
        databaseReference.child("11").setValue(new ClassJobFB("https://cdn.shopify.com/s/files/1/0065/4917/6438/products/male-graphic-designers-collection-001_300x300.jpg?v=1550440242",11,"Designer",1200));
        databaseReference.child("12").setValue(new ClassJobFB("https://clipartstation.com/wp-content/uploads/2017/11/architect-clipart-6.jpg",12,"Architect",1000));



        myList = new ArrayList<ClassTasks>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    myList.add(new ClassTasks(data.getValue(ClassJobFB.class).getJob_id(), data.getValue(ClassJobFB.class).getJob_name(), data.getValue(ClassJobFB.class).getJob_img_url(), data.getValue(ClassJobFB.class).getJob_ammount()));

                }
                recyclerViewAdopter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
