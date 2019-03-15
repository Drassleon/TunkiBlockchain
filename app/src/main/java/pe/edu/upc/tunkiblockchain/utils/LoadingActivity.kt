package pe.edu.upc.tunkiblockchain.utils

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.TextView
import com.github.ybq.android.spinkit.style.FoldingCube
import pe.edu.upc.tunkiblockchain.R

class LoadingActivity : AppCompatActivity() {

    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var statusProgressBar: ProgressBar
    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingProgressBar = findViewById(R.id.loadingBarBlockChain)
        statusTextView = findViewById(R.id.tvStatus)
        statusProgressBar = findViewById(R.id.imgStatus)
        statusProgressBar.indeterminateDrawable = FoldingCube()
        var timer = object : CountDownTimer(  2900, 29) {
            override fun onFinish() {
                finish()
            }
            override fun onTick(millisUntilFinished: Long) {
                loadingProgressBar.progress++
                if(loadingProgressBar.progress <= 10)
                {
                    statusTextView.text = "Sending transactions info"
                }
                if(loadingProgressBar.progress in 11..25)
                {
                    statusTextView.text = "Adding Transaction to a block"
                }
                if(loadingProgressBar.progress in 26..50)
                {
                    statusTextView.text = "Transmitting Transaction to other peers"
                }
                if(loadingProgressBar.progress in 51..75)
                {
                    statusTextView.text = "Validating that info complies with smart contract"
                }
                if(loadingProgressBar.progress in 76..100)
                {
                    statusTextView.text = "Adding block to the chain"
                }
            }
        }.start()

    }
}
