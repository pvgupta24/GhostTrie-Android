package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;


import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class
GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = true;
    private Random random = new Random();

    private String TAG = "Pv";
    private SimpleDictionary simpleDictionary;


    TextView ghost, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            //dictionary = new FastDictionary(inputStream);
            simpleDictionary = new SimpleDictionary(inputStream);
            Log.i(TAG, "Dictionary loaded");


        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }


        ghost = ((TextView) findViewById(R.id.ghostText));
        status = ((TextView) findViewById(R.id.gameStatus));
        Button challenge = (Button) findViewById(R.id.challenge);
        Button restart = (Button) findViewById(R.id.restart);
        onStart(null);


        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = ghost.getText().toString().toLowerCase();
                String word=simpleDictionary.getAnyWordStartingWith(input);
                ghost.setText(word);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

    }

    private void reset() {
        ghost.setText(null);
        status.setText(USER_TURN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        try{
        Log.i(TAG, "onKeyUp");
        String input = ghost.getText().toString();

        if (KeyEvent.KEYCODE_A <= keyCode && KeyEvent.KEYCODE_Z >= keyCode) {
            char a = (char) event.getUnicodeChar();
            input = input + "" + a;
            Log.i("Input bro2:", input);
            ghost.setText(input);
            //CharSequence temp = input;
            String prefix = input;

            ////    for (int i = 0; i < input.length(); i++) {
            //String prefix = input.substring(0, input.length()).toString();
            if (simpleDictionary.isWord(prefix)) {
                status.setText("You lose");
                return true;
            }
            //}

            //  userTurn = false;

            if (simpleDictionary.getAnyWordStartingWith(input)==null) {
                status.setText("You lose:NoWord starts with this");
                return true;
            }
                computerTurn();

        }
    }
        catch (Exception e)
        {
            Log.i("Errors:",e.getMessage());
            //Toast.makeText(this,"Error"boolean,);
        }

        return super.onKeyUp(keyCode, event);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     *
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        // userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
       /* String start = simpleDictionary.getStartWord();
        ((TextView) findViewById(R.id.ghostText)).setText(start);*/
        try {
            String start = simpleDictionary.getWord();
            ghost.setText(start);
        }
        catch (Exception e)
        {

        }
        // ghost.setCustomSelectionActionModeCallback(ghost.getText().length());

        return true;
    }

    private void computerTurn() {
        Log.i(TAG, "compTurn");
        // TextView label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again


        String input = ghost.getText().toString();

       if(check(input))
            status.setText("Computer Wins");

        else {

            String wordStartingWith = simpleDictionary.getAnyWordStartingWith(input);


            Log.i(TAG, "Shuru ho gaya");


            input=wordStartingWith.substring(0, input.length() + 1);
            ghost.setText(input);
            if(check(input))
            {
                status.setText("U Win");
            }

        }

       // userTurn = true;
      //  status.setText(USER_TURN);
    }

    public boolean check(String word)
    {
        if(simpleDictionary.isWord(word)||(simpleDictionary.getAnyWordStartingWith(word).equals(null)))
               return true;

        return  false;
    }
}
