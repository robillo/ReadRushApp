package com.robillo.readrush.ui.audio_play

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
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

class AudioPlayActivity : AppCompatActivity(), AudioPlayMvpView {

    var mAudioContentsList : List<RushAudioContent> = ArrayList()
    lateinit var mApiService : ApiInterface
    lateinit var mRushId : String

    fun getStartIntent(context: Context, rush_id: String): Intent {
        val intent = Intent(context, AudioPlayActivity::class.java)
        intent.putExtra("rush_id", rush_id)
        return intent
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_play)

        retry.setOnClickListener {
            retry.visibility = View.GONE
            Toast.makeText(this, "Trying to connect again", Toast.LENGTH_SHORT).show()
            loadContents()
        }

        setUp()

    }

    override fun setUp() {

        mRushId = intent.getStringExtra("rush_id")

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
                    Toast.makeText(this@AudioPlayActivity, "Size " + mAudioContentsList.size, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<RushAudioContent>>, t: Throwable) {
                Toast.makeText(this@AudioPlayActivity, "Network Error", Toast.LENGTH_SHORT).show()
                retry.visibility = View.VISIBLE
            }
        })
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
