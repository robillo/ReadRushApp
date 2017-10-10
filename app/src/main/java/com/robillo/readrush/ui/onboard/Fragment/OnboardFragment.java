package com.robillo.readrush.ui.onboard.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanks.htextview.line.LineTextView;
import com.robillo.readrush.R;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardFragment extends BaseFragment implements OnboardFMvpView {

    @Inject
    OnboardFMvpPresenter<OnboardFMvpView> mPresenter;

    @BindView(R.id.image)
    ImageView mImageView;

    @BindView(R.id.header)
    LineTextView mLineHeader;

    @BindView(R.id.description)
    TextView mTextDescription;

    public static final String BUNDLE_NAME = OnboardFragment.class.getSimpleName();

    public OnboardFragment() {
        // Required empty public constructor
    }

    public static OnboardFragment newInstance(Bundle bundle) {
        OnboardFragment fragment = new OnboardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_onboard, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(this);

        }

        setUp(v);
        return v;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp(View view) {
        Bundle args = getArguments().getBundle(BUNDLE_NAME);
    }
}
