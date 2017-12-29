package com.robillo.readrush.ui.main.library;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.db.model.library.LibraryContentRepository;
import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.db.model.library.LibraryCoverContent;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Content;
import com.robillo.readrush.data.network.retrofit.model.LibraryItem;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.rushread.ReadRushActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends BaseFragment implements LibraryMvpView, View.OnLongClickListener {

    public static int RUSH_COUNT = 0;

    //online
    private List<LibraryItem> mLibraryItemList;

    //offline
    LiveData<List<LibraryCover>> mListLibraryCovers;
    LiveData<List<LibraryCoverContent>> mRushIDContent;
    List<LibraryCoverContent> mRushIDContentsList = new ArrayList<>();
    List<LibraryCover> mCoversList;
    private List<Content> mContents;

    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @BindView(R.id.error_layout)
    LinearLayout mErrorLayout;

    @BindView(R.id.main_layout)
    LinearLayout mMainLayout;

    @BindView(R.id.error_drawable)
    ImageView mErrorDrawable;

    @BindView(R.id.error_header)
    TextView mErrorHeader;

    @BindView(R.id.error_description)
    TextView mErrorDescription;

    @BindView(R.id.rush1)
    ImageView mRushOne;

    @BindView(R.id.rush2)
    ImageView mRushTwo;

    @BindView(R.id.rush3)
    ImageView mRushThree;

    @BindView(R.id.rush4)
    ImageView mRushFour;

    @BindView(R.id.rush5)
    ImageView mRushFive;

    @BindView(R.id.rush6)
    ImageView mRushSix;

    @BindView(R.id.rush7)
    ImageView mRushSeven;

    @BindView(R.id.rush8)
    ImageView mRushEight;

    @BindView(R.id.rush9)
    ImageView mRushNine;

    @BindView(R.id.refresh_buttom)
    Button mRefreshButton;

    @BindView(R.id.progress_bar_library)
    ProgressBar mProgressLibrary;

    @Inject
    LibraryCoverRepository mLibraryCoverRepository;

    @Inject
    LibraryContentRepository mLibraryContentRepository;

    @Inject
    LibraryMvpPresenter<LibraryMvpView> mPresenter;

    public LibraryFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(LibraryFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(LibraryFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);
        mApiService = ApiClient.getClient().create(ApiInterface.class);

        mRushOne.setOnLongClickListener(this);
        mRushTwo.setOnLongClickListener(this);
        mRushThree.setOnLongClickListener(this);
        mRushFour.setOnLongClickListener(this);
        mRushFive.setOnLongClickListener(this);
        mRushSix.setOnLongClickListener(this);
        mRushSeven.setOnLongClickListener(this);
        mRushEight.setOnLongClickListener(this);
        mRushNine.setOnLongClickListener(this);

        mListLibraryCovers = mLibraryCoverRepository.getAllCovers();

        checkForExistingRushesOffline();
    }

    @Override
    public void loadRushes() {
        if(mMainLayout!=null && mMainLayout.getVisibility()==View.GONE) mMainLayout.setVisibility(View.VISIBLE);
        if(mErrorLayout!=null && mErrorLayout.getVisibility()==View.VISIBLE) mErrorLayout.setVisibility(View.GONE);
        if(mProgressLibrary!=null && mProgressLibrary.getVisibility()==View.VISIBLE) mProgressLibrary.setVisibility(View.GONE);

        loadCoversIntoRushes();
    }

    @Override
    public void checkForExistingRushesOnline() {

        Log.e("Check", "..for existing rushes online");

        Call<List<LibraryItem>> call = mApiService.fetchLibrary(mPrefsHelper.getUserId());
        if(call!=null){

            Log.e("Check", "..non null call for online fetch");

            call.enqueue(new Callback<List<LibraryItem>>() {
                @Override
                public void onResponse(@NonNull Call<List<LibraryItem>> call, @NonNull Response<List<LibraryItem>> response) {
                    mLibraryItemList = response.body();
                    if(mLibraryItemList!=null){

                        Log.e("Check", "..saving library covers offline");

                        saveLibraryCoversOffline(mLibraryItemList);
                        if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                        if(mMainLayout!=null) mMainLayout.setVisibility(View.VISIBLE);
                        if(mErrorLayout!=null) mErrorLayout.setVisibility(View.GONE);
                    }
                    else {

                        Log.e("Check", "..zero rushes");

                        if(mMainLayout!=null) mMainLayout.setVisibility(View.INVISIBLE);
                        if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                        if(mErrorLayout!=null) mErrorLayout.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<LibraryItem>> call, @NonNull Throwable t) {
                    if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                    if(mErrorLayout!=null) mErrorLayout.setVisibility(View.VISIBLE);
                    if(mMainLayout!=null) mMainLayout.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void checkForExistingRushesOffline() {
        mListLibraryCovers.observe(this, new Observer<List<LibraryCover>>() {
            @Override
            public void onChanged(@Nullable List<LibraryCover> libraryCovers) {
                mCoversList = libraryCovers;
                if(verifyOfflineRushesExist()){
                    //SHOW THE LIBRARY AND DONT SHOW THE ERROR MESSAGE
                    loadRushes();
                }
                else {
                    if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                    if(mErrorLayout!=null) mErrorLayout.setVisibility(View.VISIBLE);
                    if(mMainLayout!=null) mMainLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean verifyOfflineRushesExist() {
        return !(mCoversList == null) && mCoversList.size() > 0;
    }

    @Override
    public void showNoRushes() {
        if(mErrorLayout.getVisibility()==View.GONE){
            mErrorLayout.setVisibility(View.VISIBLE);
        }
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        mErrorDrawable.setAnimation(animation);
        mErrorDescription.setAnimation(animation);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        mErrorHeader.setAnimation(animation);
    }

    @Override
    public void loadCoversIntoRushes() {
        int count = mCoversList.size();
        if(count>0 && mCoversList.get(0).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(0).getCover(), mRushOne);
        else loadSingleCoverIntoSingleRush(null, mRushOne);
        if(count>1 && mCoversList.get(1).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(1).getCover(), mRushTwo);
        else loadSingleCoverIntoSingleRush(null, mRushTwo);
        if(count>2 && mCoversList.get(2).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(2).getCover(), mRushThree);
        else loadSingleCoverIntoSingleRush(null, mRushThree);
        if(count>3 && mCoversList.get(3).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(3).getCover(), mRushFour);
        else loadSingleCoverIntoSingleRush(null, mRushFour);
        if(count>4 && mCoversList.get(4).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(4).getCover(), mRushFive);
        else loadSingleCoverIntoSingleRush(null, mRushFive);
        if(count>5 && mCoversList.get(5).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(5).getCover(), mRushSix);
        else loadSingleCoverIntoSingleRush(null, mRushSix);
        if(count>6 && mCoversList.get(6).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(6).getCover(), mRushSeven);
        else loadSingleCoverIntoSingleRush(null, mRushSeven);
        if(count>7 && mCoversList.get(7).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(7).getCover(), mRushEight);
        else loadSingleCoverIntoSingleRush(null, mRushEight);
        if(count>8 && mCoversList.get(8).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(8).getCover(), mRushNine);
        else loadSingleCoverIntoSingleRush(null, mRushNine);
    }

    @Override
    public void loadSingleCoverIntoSingleRush(String cover_url, ImageView rush) {
        Glide.with(getActivity()).load(cover_url).placeholder(R.color.white).crossFade().centerCrop().into(rush);
    }

    @Override
    public void openReadRushScreen(final int index) {
        int count = mCoversList.size();
        if(count > index){
            mRushIDContent = mLibraryContentRepository.getContentsFromID(mCoversList.get(index).getRush_id());
            mRushIDContent.observe(this, new Observer<List<LibraryCoverContent>>() {
                @Override
                public void onChanged(@Nullable List<LibraryCoverContent> libraryCoverContents) {
                    if(libraryCoverContents!=null && libraryCoverContents.size()>0){
                        mRushIDContentsList = libraryCoverContents;
                    }
                    else {
                        getContent(mCoversList.get(index).getRush_id());
                    }
                }
            });
            if(mRushIDContentsList.size()>0 && mRushIDContentsList.get(0).getRush_id().equals(mCoversList.get(index).getRush_id())){
                mRushIDContentsList = new ArrayList<>();
                startActivity(ReadRushActivity.getStartIntent(getActivity(), mCoversList.get(index).getRush_id(),
                        mCoversList.get(index).isRush_audio(),
                        mCoversList.get(index).getTitle()));
            }
        }
        else Toast.makeText(getActivity(), "Empty Cover", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveLibraryCoversOffline(List<LibraryItem> mCoversList) {
        if(mCoversList!=null && mCoversList.size()>0){
            for(int i=0; i<mCoversList.size(); i++){
                //noinspection ConstantConditions
                LibraryCover mRoomCover = new LibraryCover(mCoversList.get(i).getRush_id(), mCoversList.get(i).getTitle(), mCoversList.get(i).getAuthor(), mCoversList.get(i).getRating(), mCoversList.get(i).getEst_time(), mCoversList.get(i).getPages(), mCoversList.get(i).getCover(), null,
                         mCoversList.get(i).getAudio()!=null);
                mLibraryCoverRepository.insertCoverItem(mRoomCover);
            }
        }
        checkForExistingRushesOffline();
    }

    @Override
    public void showDeleteAlertDialog(final String bookName, final int coverIndex) {
        if(getActivity()!=null)
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText(bookName + " will be removed from your library!")
                    .setConfirmText("Yes,delete it!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            Log.e("status", "success " + mPrefsHelper.getUserId() + " " + mCoversList.get(coverIndex).getRush_id());

                            Call<ResponseBody> call = mApiService.deleteRushFromLibrary(mPrefsHelper.getUserId(), mCoversList.get(coverIndex).getRush_id());
                            if(call!=null){
                                call.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                                        Log.e("status", "success " + response.raw().message() + response.body());
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                                        Log.e("status", "failure");
                                    }
                                });
                            }

                            mLibraryCoverRepository.deleteCoverItem(mCoversList.get(coverIndex));
                            loadRushes();
                            sDialog
                                    .setTitleText("Deleted!")
                                    .setContentText(bookName + " has been deleted from your library!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .showCancelButton(false)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
    }

    @Override
    public void setLongClickListenersRushes(ImageView imageView, final int index) {
        int count = mCoversList.size();
        if(count > index){
            showDeleteAlertDialog(mCoversList.get(index).getTitle(), index);
        }
    }

    @OnClick(R.id.rush1)
    public void clickmRushOne() {
        openReadRushScreen(0);
    }

    @OnClick(R.id.rush2)
    public void clickmRushTwo() {
        openReadRushScreen(1);
    }

    @OnClick(R.id.rush3)
    public void clickmRushThree() {
        openReadRushScreen(2);
    }
    @OnClick(R.id.rush4)
    public void clickmRushFour() {
        openReadRushScreen(3);
    }

    @OnClick(R.id.rush5)
    public void clickmRushFive() {
        openReadRushScreen(4);
    }

    @OnClick(R.id.rush6)
    public void clickmRushSix() {
        openReadRushScreen(5);
    }

    @OnClick(R.id.rush7)
    public void clickmRushSeven() {
        openReadRushScreen(6);
    }

    @OnClick(R.id.rush8)
    public void clickmRushEight() {
        openReadRushScreen(7);
    }

    @OnClick(R.id.rush9)
    public void clickmRushNine() {
        openReadRushScreen(8);
    }

    @OnClick(R.id.refresh_buttom)
    public void setmRefreshButton(){
        checkForExistingRushesOnline();
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.rush1:{
                setLongClickListenersRushes(mRushOne, 0);
                return true;
            }
            case R.id.rush2:{
                setLongClickListenersRushes(mRushTwo, 1);
                return true;
            }
            case R.id.rush3:{
                setLongClickListenersRushes(mRushThree, 2);
                return true;
            }
            case R.id.rush4:{
                setLongClickListenersRushes(mRushFour, 3);
                return true;
            }
            case R.id.rush5:{
                setLongClickListenersRushes(mRushFive, 4);
                return true;
            }
            case R.id.rush6:{
                setLongClickListenersRushes(mRushSix, 5);
                return true;
            }
            case R.id.rush7:{
                setLongClickListenersRushes(mRushSeven, 6);

                return true;
            }
            case R.id.rush8:{
                setLongClickListenersRushes(mRushEight, 7);
                return true;
            }
            case R.id.rush9:{
                setLongClickListenersRushes(mRushNine, 8);
                return true;
            }
            default:
                return true;
        }
    }

    public void getContent(String mRushId) {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Content>> call = mApiService.getRushContent(mRushId);
        if(call!=null){
            call.enqueue(new Callback<List<Content>>() {
                @Override
                public void onResponse(@NonNull Call<List<Content>> call, @NonNull Response<List<Content>> response) {
                    mContents = response.body();
                    if(mContents!=null && mContents.size()>0){
                        //noinspection ConstantConditions
                        for(int i=0; i<mContents.size(); i++){
                            LibraryCoverContent coverContent = new LibraryCoverContent
                                    (mContents.get(i).getContent_id(), mContents.get(i).getRush_id(),
                                            mContents.get(i).getContent(), mContents.get(i).getAttr(),
                                            mContents.get(i).getDatetime(), mContents.get(i).getPage_no());
                            mLibraryContentRepository.insertContentItem(coverContent);
                        }
//
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Content>> call, @NonNull Throwable t) {
                    if(getActivity()!=null) Toast.makeText(getActivity(), "Network Error while downloading rush content", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
