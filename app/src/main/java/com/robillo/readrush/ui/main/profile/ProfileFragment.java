package com.robillo.readrush.ui.main.profile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;
import com.robillo.readrush.ui.main.profile.highlights_list.HighlightsListFragment;
import com.robillo.readrush.ui.main.profile.profile_list.ProfileListFragment;
import com.robillo.readrush.ui.settings.SettingsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements ProfileMvpView {

    @BindView(R.id.name)
    TextView mUserName;

    @BindView(R.id.membership_type)
    TextView mMembershipType;

    @BindView(R.id.profile)
    Button mProfileTab;

    @BindView(R.id.highlights)
    Button mHighlightsTab;

    @BindView(R.id.profile_container)
    FrameLayout mFragmentContainer;

    @BindView(R.id.settings)
    ImageView mSettings;

    @Inject
    ProfileMvpPresenter<ProfileMvpView> mPresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(ProfileFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(ProfileFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        setProfileListFragment();
    }

    @OnClick(R.id.profile)
    public void setmProfileTab() {
        setProfileListFragment();
    }

    @OnClick(R.id.highlights)
    public void setmHighlightsTab() {
        setHighlightsListFragment();
    }

    @OnClick(R.id.settings)
    public void setmSettings() {
        startActivity(SettingsActivity.getStartIntent(getActivity()));
    }

    @Override
    public void setProfileListFragment() {
        if(getActivity()!=null)
            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.profile_container, new ProfileListFragment(), "profile_list")
                    .commit();
    }

    @Override
    public void setHighlightsListFragment() {
        if(getActivity()!=null)
            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.profile_container, new HighlightsListFragment(), "highlights_list")
                    .commit();
    }
}
