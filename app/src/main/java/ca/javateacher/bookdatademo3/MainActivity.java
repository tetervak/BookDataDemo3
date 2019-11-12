/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{

  private BookAppNavigator mBookAppNavigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // make the toolbar "up" button work with the navigation
    Toolbar toolbar = findViewById(R.id.toolbar);
    NavController navController
      = Navigation.findNavController(this, R.id.nav_host);
    AppBarConfiguration appBarConfiguration
      = new AppBarConfiguration.Builder(navController.getGraph()).build();
    NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    setSupportActionBar(toolbar);
    mBookAppNavigator = new BookAppNavigator(navController);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch(item.getItemId()){
      case android.R.id.home:{
        navigateToBookList();
        return true;
      }
      case R.id.about:{
        showAboutDialog();
        return true;
      }
      default:{
        return super.onOptionsItemSelected(item);
      }
    }
  }

  private void showAboutDialog() {
    AboutFragment fragment = AboutFragment.newInstance();
    fragment.show(getSupportFragmentManager(),AboutFragment.TAG);
  }

  public void navigateToBookList() {
    mBookAppNavigator.toBookList();
  }
}
