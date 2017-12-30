package com.robillo.readrush.ui.audio_play

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.halilibo.bettervideoplayer.BetterVideoCallback
import com.halilibo.bettervideoplayer.BetterVideoPlayer
import com.halilibo.bettervideoplayer.subtitle.CaptionsView
import com.robillo.readrush.R
import com.robillo.readrush.R2
import com.robillo.readrush.data.network.retrofit.ApiClient
import com.robillo.readrush.data.network.retrofit.ApiInterface
import com.robillo.readrush.data.network.retrofit.model.RushAudioContent
import com.robillo.readrush.ui.rushoverview.OverviewActivity
import kotlinx.android.synthetic.main.activity_audio_play.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AudioPlayActivity : AppCompatActivity(), AudioPlayMvpView, BetterVideoCallback {

    var mAudioContentsList : List<RushAudioContent> = ArrayList()
    lateinit var mApiService : ApiInterface
    lateinit var mRushId : String
    lateinit var mRushName : String
    var mCurrentPlayingAudioPos : Int = 0

    //player
    var myIsPlaying : Boolean = false

    fun getStartIntent(context: Context, rush_id: String, rush_name: String): Intent {
        val intent = Intent(context, AudioPlayActivity::class.java)
        intent.putExtra("rush_id", rush_id)
        intent.putExtra("rush_name", rush_name)
        return intent
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_play)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }

        retry.setOnClickListener {
            retry.visibility = View.GONE
            Toast.makeText(this, "Trying to connect again", Toast.LENGTH_SHORT).show()
            loadContents()
        }

        back.setOnClickListener {
            onBackPressed()
        }

        next.setOnClickListener {
            playNextRushAudio()
        }

        prev.setOnClickListener {
            playPrevRushAudio()
        }

        play_pause.setOnClickListener {
            if(player.isPrepared){
                if(player.isPlaying && myIsPlaying){
                    player.pause()
                }
                else if (!player.isPlaying && !myIsPlaying){
                    player.start()
                }
                else {
                    Log.e("else", "else" + (player.isPlaying) + (myIsPlaying))
                }
            }
        }

        setUp()

    }

    override fun setUp() {

        mRushId = intent.getStringExtra("rush_id")
        mRushName = intent.getStringExtra("rush_name")

        m_title.text = mRushName

        mApiService = ApiClient.getClient().create(ApiInterface::class.java)

        loadContents()

    }

    override fun loadContents() {
        //retrofit call to load contents
        val call : Call<List<RushAudioContent>> = mApiService.fetchRushAudioContent(mRushId)
        call.enqueue(object : Callback<List<RushAudioContent>> {
            override fun onResponse(call: Call<List<RushAudioContent>>, response: Response<List<RushAudioContent>>) {
                if(response.body()!=null){
                    mAudioContentsList = response.body()!!
                    if(mAudioContentsList.size>0){
                        avi.hide()
                        showAudioLayout()
                    }
                }
            }

            override fun onFailure(call: Call<List<RushAudioContent>>, t: Throwable) {
                Toast.makeText(this@AudioPlayActivity, "Network Error", Toast.LENGTH_SHORT).show()
                retry.visibility = View.VISIBLE
            }
        })
    }

    override fun showAudioLayout() {
        loading_layout.visibility = View.GONE
        audio_layout.visibility = View.VISIBLE
        initMediaPlayer(mCurrentPlayingAudioPos)
    }

    override fun initMediaPlayer(index : Int) {
        val temp = "(Rush " + (index + 1) + "/" + mAudioContentsList.size + ")"
        count.text = temp
        preparePlayer(index)
    }

    override fun playNextRushAudio() {
        if(mCurrentPlayingAudioPos<mAudioContentsList.size){
            mCurrentPlayingAudioPos = mCurrentPlayingAudioPos + 1
            preparePlayer(mCurrentPlayingAudioPos)
        }
    }

    override fun playPrevRushAudio() {
        if(mCurrentPlayingAudioPos>0){
            mCurrentPlayingAudioPos = mCurrentPlayingAudioPos - 1
            preparePlayer(mCurrentPlayingAudioPos)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    public override fun onPause() {
        super.onPause()
        // Make sure the player stops playing if the user presses the home button.
        if(player.isPlaying && myIsPlaying){
            player.pause()
            myIsPlaying = false
        }
    }

    override fun preparePlayer(index: Int) {

        if(mCurrentPlayingAudioPos == 0){
            prev.visibility = View.INVISIBLE
            prev.isClickable = true
        }
        else{
            prev.visibility = View.VISIBLE
            prev.isClickable = false
        }

        if(mCurrentPlayingAudioPos == mAudioContentsList.size){
            next.visibility = View.INVISIBLE
            next.isClickable = true
        }
        else{
            next.visibility = View.VISIBLE
            next.isClickable = false
        }



        player.setLoop(false)
        player.setLoadingStyle(2)
        player.setCaptions(Uri.parse("http://" + mAudioContentsList.get(index).srt), CaptionsView.CMime.SUBRIP)
        player.setSource(Uri.parse(mAudioContentsList.get(index).audio_link));
        player.setCallback(this)
    }



    override fun onError(player: BetterVideoPlayer?, e: Exception?) {

    }

    override fun onPrepared(player: BetterVideoPlayer?) {
        play_pause.visibility = View.VISIBLE
        play_pause.isClickable = true
        play_pause.setImageDrawable(resources.getDrawable(R.drawable.ic_play_circle_filled_black_24dp))
    }

    override fun onStarted(player: BetterVideoPlayer?) {
        play_pause.setImageDrawable(resources.getDrawable(R.drawable.ic_pause_circle_filled_black_24dp))
        myIsPlaying = true
    }

    override fun onCompletion(player: BetterVideoPlayer?) {
        play_pause.setImageDrawable(resources.getDrawable(R.drawable.ic_play_circle_filled_black_24dp))
        myIsPlaying = false
    }

    override fun onBuffering(percent: Int) {

    }

    override fun onPreparing(player: BetterVideoPlayer?) {

    }

    override fun onToggleControls(player: BetterVideoPlayer?, isShowing: Boolean) {

    }

    override fun onPaused(player: BetterVideoPlayer?) {
        play_pause.setImageDrawable(resources.getDrawable(R.drawable.ic_play_circle_filled_black_24dp))
        myIsPlaying = false
    }













    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openActivityOnTokenExpire() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(resId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(resId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isNetworkConnected(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
