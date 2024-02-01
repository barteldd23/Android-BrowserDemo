package edu.ddb.browserdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.ddb.browserdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
 public static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navView.setOnItemSelectedListener(onItemSelectedListener);

        webView = findViewById(R.id.webView);

    }

    private NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d(TAG, "onNavigationItemSelected: ");

            int id = item.getItemId();

            if(id == R.id.navigation_dashboard)
            {
                // Show the contents of a url.
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://www.fvtc.edu/");
            } else if (id == R.id.navigation_notifications)
            {
                // construct html
                String html = "<html><body style='background-color:cornflowerblue'>";
                html += "<table border='1'>";
                html += "<tr><td>Bart</td></tr>";
                html += "<tr><td>Marge</td></tr>";
                html += "<tr><td>Homer</td></tr>";
                html += "<tr><td>Lisa</td></tr>";
                html += "<tr><td>Mo</td></tr>";
                html += "</table></body></html>";

                webView.loadData(html, "text/html", "UTF-8");
            }

            return true;
        }
    };

}