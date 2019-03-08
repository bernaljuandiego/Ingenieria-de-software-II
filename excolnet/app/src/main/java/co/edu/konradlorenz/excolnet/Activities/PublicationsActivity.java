package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import co.edu.konradlorenz.excolnet.Fragments.NewPuplicationFragment;
import co.edu.konradlorenz.excolnet.Fragments.PasswordRecoveryFragment;
import co.edu.konradlorenz.excolnet.R;
import co.edu.konradlorenz.excolnet.Utils.Permissions;
import co.edu.konradlorenz.excolnet.Utils.SectionsPagerAdapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PublicationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        if(checkPermissionsArray(Permissions.PERMISSIONS)){

        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }

        BottomAppBar bottomAppBar = findViewById(R.id.app_bar_publications);
        setSupportActionBar(bottomAppBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NewPuplicationFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.principal, fragment);
                ft.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.app_bar_home:
                Toast.makeText(this, "Home Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_bar_notifications:
                Toast.makeText(this, "Notifications Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_bar_profile:
                Toast.makeText(this, "Profile Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }








        private static final int ACTIVITY_NUM = 2;
        private static final int VERIFY_PERMISSIONS_REQUEST = 1;

        private ViewPager mViewPager;



        /**
         * return the current tab number
         * 0 = GalleryFragment
         * 1 = PhotoFragment
         * @return
         */
        public int getCurrentTabNumber(){
            return mViewPager.getCurrentItem();
        }

        /**
         * setup viewpager for manager the tabs
         */

        public int getTask(){
            return getIntent().getFlags();
        }

        /**
         * verifiy all the permissions passed to the array
         * @param permissions
         */
        public void verifyPermissions(String[] permissions){

            ActivityCompat.requestPermissions(
                    PublicationsActivity.this,
                    permissions,
                    VERIFY_PERMISSIONS_REQUEST
            );
        }

        /**
         * Check an array of permissions
         * @param permissions
         * @return
         */
        public boolean checkPermissionsArray(String[] permissions){

            for(int i = 0; i< permissions.length; i++){
                String check = permissions[i];
                if(!checkPermissions(check)){
                    return false;
                }
            }
            return true;
        }



        /**
         * Check a single permission is it has been verified
         * @param permission
         * @return
         */
        public boolean checkPermissions(String permission){

            int permissionRequest = ActivityCompat.checkSelfPermission(PublicationsActivity.this, permission);

            if(permissionRequest != PackageManager.PERMISSION_GRANTED){
                return false;
            }
            else{
                return true;
            }
        }
    }


