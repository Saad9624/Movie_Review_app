package com.example.danishlaptop.test;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class DetailedActivity extends YouTubeBaseActivity{
    YouTubePlayerView youTubePlayerView;
    Button Playbtn;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private FirebaseAuth mAuth;
    DatabaseReference actionmoviesRef;
    DatabaseReference action_movies_Ref;
    FirebaseStorage storage;
    String updateId;
    String key;
    ArrayList<ActionMovies> movies;

    ArrayList<Comments> comm1;
    ArrayList<ActionMovies> movies1;
    RecyclerAdapter adapter;
    TextView MovieTitleDetailed;
    TextView MovieDescriptionDetailed;
    String link;
    ImageView MovieImageDetailed;
    TextView Comment1;
    RatingBar RatingBar;
    ImageView MovieImage;
    Button RatingBarText;
    Button CommentBtn;
    RecyclerAdapterForcomments RecyclerAdapterForComments;
    Button RatingBtn;
    TextView RatingText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        TextView RatingText=findViewById(R.id.RatingText);
        final RecyclerView recyclerViewforcomment = findViewById(R.id.RecyclerForComment);
        final EditText ComentSubEditText = findViewById(R.id.CommentSubEditText);
        final Button CommentSubBtn = findViewById(R.id.CommentSubBtn);


        RatingBar = findViewById(R.id.RatingBar);
        RatingBarText = findViewById(R.id.RatingBarText);


        RatingBarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();
            }
        });

      //  UserRating.setText("" + RatingBar.getRating());


        MovieImage = findViewById(R.id.MovieImage);

        MovieTitleDetailed = findViewById(R.id.MovieTitleDetailed);
        MovieDescriptionDetailed = findViewById(R.id.MovieDescriptionDetailed);
        MovieImageDetailed = findViewById(R.id.MovieImageDetailed);
        Playbtn = findViewById(R.id.PlayBtn);
        youTubePlayerView = findViewById(R.id.YoutubePlayer);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(link);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        Playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.initialize(Playerconfig.API_KEY, onInitializedListener);

            }
        });

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        actionmoviesRef = reference.child("Action_Movies").child(getIntent().getExtras().getString("KEY"));



        /*
        actionmoviesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ActionMovies actionMovies=dataSnapshot.getValue(ActionMovies.class);
                movies.add(actionMovies);
                //

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/

//Detail activity
        actionmoviesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ActionMovies actionMovies = dataSnapshot.getValue(ActionMovies.class);
                MovieTitleDetailed.setText(actionMovies.getName());
                MovieDescriptionDetailed.setText(actionMovies.getDescription());


                Glide.with(MovieImageDetailed.getContext()).load(actionMovies.getImage()).into(MovieImageDetailed);
                link = actionMovies.getLink();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        storage = FirebaseStorage.getInstance();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database1.getReference();
       // action_movies_Ref = databaseReference.child("Action_Movies").child(getIntent().getExtras().getString("KEY"));

        ActionMovies actionMovies = new ActionMovies();
        updateId = actionMovies.getId();


        CommentSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comm = ComentSubEditText.getText().toString();
                    String key = updateId;
                if (updateId == null) {

                    key = actionmoviesRef.push().getKey();
                }
                Comments actmovie = new Comments(key, comm);
                actionmoviesRef.child("Comment").child(key).setValue(actmovie);
                updateId = null;
                ComentSubEditText.setText("");
            }

        });



        comm1 = new ArrayList<>();


        //for Comments Retrievable
    actionmoviesRef.child("Comment").addChildEventListener(new ChildEventListener() {
           @Override


            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Comments comm = dataSnapshot.getValue(Comments.class);
                comm1.add(comm);
                RecyclerAdapterForComments.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Comments comm = dataSnapshot.getValue(Comments.class);
                int indexOfItem = comm1.indexOf(comm);
                if (indexOfItem >= 0) {
                    comm1.set(indexOfItem, comm);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Comments comm = dataSnapshot.getValue(Comments.class);
                int indexOfItem = comm1.indexOf(comm);
                comm1.remove(indexOfItem);
                RecyclerAdapterForComments.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        movies = new ArrayList<>();
        RecyclerAdapterForComments = new RecyclerAdapterForcomments(comm1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewforcomment.setLayoutManager(layoutManager);

        recyclerViewforcomment.setAdapter(RecyclerAdapterForComments);
    }

    public  void showDialog() {
        Dialog dialog = new Dialog(DetailedActivity.this);

        dialog.setContentView(R.layout.layout_dialog);
        final TextView Tv = dialog.findViewById(R.id.DialogText);
        final Button btn = dialog.findViewById(R.id.RatingBtn1);
        final RatingBar ratingBar = dialog.findViewById(R.id.ratingBar1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Rt = Tv.getText().toString();
                String key = updateId;
                if (updateId == null) {

                    key = actionmoviesRef.push().getKey();
                }
                Comments Ratmovie = new Comments(key, Rt);
                actionmoviesRef.child("Rating").child(key).setValue(Ratmovie);
                updateId = null;
                Tv.setText("");
                Tv.setText("You Rated::" + ratingBar.getRating());
            }
        });


        dialog.show();

        // for rating

  /*actionmoviesRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
    ActionMovies actionMovies =dataSnapshot.getValue(ActionMovies.class);

    RatingText.setText(actionMovies.getRatingText);



    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});
*/

    }

}

