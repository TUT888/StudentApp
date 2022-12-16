package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.studentapp.R;
import com.example.studentapp.adapter.ViewPagerAdapter;
import com.example.studentapp.extra_fragment.AddNewPostFragment;
import com.example.studentapp.extra_fragment.PostDetailFragment;
import com.example.studentapp.extra_fragment.RateFragment;
import com.example.studentapp.extra_fragment.RatingDetailFragment;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.Rate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    public static final String[] PLACES_TO_CHOOSE = {
            "Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6",
            "Quận 7", "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12",
            "Quận Bình Tân", "Quận Bình Thạnh", "Quận Gò Vấp", "Quận Phú Nhuận", "Quận Tân Bình", "Quận Tân Phú",
            "Quận Thủ Đức", "Huyện Bình Chánh", "Huyện Cần Giờ", "Huyện Củ Chi", "Huyện Hóc Môn", "Huyện Nhà Bè"
    };
    public static final String[] FIELDS_TO_CHOOSE = {
            "Toán Học", "Vật Lý", "Hóa Học", "Ngữ Văn", "Lịch Sử", "Địa Lý", "Sinh Học", "Tiếng Anh",
            "Âm Nhạc", "Hội Họa", "Kỹ Năng", "Kinh Tế", "Kỹ thuật", "Công nghệ thông tin", "Y Học", "Khác"
    };
    BottomNavigationView mBottomNavigationView;
    ViewPager2 mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);

        setUpViewPager();
        setUpBottomNavigationView();
    }

    //ViewPager settings
    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.bottomNav_home).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.bottomNav_search).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.bottomNav_post).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.bottomNav_chat).setChecked(true);
                        break;
                    case 4:
                        mBottomNavigationView.getMenu().findItem(R.id.bottomNav_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void setUpBottomNavigationView() {
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottomNav_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.bottomNav_search:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.bottomNav_post:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.bottomNav_chat:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.bottomNav_profile:
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    public void goToPostDetailFragment(Post post, String previousFragment) {
        //Example: previousFragment = MyPostFragment.class.getSimpleName() = "MyPostFragment"
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PostDetailFragment detailFragment = new PostDetailFragment(); //Child fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", (Serializable) post);
        bundle.putString("previous", previousFragment);

        detailFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.main_activity_content, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void goToRateDetailFragment(Rate rate, String previousFragment) {
        //Example: previousFragment = MyPostFragment.class.getSimpleName() = "MyPostFragment"
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RatingDetailFragment ratingDetailFragment = new RatingDetailFragment(); //Child fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("rate", (Serializable) rate);
        bundle.putString("previous", previousFragment);

        ratingDetailFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.main_activity_content, ratingDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void goToAddNewPostFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        AddNewPostFragment detailFragment = new AddNewPostFragment(); //Child fragment
        Bundle bundle = new Bundle();
        detailFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.main_activity_content, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void goToRateFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RateFragment rateFragment = new RateFragment(); //Child fragment
        Bundle bundle = new Bundle();
        rateFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.main_activity_content, rateFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}