package com.zfakgroup.israel.schoollocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 27.01.15.
 */
public class FragmentCourses extends Fragment {
    private SlidingTabLayout mSlidingTabLayout;

    private List<SamplePagerItem> mTabs = new ArrayList<SamplePagerItem>();
    Course[] courses;
    Group[] groups;

    public FragmentCourses() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTabs.add(new SamplePagerItem(
                getString(R.string.courses) // Title
        ));

        mTabs.add(new SamplePagerItem(
                getString(R.string.groups) // Title
        ));


        ListGroupAsync listGroupAsync = new ListGroupAsync();
        listGroupAsync.execute(new AsyncCallback() {
            @Override
            public void callback(Object result) {
                if (result instanceof List) {
                    ArrayList<Group> arrayCourses = (ArrayList<Group>) result;
                    groups = new Group[((ArrayList<Course>) result).size()];
                    arrayCourses.toArray(groups);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slide_layout, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        FragmentPagerAdapter fpa = new SampleFragmentPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(fpa);

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);

        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(viewPager);
    }

    class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the {@link android.support.v4.app.Fragment} to be displayed at {@code position}.
         * <p/>
         * Here we return the value returned from
         */
        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i).createFragment(i);
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we return the value returned from {@link SamplePagerItem#getTitle()}.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }
        // END_INCLUDE (pageradapter_getpagetitle)

    }

    static class SamplePagerItem {
        private final CharSequence mTitle;

        SamplePagerItem(CharSequence mTitle) {
            this.mTitle = mTitle;
        }

        /**
         * @return A new {@link Fragment} to be displayed by a {@link ViewPager}
         */
        Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return ContentCourseFragment.newInstance(mTitle);
                case 1:
                    return ContentGroupFragment.newInstance(mTitle);

            }
            return new Fragment();
        }

            /**
             * @return the title which represents this tab. In this sample this is used directly by
             * {@link android.support.v4.view.PagerAdapter#getPageTitle(int)}
             */
            CharSequence getTitle () {
                return mTitle;
            }


        }
    }